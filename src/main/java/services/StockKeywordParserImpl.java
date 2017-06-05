package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import hibernate.dao.RelatedKeywordLinkDao;
import models.EmptyStockInfo;
import models.KeywordInfo;
import models.ParsingResult;
import org.jsoup.nodes.Document;
import parsers.EmptyKeywordInfo;

import java.util.logging.Logger;

/**
 * Created by DongwooSeo on 2017-05-29.
 */
@Singleton
public class StockKeywordParserImpl implements StockKeywordParser {
    private static final Logger logger = Logger.getLogger(StockDetailParserImpl.class.getName());
    private final PageReader pageReader;
    private final StockKeywordGenerator stockKeywordGenerator;
    private final StockDetailsFactory stockDetailsFactory;
    private final RelatedKeywordLinkDao relatedKeywordLinkDao;

    @Inject
    public StockKeywordParserImpl(PageReader pageReader, StockKeywordGenerator stockKeywordGenerator,
                                  StockDetailsFactory stockDetailsFactory,
                                  RelatedKeywordLinkDao relatedKeywordLinkDao) {
        this.pageReader = pageReader;
        this.stockKeywordGenerator = stockKeywordGenerator;
        this.stockDetailsFactory = stockDetailsFactory;
        this.relatedKeywordLinkDao = relatedKeywordLinkDao;
    }

    @Override
    public ParsingResult parse(String link, int agentId, int parentId) {
        PageParser pageParser = stockDetailsFactory.create(agentId);
        Document pageHtml = pageReader.read(link);
        KeywordInfo keywordInfo = pageParser.parse(pageHtml);

        if (keywordInfo instanceof EmptyKeywordInfo)
            return new EmptyParsingResult();

        int stockKeywordId = stockKeywordGenerator.generate(keywordInfo.getKeywordName(), link, agentId, keywordInfo.getKeywordType());
        relatedKeywordLinkDao.save(parentId, stockKeywordId, 0);

        logger.info(String.format("%s [Type : %s] [StockKeywordId : %d]",
                keywordInfo.getKeywordName(), keywordInfo.getKeywordType(),stockKeywordId));
        return new ParsingResult(keywordInfo.getRelatedKeywordLinks(), stockKeywordId);
    }

}
