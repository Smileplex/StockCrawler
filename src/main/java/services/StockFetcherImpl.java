package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import helper.Helper;
import hibernate.dao.StockDetailDao;
import models.StockInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by DongwooSeo on 2017-05-28.
 */
@Singleton
public class StockFetcherImpl implements StockFetcher{
    public static final String STOCK_REQUEST_URL = "https://search.naver.com/p/n.search/finance/api/item/itemJson.nhn?_callback=window.__jindo2_callback._575&code=%s";
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
    public static final int TIMEOUT = 30 * 1000;
    private final ObjectMapper objectMapper;

    @Inject
    public StockFetcherImpl() {
        objectMapper = new ObjectMapper();
    }

    public StockInfo fetch(Document document, int keywordId) {
        String stockName = Helper.cutStringInRange(document.toString(),"sItemName : \"","\"");
        String stockCode = Helper.cutStringInRange(document.toString(), "sItemCode : \"", "\"");
        try {
            Document rawResult = Jsoup.connect(String.format(STOCK_REQUEST_URL,stockCode)).timeout(TIMEOUT).userAgent(USER_AGENT).get();
            String jsonStock = Helper.cutStringInRange(rawResult.toString(),"{\"result\":",",\"resultCode\"");
            StockInfo stockInfo = objectMapper.readValue(jsonStock, StockInfo.class);
            stockInfo.setStockName(stockName);
            stockInfo.setStockCode(stockCode);
            return stockInfo;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            e.printStackTrace();
            System.out.println("거래정지 종목.");
        }
        return null;
    }
}
