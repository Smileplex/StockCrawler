package hibernate.dao;

/**
 * Created by DongwooSeo on 6/4/2017.
 */
public interface StockDetailKeywordDao {
    int finyByStockKeywordId(int stockKeywordId);

    int save(int stockDetailId, int stockKeywordId);
}
