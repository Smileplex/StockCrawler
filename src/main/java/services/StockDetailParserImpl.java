package services;

import com.google.inject.Inject;
import hibernate.dao.StockDetailDao;
import hibernate.dao.StockDetailKeywordDao;
import hibernate.model.StockKeyword;
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
    private StockInfo stockInfo;

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
    public void parse(StockKeyword stockKeyword) {
        stockInfo = getStockInfo(stockKeyword);
        if(stockInfo instanceof EmptyStockInfo)
            return;

        int stockDetailId = generateStockDetail();
        if(stockDetailId==NOT_EXIST)
            return;

        logProcess();
        saveStockDetailKeyword(stockDetailId, stockKeyword.getId());
    }

    private StockInfo getStockInfo(StockKeyword stockKeyword) {
        Document pageHtml = pageReader.read(stockKeyword.getLink());
        return stockFetcher.fetch(pageHtml, stockKeyword.getId());
    }

    private int generateStockDetail() {
        return stockDetailDao.save(stockInfo);
    }

    private void logProcess() {
        logger.info(String.format("[%s | %s] updated", stockInfo.getStockName(), stockInfo.getStockCode()));
    }

    private int saveStockDetailKeyword(int stockDetailId, int stockKeywordId) {
        return stockDetailKeywordDao.save(stockDetailId, stockKeywordId);
    }




}
