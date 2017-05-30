package services;

import com.google.inject.Inject;
import hibernate.dao.RelatedKeywordLinkDao;
import models.KeywordInfo;
import org.jsoup.nodes.Document;

/**
 * Created by DongwooSeo on 2017-05-29.
 */
public class StockKeywordParserImpl implements StockKeywordParser {
    public static final int STOCK_KEYWORD = 1;
    private final PageFetcher pageFetcher;
    private final StockKeywordGenerator stockKeywordGenerator;
    private final StockDetailsFactory stockDetailsFactory;
    private final StockFetcher stockFetcher;
    private final RelatedKeywordLinkDao relatedKeywordLinkDao;
    private KeywordInfo keywordInfo;
    private int stockKeywordId;

    @Inject
    public StockKeywordParserImpl(PageFetcher pageFetcher, StockKeywordGenerator stockKeywordGenerator,
                                  StockDetailsFactory stockDetailsFactory, StockFetcher stockFetcher,
                                  RelatedKeywordLinkDao relatedKeywordLinkDao) {
        this.pageFetcher = pageFetcher;
        this.stockKeywordGenerator = stockKeywordGenerator;
        this.stockDetailsFactory = stockDetailsFactory;
        this.stockFetcher = stockFetcher;
        this.relatedKeywordLinkDao = relatedKeywordLinkDao;
    }

    @Override
    public ParsingResult parse(String link, int agentId, int parentId) {
        PageParser pageParser = stockDetailsFactory.create(agentId);
        Document document = pageFetcher.fetchDocument(link);

        keywordInfo = pageParser.parse(document);
        stockKeywordId = stockKeywordGenerator.generate(keywordInfo.getKeywordName(), link, agentId, keywordInfo.getKeywordType());

        if (keywordInfo.getKeywordType() == STOCK_KEYWORD) {
            stockFetcher.parseStock(document, stockKeywordId);
        }
        relatedKeywordLinkDao.saveRelatedKeyword(parentId, stockKeywordId, 0);
        return new ParsingResult(keywordInfo.getRelatedKeywordLinks(), stockKeywordId);
    }

}
