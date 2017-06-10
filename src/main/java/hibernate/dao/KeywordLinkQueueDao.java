package hibernate.dao;


import hibernate.model.KeywordLinkQueue;
import models.ParsingResult;

import java.util.List;

public interface KeywordLinkQueueDao {
	KeywordLinkQueue findByLink(String link);
	KeywordLinkQueue fetchFirstRow();
	List<KeywordLinkQueue> fetchMultipleRows(int limit);
	void save(KeywordLinkQueue entity);
	void update(KeywordLinkQueue keywordLinkQueue);
	void saveAll(ParsingResult parsingResult, KeywordLinkQueue keywordLinkQueue);
}
