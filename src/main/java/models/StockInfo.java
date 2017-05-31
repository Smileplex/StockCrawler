package models;

/**
 * Created by DongwooSeo on 2017-05-31.
 */
public class StockInfo {
    private String stockName;
    private String stockCode;
    private int stockPrice;
    private int stockPricePrev;
    private int stockPriceMax;
    private int stockPriceMin;
    private int stockPriceFluct;
    private double stockPriceFluctRate;
    private String chartDailyUrl;
    private String chartWeeklyUrl;
    private String chartMonthlyUrl;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public int getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(int stockPrice) {
        this.stockPrice = stockPrice;
    }

    public int getStockPricePrev() {
        return stockPricePrev;
    }

    public void setStockPricePrev(int stockPricePrev) {
        this.stockPricePrev = stockPricePrev;
    }

    public int getStockPriceMax() {
        return stockPriceMax;
    }

    public void setStockPriceMax(int stockPriceMax) {
        this.stockPriceMax = stockPriceMax;
    }

    public int getStockPriceMin() {
        return stockPriceMin;
    }

    public void setStockPriceMin(int stockPriceMin) {
        this.stockPriceMin = stockPriceMin;
    }

    public int getStockPriceFluct() {
        return stockPriceFluct;
    }

    public void setStockPriceFluct(int stockPriceFluct) {
        this.stockPriceFluct = stockPriceFluct;
    }

    public String getChartDailyUrl() {
        return chartDailyUrl;
    }

    public void setChartDailyUrl(String chartDailyUrl) {
        this.chartDailyUrl = chartDailyUrl;
    }

    public String getChartWeeklyUrl() {
        return chartWeeklyUrl;
    }

    public void setChartWeeklyUrl(String chartWeeklyUrl) {
        this.chartWeeklyUrl = chartWeeklyUrl;
    }

    public String getChartMonthlyUrl() {
        return chartMonthlyUrl;
    }

    public void setChartMonthlyUrl(String chartMonthlyUrl) {
        this.chartMonthlyUrl = chartMonthlyUrl;
    }

    public double getStockPriceFluctRate() {
        return stockPriceFluctRate;
    }

    public void setStockPriceFluctRate(double stockPriceFluctRate) {
        this.stockPriceFluctRate = stockPriceFluctRate;
    }

    @Override
    public String toString() {
        return "StockInfo{" +
                "stockName='" + stockName + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", stockPrice=" + stockPrice +
                ", stockPricePrev=" + stockPricePrev +
                ", stockPriceMax=" + stockPriceMax +
                ", stockPriceMin=" + stockPriceMin +
                ", stockPriceFluct=" + stockPriceFluct +
                ", stockPriceFluctRate=" + stockPriceFluctRate +
                ", chartDailyUrl='" + chartDailyUrl + '\'' +
                ", chartWeeklyUrl='" + chartWeeklyUrl + '\'' +
                ", chartMonthlyUrl='" + chartMonthlyUrl + '\'' +
                '}';
    }
}
