package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import hibernate.dao.KeywordMainDao;
import hibernate.dao.StockKeywordDao;
import hibernate.model.KeywordLinkQueue;
import models.KeywordInfo;

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
    public int generate(KeywordInfo keywordInfo, KeywordLinkQueue keywordLinkQueue) {
        int keywordMainId = keywordMainDao.saveKeywordMain(keywordInfo.getKeywordName(),
                keywordLinkQueue.getLink());
        if (keywordMainId == 0) {
            return 0;
        }
        int stockKeywordId = stockKeywordDao.save(keywordInfo, keywordLinkQueue, keywordMainId);
        return stockKeywordId;
    }
}
