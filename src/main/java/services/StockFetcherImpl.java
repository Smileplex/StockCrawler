package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import helper.Helper;
import hibernate.dao.StockDetailDao;
import models.EmptyStockInfo;
import models.StockInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by DongwooSeo on 2017-05-28.
 */
public class StockFetcherImpl implements StockFetcher{
    private static final Logger logger = Logger.getLogger(StockFetcherImpl.class.getName());
    public static final String STOCK_REQUEST_URL =
            "https://search.naver.com/p/n.search/finance/api/item/itemJson.nhn?_callback=window.__jindo2_callback._575&code=%s";
    private final ObjectMapper objectMapper;
    private final PageReader pageReader;

    @Inject
    public StockFetcherImpl(PageReader pageReader) {
        this.pageReader = pageReader;
        objectMapper = new ObjectMapper();
    }

    public StockInfo fetch(Document document, int keywordId) {
        String stockName = getStockName(document);
        String stockCode = getStockCode(document);
        try {
            Document rawResult = pageReader.read(String.format(STOCK_REQUEST_URL,stockCode));

            StockInfo stockInfo = objectMapper.readValue(getJsonStock(rawResult), StockInfo.class);
            stockInfo.setStockName(stockName);
            stockInfo.setStockCode(stockCode);
            return stockInfo;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            e.printStackTrace();
            logger.warning("거래정지 종목!");
        }
        return new EmptyStockInfo();
    }

    private String getJsonStock(Document rawResult) {
        return Helper.cutStringInRange(rawResult.toString(),"{\"result\":",",\"resultCode\"");
    }
    private String getStockName(Document document) {
        return Helper.cutStringInRange(document.toString(),"sItemName : \"","\"");
    }
    private String getStockCode(Document document) {
        return Helper.cutStringInRange(document.toString(), "sItemCode : \"", "\"");
    }
}
