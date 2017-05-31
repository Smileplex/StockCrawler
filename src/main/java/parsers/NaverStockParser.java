package parsers;

import helper.Helper;
import models.KeywordInfo;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import services.PageParser;
import services.StockFetcher;

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

    private final StockFetcher stockFetcher;

    public NaverStockParser(StockFetcher stockFetcher) {
        this.stockFetcher = stockFetcher;
    }

    @Override
    public KeywordInfo parse(Document document) {
        KeywordInfo keywordInfo = new KeywordInfo();
        keywordInfo.setKeywordName(parseKeywordName(document));
        keywordInfo.setKeywordType(checkKeywordTypeBy(document, parseKeywordName(document)));
        keywordInfo.setRelatedKeywordLinks(parseRelatedKeywordLinks(document));
        return keywordInfo;
    }

    private String parseKeywordName(Document document) {
        return document.select("input#nx_query").attr("value").trim()
                .replaceAll("주가", "")
                .replaceAll("주식", "");
    }

    private int checkKeywordTypeBy(Document document, String keywordName) {
        String pageHtml = document.toString();
        boolean isStockKeyword = pageHtml.contains("<div class=\"stock_tlt\">");
        if (!isStockKeyword) {
            boolean isStockRelatedKeyword = Helper.containValue("관련주,연관주,테마주,수혜주,대장주", keywordName);
            if (isStockRelatedKeyword)
                return STOCK_RELATED_KEYWORD;
            else
                return NOT_A_STOCK_KEYWORD;
        } else {
            return STOCK_KEYWORD;
        }
    }

    private List<String> parseRelatedKeywordLinks(Document document){
        List<String> links = new ArrayList<String>();
        Elements elements = document.select("div._rk_hcheck a");
        for(Element element : elements){
            String link = SEARCH_QUERY + element.attr("href");
            if(!links.contains(link))
                links.add(link);
        }
        return links;
    }

}