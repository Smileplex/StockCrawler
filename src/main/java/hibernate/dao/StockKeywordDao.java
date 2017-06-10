package hibernate.dao;


import hibernate.model.KeywordLinkQueue;
import hibernate.model.StockKeyword;
import models.KeywordInfo;

public interface StockKeywordDao {
	StockKeyword fetchFirstRow();
	void update(StockKeyword entity);
	int save(KeywordInfo keywordInfo, KeywordLinkQueue keywordLinkQueue, int keywordMainId);
}
