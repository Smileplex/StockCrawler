package services;

import models.ParsingResult;

/**
 * Created by DongwooSeo on 2017-05-29.
 */
public interface StockKeywordParser {
    ParsingResult parse(String link, int agentId, int parentId);
}
