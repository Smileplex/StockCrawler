package services;

import com.google.inject.Inject;
import hibernate.dao.StockDetailDao;
import hibernate.dao.StockDetailKeywordDao;
import models.StockInfo;
import org.jsoup.nodes.Document;

/**
 * Created by DongwooSeo on 6/4/2017.
 */
public class StockDetailParserImpl implements StockDetailParser {

    public static final int NOT_EXIST = 0;
    private final PageFetcher pageFetcher;
    private final StockFetcher stockFetcher;
    private final StockDetailDao stockDetailDao;
    private final StockDetailKeywordDao stockDetailKeywordDao;

    @Inject
    public StockDetailParserImpl(PageFetcher pageFetcher, StockFetcher stockFetcher,
                                 StockDetailDao stockDetailDao,
                                 StockDetailKeywordDao stockDetailKeywordDao) {
        this.pageFetcher = pageFetcher;
        this.stockFetcher = stockFetcher;
        this.stockDetailDao = stockDetailDao;
        this.stockDetailKeywordDao = stockDetailKeywordDao;
    }

    @Override
    public void parse(String link, int stockKeywordId) {
        Document pageHtml = pageFetcher.fetch(link);
        StockInfo stockInfo = stockFetcher.fetch(pageHtml, stockKeywordId);
        int stockDetailId = stockDetailDao.save(stockInfo);
        if(stockDetailId==NOT_EXIST)
            return;
        stockDetailKeywordDao.save(stockDetailId, stockKeywordId);
    }
}
