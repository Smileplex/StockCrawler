package crawlers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import hibernate.dao.EmptyStockKeyword;
import hibernate.dao.StockKeywordDao;
import hibernate.dao.StockKeywordDaoImpl;
import hibernate.model.StockKeyword;
import services.StockDetailParser;

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by DongwooSeo on 2017-05-27.
 */
public class StockDetailCrawler implements Crawler{
    private final Logger logger = Logger.getLogger(StockDetailCrawler.class.getName());
    private final int PARSING_FINISHED = 3;
    private static StockKeywordDao stockKeywordDao;
    private final StockDetailParser stockDetailParser;
    private StockKeyword stockKeyword;

    public StockDetailCrawler(StockDetailParser stockDetailParser) {
        this.stockKeywordDao = new StockKeywordDaoImpl();
        this.stockDetailParser = stockDetailParser;
    }

    @Override
    public void run() {
        while(true){
            processParsing();
        }
    }

    public void processParsing() {
        stockKeyword = getCrawlableStockKeyword();
        if(stockKeyword instanceof EmptyStockKeyword)
            return;

        parseStockKeyword();
        setCurrentStockKeywordStatusFinished();
    }

    private static synchronized StockKeyword getCrawlableStockKeyword() {
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
