package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by DongwooSeo on 2017-05-31.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockInfo {
    public static final String CHART_WEEKLY_URL = "https://ssl.pstatic.net/imgfinance/chart/mobile/candle/week/%s_search.png?sidcode=1398073782866";
    public static final String CHART_DAILY_URL = "https://ssl.pstatic.net/imgfinance/chart/mobile/candle/day/%s_search.png?sidcode=1398073782866";
    public static final String CHART_MONTHLY_URL = "https://ssl.pstatic.net/imgfinance/chart/mobile/candle/month/%s_search.png?sidcode=1398073782866";
    private String stockName;
    private String stockCode;
    private int priceLast;
    private int prevClose;
    private int highVal;
    private int lowVal;
    private int priceChange;
    private double priceChangeRate;
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

    public int getPriceLast() {
        return priceLast;
    }

    public void setPriceLast(int priceLast) {
        this.priceLast = priceLast;
    }

    public int getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(int prevClose) {
        this.prevClose = prevClose;
    }

    public int getHighVal() {
        return highVal;
    }

    public void setHighVal(int highVal) {
        this.highVal = highVal;
    }

    public int getLowVal() {
        return lowVal;
    }

    public void setLowVal(int lowVal) {
        this.lowVal = lowVal;
    }

    public int getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(int priceChange) {
        this.priceChange = priceChange;
    }

    public double getPriceChangeRate() {
        return priceChangeRate;
    }

    public void setPriceChangeRate(double priceChangeRate) {
        this.priceChangeRate = priceChangeRate;
    }

    public String getChartDailyUrl() {
        return String.format(CHART_DAILY_URL,stockCode);
    }

    public String getChartWeeklyUrl() {
        return String.format(CHART_WEEKLY_URL,stockCode);
    }

    public String getChartMonthlyUrl() {
        return String.format(CHART_MONTHLY_URL,stockCode);
    }
}
