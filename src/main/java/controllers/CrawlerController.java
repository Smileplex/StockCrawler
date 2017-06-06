package controllers;

import app.AppSettings;
import hibernate.model.KeywordLinkQueue;
import crawlers.Crawler;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by DongwooSeo on 2017-05-27.
 */
public class CrawlerController {
    private final Crawler crawler;

    @Inject
    public CrawlerController(Crawler crawler) {
        this.crawler = crawler;
    }

    public void start(){
        crawler.execute(AppSettings.CRAWLER_THREADS);
    }
}
