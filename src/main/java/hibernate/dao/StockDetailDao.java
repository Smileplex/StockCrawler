package hibernate.dao;

import hibernate.model.StockDetail;
import models.StockInfo;

public interface StockDetailDao {
    int save(StockInfo stockInfo);
}
