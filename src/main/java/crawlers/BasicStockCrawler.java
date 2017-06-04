package crawlers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import hibernate.dao.KeywordLinkQueueDao;
import hibernate.model.KeywordLinkQueue;
import models.ParsingResult;
import services.StockKeywordParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DongwooSeo on 2017-05-27.
 */
@Singleton
public class BasicStockCrawler implements Crawler {

    public static final int FINISHED = 3;
    private final KeywordLinkQueueDao keywordLinkQueueDao;
    private final StockKeywordParser stockKeywordParser;

    @Inject
    public BasicStockCrawler(KeywordLinkQueueDao keywordLinkQueueDao,
                             StockKeywordParser stockKeywordParser) {
        this.keywordLinkQueueDao = keywordLinkQueueDao;
        this.stockKeywordParser = stockKeywordParser;
    }

    @Override
    public void execute(int numberOfCrawler) {
        final List<Thread> threads = new ArrayList<>();
        for(int i=0; i<numberOfCrawler; i++){
            Thread thread = new Thread(() -> {
                while (true) {
                    KeywordLinkQueue keywordLinkQueue = getCrawlableLink();
                    ParsingResult parsingResult =
                            stockKeywordParser.parse(keywordLinkQueue.getLink(), keywordLinkQueue.getAgentId(), keywordLinkQueue.getParentId());
                    if (parsingResult == null) {
                        continue;
                    }
                    keywordLinkQueue.setStatus(FINISHED);
                    keywordLinkQueueDao.update(keywordLinkQueue);

                    saveKeywordLinkQueues(parsingResult.getLinks(), parsingResult.getKeywordId(),
                            keywordLinkQueue.getAgentId());
                }
            });
            thread.start();
            System.out.println(String.format("Crawler %d started", i+1));
            threads.add(thread);
        }
    }

    private KeywordLinkQueue getCrawlableLink() {
        return keywordLinkQueueDao.fetchFirstRow();
    }

    private void saveKeywordLinkQueues(List<String> links, int keywordId, int agentId) {
        keywordLinkQueueDao.saveAll(links, agentId, keywordId);
    }
}
