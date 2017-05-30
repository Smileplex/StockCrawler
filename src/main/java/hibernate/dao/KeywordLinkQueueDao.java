package hibernate.dao;


import hibernate.model.KeywordLinkQueue;

import java.util.List;

public interface KeywordLinkQueueDao {
	KeywordLinkQueue findByLink(String link);
	KeywordLinkQueue fetchFirstRow();
	List<KeywordLinkQueue> fetchMultipleRows(int limit);
	void save(KeywordLinkQueue entity);
	void saveAll(List<KeywordLinkQueue> keywordLinkQueues);
	void saveIfNotExist(String link, int agentId, int parentId);
	void update(KeywordLinkQueue keywordLinkQueue);

}
