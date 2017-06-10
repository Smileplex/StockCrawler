package services;

import hibernate.model.KeywordLinkQueue;
import models.KeywordInfo;

/**
 * Created by DongwooSeo on 2017-05-28.
 */
public interface StockKeywordGenerator {
    int generate(KeywordInfo keywordInfo, KeywordLinkQueue keywordLinkQueue);

}
