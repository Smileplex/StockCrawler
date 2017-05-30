package services;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by DongwooSeo on 2017-05-28.
 */
public interface PageFetcher {
    Document fetchDocument(String link);
}
