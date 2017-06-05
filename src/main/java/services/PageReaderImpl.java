package services;

import app.AppSettings;
import com.google.inject.Singleton;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.omg.CORBA.TIMEOUT;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DongwooSeo on 2017-05-28.
 */
@Singleton
public class PageReaderImpl implements PageReader {
    public Document read(String link) {
        Map<String,String> headers = new HashMap<>();
        headers.put("Host","m.search.naver.com");
        headers.put("Connection","keep-alive");
        headers.put("Cache-Control","max-age=0");
        headers.put("Upgrade-Insecure-Requests","1");
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
        headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        headers.put("Accept-Encoding","gzip, deflate, sdch, br");
        headers.put("Accept-Language","ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
        headers.put("Cookie","Cookie: NNB=R5OIDEDWDAZVS; page_uid=TgKz6wpVuqwssteWhAGssssss7o-068819; _naver_usersession_=SMs6MTU5bT0BxpBow5Hntw==; BMR=");
        Connection connection = null;
        try {
            connection = Jsoup.connect(URLDecoder.decode(link,"UTF-8")).timeout(AppSettings.TIMEOUT).headers(headers);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            return connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
