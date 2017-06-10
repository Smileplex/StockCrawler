package services;

import models.KeywordInfo;
import org.jsoup.nodes.Document;

/**
 * Created by DongwooSeo on 2017-05-29.
 */
public interface PageParser {
    KeywordInfo processParsing(Document document);

}
