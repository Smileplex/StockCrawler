package models;

import java.util.List;

/**
 * Created by DongwooSeo on 2017-05-29.
 */
public class KeywordInfo {
    String keywordName;
    int keywordType;
    List<String> relatedKeywordLinks;

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }

    public int getKeywordType() {
        return keywordType;
    }

    public void setKeywordType(int keywordType) {
        this.keywordType = keywordType;
    }

    public List<String> getRelatedKeywordLinks() {
        return relatedKeywordLinks;
    }

    public void setRelatedKeywordLinks(List<String> relatedKeywordLinks) {
        this.relatedKeywordLinks = relatedKeywordLinks;
    }
}
