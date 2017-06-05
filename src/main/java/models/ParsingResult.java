package models;

import services.PageReader;

import java.util.List;

/**
 * Created by DongwooSeo on 2017-05-30.
 */
public class ParsingResult {
    private List<String> links;
    private int keywordId;

    public ParsingResult() {}
    public ParsingResult(List<String> links, int keywordId) {
        this.links = links;
        this.keywordId = keywordId;
    }

    public List<String> getLinks() {
        return links;
    }

    public int getKeywordId() {
        return keywordId;
    }

}
