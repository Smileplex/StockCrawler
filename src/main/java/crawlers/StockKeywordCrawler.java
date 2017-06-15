package crawlers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import hibernate.dao.KeywordLinkQueueDao;
import hibernate.dao.KeywordLinkQueueDaoImpl;
import hibernate.model.KeywordLinkQueue;
import models.ParsingResult;
import services.EmptyParsingResult;
import services.StockKeywordParser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by DongwooSeo on 2017-05-27.
 */
public class StockKeywordCrawler implements Crawler {
    private final Logger logger = Logger.getLogger(StockKeywordCrawler.class.getName());
    private final int PARSING_FINISHED = 3;
    private static KeywordLinkQueueDao keywordLinkQueueDao;
    private final StockKeywordParser stockKeywordParser;
    private KeywordLinkQueue keywordLinkQueue;
    private ParsingResult parsingResult;

    public StockKeywordCrawler(StockKeywordParser stockKeywordParser) {
        this.keywordLinkQueueDao = new KeywordLinkQueueDaoImpl();
        this.stockKeywordParser = stockKeywordParser;
    }
    @Override
    public void run() {
        while(true){
            processParsing();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processParsing() {
        keywordLinkQueue = getCrawlableLink();
        if(keywordLinkQueue==null) {
            return;
        }
        parsingResult = getParsingResult();
        if (parsingResult instanceof EmptyParsingResult)
            return;
        setCurrentLinkQueueStatusFinished();
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
        if(parsingResult.getLinks()==null ||
                parsingResult.getKeywordId()==0) return;

        keywordLinkQueueDao.saveAll(parsingResult, keywordLinkQueue);
    }
}
