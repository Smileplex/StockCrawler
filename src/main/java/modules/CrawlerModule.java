package modules;

import com.google.inject.AbstractModule;
import crawlers.BasicStockCrawler;
import crawlers.Crawler;
import hibernate.dao.KeywordLinkQueueDao;
import hibernate.dao.KeywordLinkQueueDaoImpl;

/**
 * Created by DongwooSeo on 2017-05-27.
 */
public class CrawlerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Crawler.class).to(BasicStockCrawler.class);
        bind(KeywordLinkQueueDao.class).to(KeywordLinkQueueDaoImpl.class);
    }
}
