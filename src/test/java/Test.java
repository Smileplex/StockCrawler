import com.fasterxml.jackson.databind.ObjectMapper;
import helper.Helper;
import models.StockInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Test{
    public static void main(String[] args){
        try {
            Document result = Jsoup.connect("https://search.naver.com/p/n.search/finance/api/item/itemJson.nhn?_callback=window.__jindo2_callback._575&code=034220")
                    .timeout(30 * 1000).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36").get();
            String stockJson = Helper.cutStringInRange(result.toString(),"{\"result\":",",\"resultCode\"");
            ObjectMapper mapper =   new ObjectMapper();
            StockInfo stockInfo = mapper.readValue(stockJson, StockInfo.class);
            System.out.println(stockInfo.getChartDailyUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}