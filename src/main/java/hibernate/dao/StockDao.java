package hibernate.dao;

import models.StockInfo;

public interface StockDao {
    void upsertStock(int keywordId, StockInfo stockInfo);
}
