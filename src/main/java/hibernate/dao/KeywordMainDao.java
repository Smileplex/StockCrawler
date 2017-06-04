package hibernate.dao;


import hibernate.model.KeywordMain;

public interface KeywordMainDao {
	KeywordMain findByName(String keywordName);
	int saveKeywordMain(String name, String link);
	int save(KeywordMain keywordMain);
	void update(KeywordMain keywordMain);
}
