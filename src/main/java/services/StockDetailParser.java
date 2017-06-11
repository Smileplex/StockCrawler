package services;

import hibernate.model.StockKeyword;

/**
 * Created by DongwooSeo on 6/4/2017.
 */

public interface StockDetailParser {
    void parse(StockKeyword stockKeyword);
}
