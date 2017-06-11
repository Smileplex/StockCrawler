package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import hibernate.dao.RelatedKeywordLinkDao;
import hibernate.model.KeywordLinkQueue;
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
    private KeywordInfo keywordInfo;
    private KeywordLinkQueue keywordLinkQueue;

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
    public ParsingResult processParsing(KeywordLinkQueue keywordLinkQueue) {
        this.keywordLinkQueue = keywordLinkQueue;
        this.keywordInfo = parseKeywordInfo();
        if (keywordInfo instanceof EmptyKeywordInfo)
            return new EmptyParsingResult();

        int stockKeywordId = getGeneratedKeywordId();
        saveRelatedKeywordLink(stockKeywordId);

        logStatus(keywordInfo, stockKeywordId);
        return new ParsingResult(keywordInfo.getRelatedKeywordLinks(), stockKeywordId);
    }

    private KeywordInfo parseKeywordInfo() {
        Document pageHtml = pageReader.read(keywordLinkQueue.getLink());
        PageParser pageParser = stockDetailsFactory.create(keywordLinkQueue.getAgentId());
        return pageParser.processParsing(pageHtml);
    }

    private int getGeneratedKeywordId() {
        return stockKeywordGenerator.generate(keywordInfo, keywordLinkQueue);
    }

    private void saveRelatedKeywordLink(int stockKeywordId) {
        relatedKeywordLinkDao.save(keywordLinkQueue.getParentId(), stockKeywordId, 0);
    }

    private void logStatus(KeywordInfo keywordInfo, int stockKeywordId) {
        logger.info(String.format("%s [Type : %s] [StockKeywordId : %d]",
                keywordInfo.getKeywordName(), keywordInfo.getKeywordType(), stockKeywordId));
    }
}
