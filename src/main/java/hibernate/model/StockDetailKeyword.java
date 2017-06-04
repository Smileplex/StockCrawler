package hibernate.model;

/**
 * Created by DongwooSeo on 6/4/2017.
 */
public class StockDetailKeyword {
    private int id;
    private int stockDetailId;
    private int stockKeywordId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStockDetailId() {
        return stockDetailId;
    }

    public void setStockDetailId(int stockDetailId) {
        this.stockDetailId = stockDetailId;
    }

    public int getStockKeywordId() {
        return stockKeywordId;
    }

    public void setStockKeywordId(int stockKeywordId) {
        this.stockKeywordId = stockKeywordId;
    }
}
