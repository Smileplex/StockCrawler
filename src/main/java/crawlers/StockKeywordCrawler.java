package crawlers;

import hibernate.dao.KeywordLinkQueueDao;
import hibernate.dao.KeywordLinkQueueDaoImpl;
import hibernate.model.KeywordLinkQueue;
import models.ParsingResult;
import services.EmptyParsingResult;
import services.StockKeywordParser;

import java.util.logging.Logger;

/**
 * Created by DongwooSeo on 2017-05-27.
 */
public class StockKeywordCrawler implements Crawler {
    private static KeywordLinkQueueDao keywordLinkQueueDao;
    private final Logger logger = Logger.getLogger(StockKeywordCrawler.class.getName());
    private final int PARSING_FINISHED = 3;
    private final StockKeywordParser stockKeywordParser;
    private KeywordLinkQueue keywordLinkQueue;
    private ParsingResult parsingResult;

    public StockKeywordCrawler(StockKeywordParser stockKeywordParser) {
        this.keywordLinkQueueDao = new KeywordLinkQueueDaoImpl();
        this.stockKeywordParser = stockKeywordParser;
    }

    @Override
    public void run() {
        while (true) {
            processParsing();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processParsing() {
        keywordLinkQueue = getCrawlableLink();
        if (keywordLinkQueue == null) {
            return;
        }
        parsingResult = getParsingResult();
        setCurrentLinkQueueStatusFinished();

        if (parsingResult instanceof EmptyParsingResult)
            return;

        saveNewKeywordLinkQueues();
    }

    private static synchronized KeywordLinkQueue getCrawlableLink() {
        return keywordLinkQueueDao.fetchFirstRow();
    }

    private ParsingResult getParsingResult() {
        return stockKeywordParser.parse(keywordLinkQueue);
    }

    private void setCurrentLinkQueueStatusFinished() {
        keywordLinkQueue.setStatus(PARSING_FINISHED);
        keywordLinkQueueDao.update(keywordLinkQueue);
    }

    private void saveNewKeywordLinkQueues() {
        if (isInvalidParsingResult()) return;
        keywordLinkQueueDao.saveAll(parsingResult, keywordLinkQueue);
    }

    private boolean isInvalidParsingResult() {
        return parsingResult.getLinks() == null ||
                parsingResult.getKeywordId() == 0;
    }
}
