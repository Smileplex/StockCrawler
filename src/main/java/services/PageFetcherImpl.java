package services;

import com.google.inject.Singleton;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by DongwooSeo on 2017-05-28.
 */
@Singleton
public class PageFetcherImpl implements PageFetcher {
    public Document fetch(String link) {
        Connection connection = Jsoup.connect(link).timeout(30000)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        try {
            return connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
