package services;

import com.google.inject.Inject;
import parsers.NaverStockParser;

/**
 * Created by DongwooSeo on 2017-05-29.
 */
public class StockDetailsFactoryImpl implements StockDetailsFactory {
    public static final int NAVER = 1;
    private final StockFetcher stockFetcher;

    @Inject
    public StockDetailsFactoryImpl(StockFetcher stockFetcher) {
        this.stockFetcher = stockFetcher;
    }

    @Override
    public PageParser create(int agentId) {
        if(agentId== NAVER) return new NaverStockParser(stockFetcher);
        return null;
    }
}
