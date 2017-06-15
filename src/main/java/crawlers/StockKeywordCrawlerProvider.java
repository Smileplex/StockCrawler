package crawlers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import crawlers.Crawler;
import crawlers.StockKeywordCrawler;
import hibernate.dao.KeywordLinkQueueDao;
import hibernate.dao.StockKeywordDao;
import services.StockKeywordParser;

/**
 * Created by DongwooSeo on 6/14/2017.
 */
public class StockKeywordCrawlerProvider implements Provider<Crawler> {
    private final StockKeywordParser stockKeywordParser;

    @Inject
    public StockKeywordCrawlerProvider(KeywordLinkQueueDao keywordLinkQueueDao,
                                       StockKeywordParser stockKeywordParser) {
        this.stockKeywordParser = stockKeywordParser;
    }

    @Override
    public Crawler get() {
        return new StockKeywordCrawler(stockKeywordParser);
    }
}
