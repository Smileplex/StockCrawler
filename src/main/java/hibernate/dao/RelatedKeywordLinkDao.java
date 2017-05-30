package hibernate.dao;


import hibernate.model.RelatedKeywordLink;

import java.util.List;

public interface RelatedKeywordLinkDao {
	RelatedKeywordLink findByKeywordId(int keywordId);
	RelatedKeywordLink findByKeywordAndRelatedId(int keywordId, int relatedId);
	void save(RelatedKeywordLink relatedKeywordLink);
	void saveAll(List<RelatedKeywordLink> relatedKeywordLinks);
	void saveRelatedKeyword(int keywordId, int relatedId, int correl);
}
