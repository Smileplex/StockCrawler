package hibernate.dao;


import hibernate.model.StockKeyword;

public interface StockKeywordDao {
	StockKeyword fetchFirstRow();
	void update(StockKeyword entity);
	int save(String keywordName, String link, int keywordMainId, int agentId, int typeId);
	boolean checkIfExist(String keywordName);
}
