package hibernate.dao;


import hibernate.model.RelatedKeywordLink;

import java.util.List;

public interface RelatedKeywordLinkDao {
	void save(int keywordId, int relatedId, int correl);
}
