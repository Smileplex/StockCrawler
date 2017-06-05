package services;

import com.google.inject.Inject;
import hibernate.dao.StockDetailDao;
import hibernate.dao.StockDetailKeywordDao;
import models.EmptyStockInfo;
import models.StockInfo;
import org.jsoup.nodes.Document;

import java.util.logging.Logger;

/**
 * Created by DongwooSeo on 6/4/2017.
 */
public class StockDetailParserImpl implements StockDetailParser {
    public static final int NOT_EXIST = 0;
    private static final Logger logger = Logger.getLogger(StockDetailParserImpl.class.getName());
    private final PageReader pageReader;
    private final StockFetcher stockFetcher;
    private final StockDetailDao stockDetailDao;
    private final StockDetailKeywordDao stockDetailKeywordDao;

    @Inject
    public StockDetailParserImpl(PageReader pageReader, StockFetcher stockFetcher,
                                 StockDetailDao stockDetailDao,
                                 StockDetailKeywordDao stockDetailKeywordDao) {
        this.pageReader = pageReader;
        this.stockFetcher = stockFetcher;
        this.stockDetailDao = stockDetailDao;
        this.stockDetailKeywordDao = stockDetailKeywordDao;
    }

    @Override
    public void parse(String link, int stockKeywordId) {
        Document pageHtml = pageReader.read(link);
        StockInfo stockInfo = stockFetcher.fetch(pageHtml, stockKeywordId);
        if(stockInfo instanceof EmptyStockInfo)
            return;

        int stockDetailId = stockDetailDao.save(stockInfo);
        if(stockDetailId==NOT_EXIST)
            return;
        logger.info(String.format("[%s | %s] updated",stockInfo.getStockName(),stockInfo.getStockCode()));
        stockDetailKeywordDao.save(stockDetailId, stockKeywordId);
    }
}
