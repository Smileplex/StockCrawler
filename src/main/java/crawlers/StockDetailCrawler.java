package crawlers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import hibernate.dao.StockKeywordDao;
import hibernate.model.StockKeyword;
import services.StockDetailParser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by DongwooSeo on 2017-05-27.
 */
@Singleton
public class StockDetailCrawler implements Crawler {
    private final Logger logger = Logger.getLogger(StockDetailCrawler.class.getName());
    private final int PARSING_FINISHED = 3;
    private final StockKeywordDao stockKeywordDao;
    private final StockDetailParser stockDetailParser;
    private StockKeyword stockKeyword;

    @Inject
    public StockDetailCrawler(StockKeywordDao stockKeywordDao, StockDetailParser stockDetailParser) {
        this.stockKeywordDao = stockKeywordDao;
        this.stockDetailParser = stockDetailParser;
    }

    @Override
    public void execute(int numberOfCrawler) {
        final List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfCrawler; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    processParsing();
                }
            });
            thread.start();
            threads.add(thread);
            logger.info(String.format("Crawler %d started", i + 1));
        }
    }

    private void processParsing() {
        stockKeyword = getCrawlableStockKeyword();
        parseStockKeyword();
        setCurrentStockKeywordStatusFinished();
    }

    private StockKeyword getCrawlableStockKeyword() {
        return stockKeywordDao.fetchFirstRow();
    }

    private void parseStockKeyword() {
        stockDetailParser.parse(stockKeyword);
    }

    private void setCurrentStockKeywordStatusFinished() {
        stockKeyword.setStatus(PARSING_FINISHED);
        stockKeyword.setDateUpdated(new Timestamp(new Date().getTime()));
        stockKeywordDao.update(stockKeyword);
    }

}
