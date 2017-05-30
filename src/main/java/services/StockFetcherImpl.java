package services;

import hibernate.dao.StockDao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

/**
 * Created by DongwooSeo on 2017-05-28.
 */
public class StockFetcherImpl {
    private StockDao stockDao;

    public StockFetcherImpl(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    public void parseStock(Document document, int stockKeywordId) {
        String content = document.toString();
//        try {
//            String stockName = ParseCommon.getTarget(content, DataCommon.getValueBy("StockNameStarted", parsers),
//                    DataCommon.getValueBy("StockNameEnded", parsers));
//            String stockCode = ParseCommon.getTarget(content, DataCommon.getValueBy("StockCodeStarted", parsers),
//                    DataCommon.getValueBy("StockCodeEnded", parsers));
//            String url = String.format(DataCommon.getValueBy("StockUrlFormat", parsers), stockCode);
//
//            Document stockInfo = Jsoup.connect(url).timeout(agentConfig.getTimeout() * 1000)
//                    .userAgent(agentConfig.getUserAgent()).get();
//
//            int stockPrice = Integer.parseInt(
//                    ParseCommon.getTarget(stockInfo.toString(), DataCommon.getValueBy("StockPriceStarted", parsers),
//                            DataCommon.getValueBy("StockPriceEnded", parsers)));
//            int stockPricePrev = Integer.parseInt(
//                    ParseCommon.getTarget(stockInfo.toString(), DataCommon.getValueBy("StockPricePrevStarted", parsers),
//                            DataCommon.getValueBy("StockPricePrevEnded", parsers)));
//            int stockPriceMax = Integer.parseInt(
//                    ParseCommon.getTarget(stockInfo.toString(), DataCommon.getValueBy("StockPriceMaxStarted", parsers),
//                            DataCommon.getValueBy("StockPriceMaxEnded", parsers)));
//            int stockPriceMin = Integer.parseInt(
//                    ParseCommon.getTarget(stockInfo.toString(), DataCommon.getValueBy("StockPriceMinStarted", parsers),
//                            DataCommon.getValueBy("StockPriceMinEnded", parsers)));
//            int stockPriceFluct = Integer.parseInt(ParseCommon.getTarget(stockInfo.toString(),
//                    DataCommon.getValueBy("StockPriceFluctStarted", parsers),
//                    DataCommon.getValueBy("StockPriceFluctEnded", parsers)));
//            double stockPriceFluctRate = Double.parseDouble(ParseCommon.getTarget(stockInfo.toString(),
//                    DataCommon.getValueBy("StockPriceFluctRateStarted", parsers),
//                    DataCommon.getValueBy("StockPriceFluctRateEnded", parsers)));
//
//            String chartDailyUrl = String.format(DataCommon.getValueBy("StockChartDailyFormat", parsers), stockCode);
//            String chartWeeklyUrl = String.format(DataCommon.getValueBy("StockChartWeeklyFormat", parsers), stockCode);
//            String chartMonthlyUrl = String.format(DataCommon.getValueBy("StockChartMonthlyFormat", parsers),
//                    stockCode);
//
//            System.out.println(stockName + " | " + stockCode + " | " + stockPrice + " | " + stockPricePrev + " | "
//                    + stockPriceMax + " | " + stockPriceMin + " | " + stockPriceFluct + " | " + stockPriceFluctRate);
//            saveStock(stockKeywordId, stockName, stockCode, stockPrice, stockPricePrev, stockPriceMax, stockPriceMin, stockPriceFluct,
//                    stockPriceFluctRate, chartDailyUrl, chartWeeklyUrl, chartMonthlyUrl);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (NumberFormatException e){
//            //e.printStackTrace();
//            //System.out.println("거래정지 종목.");
//        }

    }

    private void saveStock(int keywordId, String name, String code, int price, int pricePrev, int priceMax, int priceMin,
                           int priceFluct, double priceFluctRate, String chartDailyUrl, String chartWeeklyUrl,
                           String chartMonthlyUrl) {
        stockDao.upsertStock(keywordId, name, code, price, pricePrev, priceMax, priceMin, priceFluct, priceFluctRate, chartDailyUrl, chartWeeklyUrl, chartMonthlyUrl);
    }
}
