package services;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by DongwooSeo on 2017-05-28.
 */
public interface StockFetcher {
    void fetch(Document document, int stockKeywordId);
}
