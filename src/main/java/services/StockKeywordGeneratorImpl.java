package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import hibernate.dao.KeywordDao;
import hibernate.dao.KeywordMainDao;
import hibernate.dao.StockKeywordDao;
import hibernate.dao.StockKeywordDaoImpl;

/**
 * Created by DongwooSeo on 2017-05-28.
 */
@Singleton
public class StockKeywordGeneratorImpl implements StockKeywordGenerator {

    private final StockKeywordDao stockKeywordDao;
    private final KeywordMainDao keywordMainDao;

    @Inject
    public StockKeywordGeneratorImpl(StockKeywordDao stockKeywordDao, KeywordMainDao keywordMainDao) {
        this.stockKeywordDao = stockKeywordDao;
        this.keywordMainDao = keywordMainDao;
    }

    @Override
    public int generate(String keywordName, String link, int agentId, int typeId) {
        int keywordMainId = keywordMainDao.saveKeywordMain(keywordName);
        if (keywordMainId == 0) {
            return 0;
        }
        int stockKeywordId = stockKeywordDao.saveKeyword(keywordName, link, keywordMainId, agentId, typeId);
        return stockKeywordId;
    }
}