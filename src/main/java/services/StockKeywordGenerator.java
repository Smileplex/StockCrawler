package services;

/**
 * Created by DongwooSeo on 2017-05-28.
 */
public interface StockKeywordGenerator {
    int generate(String keywordName, String link, int agentId, int typeId);

}
