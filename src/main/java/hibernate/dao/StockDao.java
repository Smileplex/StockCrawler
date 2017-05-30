package hibernate.dao;

public interface StockDao {
	void upsertStock(int keywordId, String name, String code, int price, int pricePrev, int priceMax, int priceMin,
                     int priceFluct, double priceFluctRate, String chartDailyUrl, String chartWeeklyUrl, String chartMonthlyUrl);

}
