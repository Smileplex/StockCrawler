package services;

import hibernate.model.KeywordLinkQueue;
import models.ParsingResult;

/**
 * Created by DongwooSeo on 2017-05-29.
 */
public interface StockKeywordParser {
    ParsingResult processParsing(KeywordLinkQueue keywordLinkQueue);
}
