package modules;

import com.google.inject.AbstractModule;
import crawlers.*;
import hibernate.dao.*;
import services.*;

/**
 * Created by DongwooSeo on 2017-05-27.
 */
public class CrawlerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Crawler.class).toProvider(StockDetailCrawlerProvider.class);
        bind(KeywordMainDao.class).to(KeywordMainDaoImpl.class);
        bind(StockDetailDao.class).to(StockDetailDaoImpl.class);
        bind(StockKeywordDao.class).to(StockKeywordDaoImpl.class);
        bind(StockDetailKeywordDao.class).to(StockDetailKeywordDaoImpl.class);
        bind(KeywordLinkQueueDao.class).to(KeywordLinkQueueDaoImpl.class);
        bind(RelatedKeywordLinkDao.class).to(RelatedKeywordLinkDaoImpl.class);
        bind(PageReader.class).to(PageReaderImpl.class);
        bind(StockFetcher.class).to(StockFetcherImpl.class);
        bind(StockKeywordParser.class).to(StockKeywordParserImpl.class);
        bind(StockDetailParser.class).to(StockDetailParserImpl.class);
        bind(StockDetailsFactory.class).to(StockDetailsFactoryImpl.class);
        bind(StockKeywordGenerator.class).to(StockKeywordGeneratorImpl.class);
    }
}
