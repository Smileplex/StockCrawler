package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import hibernate.dao.RelatedKeywordLinkDao;
import models.KeywordInfo;
import models.ParsingResult;
import org.jsoup.nodes.Document;

/**
 * Created by DongwooSeo on 2017-05-29.
 */
@Singleton
public class StockKeywordParserImpl implements StockKeywordParser {
    public static final int STOCK_KEYWORD = 1;
    private final PageFetcher pageFetcher;
    private final StockKeywordGenerator stockKeywordGenerator;
    private final StockDetailsFactory stockDetailsFactory;
    private final RelatedKeywordLinkDao relatedKeywordLinkDao;

    @Inject
    public StockKeywordParserImpl(PageFetcher pageFetcher, StockKeywordGenerator stockKeywordGenerator,
                                  StockDetailsFactory stockDetailsFactory,
                                  RelatedKeywordLinkDao relatedKeywordLinkDao) {
        this.pageFetcher = pageFetcher;
        this.stockKeywordGenerator = stockKeywordGenerator;
        this.stockDetailsFactory = stockDetailsFactory;
        this.relatedKeywordLinkDao = relatedKeywordLinkDao;
    }

    @Override
    public ParsingResult parse(String link, int agentId, int parentId) {
        PageParser pageParser = stockDetailsFactory.create(agentId);
        Document pageHtml = pageFetcher.fetch(link);

        KeywordInfo keywordInfo = pageParser.parse(pageHtml);
        if (keywordInfo == null)
            return null;

        int stockKeywordId = stockKeywordGenerator.generate(keywordInfo.getKeywordName(), link, agentId, keywordInfo.getKeywordType());
        System.out.println(String.format("%s parsed [type : %s]", keywordInfo.getKeywordName(), keywordInfo.getKeywordType()));

        relatedKeywordLinkDao.saveRelatedKeyword(parentId, stockKeywordId, 0);
        return new ParsingResult(keywordInfo.getRelatedKeywordLinks(), stockKeywordId);
    }

}
