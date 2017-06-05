package crawlers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import hibernate.dao.KeywordLinkQueueDao;
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
@Singleton
public class StockKeywordCrawler implements Crawler {
    private static final Logger logger = Logger.getLogger(StockKeywordCrawler.class.getName());
    private static final int PARSING_FINISHED = 3;
    private final KeywordLinkQueueDao keywordLinkQueueDao;
    private final StockKeywordParser stockKeywordParser;

    @Inject
    public StockKeywordCrawler(KeywordLinkQueueDao keywordLinkQueueDao,
                               StockKeywordParser stockKeywordParser) {
        this.keywordLinkQueueDao = keywordLinkQueueDao;
        this.stockKeywordParser = stockKeywordParser;
    }

    @Override
    public void execute(int numberOfCrawler) {
        final List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfCrawler; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    KeywordLinkQueue keywordLinkQueue = getCrawlableLink();
                    ParsingResult parsingResult =
                            stockKeywordParser.parse(keywordLinkQueue.getLink(), keywordLinkQueue.getAgentId(), keywordLinkQueue.getParentId());
                    if (parsingResult instanceof EmptyParsingResult) {
                        continue;
                    }

                    keywordLinkQueue.setStatus(PARSING_FINISHED);
                    keywordLinkQueueDao.update(keywordLinkQueue);
                    keywordLinkQueueDao.saveAll(parsingResult.getLinks(), parsingResult.getKeywordId(),
                            keywordLinkQueue.getAgentId());
                }
            });
            thread.start();
            threads.add(thread);
            logger.info(String.format("Crawler %d started", i+1));
        }
    }

    private KeywordLinkQueue getCrawlableLink() {
        return keywordLinkQueueDao.fetchFirstRow();
    }
}
