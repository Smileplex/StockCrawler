package parsers;

import helper.Helper;
import models.KeywordInfo;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import services.PageParser;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by DongwooSeo on 2017-05-28.
 */
public class NaverStockParser implements PageParser {
    public static final String SEARCH_QUERY = "https://m.search.naver.com/search.naver";
    private static final int STOCK_KEYWORD = 1;
    private static final int STOCK_RELATED_KEYWORD = 2;
    private static final int NOT_A_STOCK_KEYWORD = 3;

    @Override
    public KeywordInfo processParsing(Document pageHtml) {
        String keywordName = getKeywordName(pageHtml);
        int keywordType = getKeywordType(pageHtml, keywordName);
        if(keywordType == NOT_A_STOCK_KEYWORD) {
            return new EmptyKeywordInfo();
        }
        return new KeywordInfo(keywordName, keywordType, getRelatedKeywordLinks(pageHtml));
    }

    private String getKeywordName(Document pageHtml) {
        try {
            return URLDecoder.decode(pageHtml.select("input#nx_query").attr("value").trim()
                    .replaceAll("주가", "")
                    .replaceAll("주식", ""),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private int getKeywordType(Document pageHtml, String keywordName) {
        String pageText = pageHtml.toString();
        if (!isStockKeyword(pageText)) {
            if (isStockRelatedKeyword(keywordName))
                return STOCK_RELATED_KEYWORD;
            else
                return NOT_A_STOCK_KEYWORD;
        } else {
            return STOCK_KEYWORD;
        }
    }

    private boolean isStockKeyword(String pageText) {
        return pageText.contains("<div class=\"stock_tlt\">");
    }

    private boolean isStockRelatedKeyword(String keywordName) {
        return Helper.containValue("관련주,연관주,테마주,수혜주,대장주", keywordName);
    }

    private List<String> getRelatedKeywordLinks(Document pageHtml){
        List<String> collectedLinks = new ArrayList<>();
        for(Element anchorTag : getAnchorTags(pageHtml)){
            String link = combineLinkWithSearchQuery(anchorTag);
            if(!collectedLinks.contains(link)) collectedLinks.add(link);
        }
        return collectedLinks;
    }

    private Elements getAnchorTags(Document pageHtml) {
        return pageHtml.select("div._rk_hcheck a");
    }

    private String combineLinkWithSearchQuery(Element anchorTag) {
        return SEARCH_QUERY + anchorTag.attr("href");
    }
}
