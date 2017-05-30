package app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import controllers.CrawlerController;
import hibernate.dao.KeywordLinkQueueDao;
import hibernate.dao.KeywordLinkQueueDaoImpl;
import modules.CrawlerModule;

/**
 * Created by DongwooSeo on 2017-05-27.
 */
public class AppMain {
    public static void main(String[] args){
        Injector injector = Guice.createInjector(new CrawlerModule());
        CrawlerController crawlerController = injector.getInstance(CrawlerController.class);
        crawlerController.start();
    }
}
