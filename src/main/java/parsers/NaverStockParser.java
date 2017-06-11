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
    private final String SEARCH_QUERY = "https://m.search.naver.com/search.naver";
    private final int STOCK_KEYWORD = 1;
    private final int STOCK_RELATED_KEYWORD = 2;
    private final int NOT_A_STOCK_KEYWORD = 3;
    private Document pageHtml;
    private String keywordName;

    @Override
    public KeywordInfo parse(Document pageHtml) {
        this.pageHtml = pageHtml;
        keywordName = getKeywordName();
        int keywordType = getKeywordType();
        if(keywordType == NOT_A_STOCK_KEYWORD) {
            return new EmptyKeywordInfo();
        }
        return new KeywordInfo(keywordName, keywordType, getRelatedKeywordLinks());
    }

    private String getKeywordName() {
        try {
            return URLDecoder.decode(pageHtml.select("input#nx_query").attr("value").trim()
                    .replaceAll("주가", "")
                    .replaceAll("주식", ""),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private int getKeywordType() {
        if (!isStockKeyword()) {
            if (isStockRelatedKeyword())
                return STOCK_RELATED_KEYWORD;
            else
                return NOT_A_STOCK_KEYWORD;
        } else {
            return STOCK_KEYWORD;
        }
    }

    private boolean isStockKeyword() {
        return pageHtml.toString().contains("<div class=\"stock_tlt\">");
    }

    private boolean isStockRelatedKeyword() {
        return Helper.containValue("관련주,연관주,테마주,수혜주,대장주", keywordName);
    }

    private List<String> getRelatedKeywordLinks(){
        List<String> collectedLinks = new ArrayList<>();
        for(Element anchorTag : getAnchorTags()){
            String link = combineLinkWithSearchQuery(anchorTag);
            if(!collectedLinks.contains(link)) collectedLinks.add(link);
        }
        return collectedLinks;
    }

    private Elements getAnchorTags() {
        return pageHtml.select("div._rk_hcheck a");
    }

    private String combineLinkWithSearchQuery(Element anchorTag) {
        return SEARCH_QUERY + anchorTag.attr("href");
    }
}
