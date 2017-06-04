package modules;

import com.google.inject.AbstractModule;
import crawlers.BasicStockCrawler;
import crawlers.Crawler;
import hibernate.dao.*;
import services.*;

/**
 * Created by DongwooSeo on 2017-05-27.
 */
public class CrawlerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Crawler.class).to(BasicStockCrawler.class);
        bind(KeywordMainDao.class).to(KeywordMainDaoImpl.class);
        bind(StockDao.class).to(StockDaoImpl.class);
        bind(StockKeywordDao.class).to(StockKeywordDaoImpl.class);
        bind(KeywordLinkQueueDao.class).to(KeywordLinkQueueDaoImpl.class);
        bind(RelatedKeywordLinkDao.class).to(RelatedKeywordLinkDaoImpl.class);
        bind(PageFetcher.class).to(PageFetcherImpl.class);
        bind(StockFetcher.class).to(StockFetcherImpl.class);
        bind(StockKeywordParser.class).to(StockKeywordParserImpl.class);
        bind(StockDetailsFactory.class).to(StockDetailsFactoryImpl.class);
        bind(StockKeywordGenerator.class).to(StockKeywordGeneratorImpl.class);
    }
}
