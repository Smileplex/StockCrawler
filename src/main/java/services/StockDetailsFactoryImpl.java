package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import parsers.NaverStockParser;

/**
 * Created by DongwooSeo on 2017-05-29.
 */
@Singleton
public class StockDetailsFactoryImpl implements StockDetailsFactory {
    public static final int NAVER = 1;

    @Override
    public PageParser create(int agentId) {
        if(agentId== NAVER) return new NaverStockParser();
        return null;
    }
}
