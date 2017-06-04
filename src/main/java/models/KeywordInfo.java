package models;

import java.util.List;

/**
 * Created by DongwooSeo on 2017-05-29.
 */
public class KeywordInfo {
    private String keywordName;
    private int keywordType;
    private List<String> relatedKeywordLinks;

    public KeywordInfo() {

    }

    public KeywordInfo(String keywordName, int keywordType, List<String> relatedKeywordLinks) {
        this.keywordName = keywordName;
        this.keywordType = keywordType;
        this.relatedKeywordLinks = relatedKeywordLinks;
    }

    public String getKeywordName() {
        return keywordName;
    }
    public int getKeywordType() {
        return keywordType;
    }
    public List<String> getRelatedKeywordLinks() {
        return relatedKeywordLinks;
    }

}
