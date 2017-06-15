package crawlers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import hibernate.dao.StockKeywordDao;
import services.StockDetailParser;

/**
 * Created by DongwooSeo on 6/14/2017.
 */
public class StockDetailCrawlerProvider implements Provider<Crawler> {
    private final StockDetailParser stockDetailParser;

    @Inject
    public StockDetailCrawlerProvider(StockDetailParser stockDetailParser){
        this.stockDetailParser = stockDetailParser;
    }

    @Override
    public Crawler get() {
        return new StockDetailCrawler(stockDetailParser);
    }
}
