package controllers;

import app.AppSettings;
import com.google.inject.Provider;
import crawlers.Crawler;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DongwooSeo on 2017-05-27.
 */
public class CrawlerController {
    private final Provider<Crawler> provider;

    @Inject
    public CrawlerController(Provider<Crawler> provider) {
        this.provider = provider;
    }

    public void start(){
        List<Thread> threads = new ArrayList<>();
        for(int i=1; i<=AppSettings.CRAWLER_THREADS; i++){
           Crawler crawler = provider.get();
            System.out.println(crawler);
           Thread thread =  new Thread(crawler, "Crawler-" +i);
           thread.start();
           threads.add(thread);
        }
    }
}
