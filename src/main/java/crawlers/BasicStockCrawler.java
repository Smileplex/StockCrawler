package crawlers;

import com.google.inject.Inject;
import hibernate.dao.KeywordLinkQueueDao;
import hibernate.model.KeywordLinkQueue;
import models.ParsingResult;
import services.StockKeywordParser;

import java.util.List;

/**
 * Created by DongwooSeo on 2017-05-27.
 */
public class BasicStockCrawler implements Crawler {

    private final KeywordLinkQueueDao keywordLinkQueueDao;
    private final StockKeywordParser stockKeywordParser;

    @Inject
    public BasicStockCrawler(KeywordLinkQueueDao keywordLinkQueueDao,
                             StockKeywordParser stockKeywordParser) {
        this.keywordLinkQueueDao = keywordLinkQueueDao;
        this.stockKeywordParser = stockKeywordParser;
    }

    @Override
    public void execute() {
        List<KeywordLinkQueue> keywordLinkQueues = getCrawlableLinks(20);

        for (KeywordLinkQueue keywordLinkQueue : keywordLinkQueues) {
            ParsingResult parsingResult =
                    stockKeywordParser.parse(keywordLinkQueue.getLink(), keywordLinkQueue.getAgentId(), keywordLinkQueue.getParentId());

            keywordLinkQueue.setStatus(3);
            keywordLinkQueueDao.update(keywordLinkQueue);

            saveKeywordLinkQueues(parsingResult.getLinks(), parsingResult.getKeywordId(),
                    keywordLinkQueue.getAgentId());
        }
    }

    private List<KeywordLinkQueue> getCrawlableLinks(int max) {
        return keywordLinkQueueDao.fetchMultipleRows(max);
    }

    private void saveKeywordLinkQueues(List<String> links, int keywordId, int agentId) {
        for (String link : links) {
            keywordLinkQueueDao.saveIfNotExist(link, agentId, keywordId);
        }
    }
}
