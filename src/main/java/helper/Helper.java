package helper;

import java.util.List;

/**
 * Created by DongwooSeo on 2017-05-28.
 */
public class Helper {
    public static boolean containValue(String target, String value) {
        if (target.contains(",")) {
            for (String str : target.split(",")) {
                if (value.contains(str))
                    return true;
            }
        } else {
            if (value.contains(target))
                return true;
        }
        return false;
    }

    public static String cutStringInRange(String content, String startText, String endText){
        //System.out.println(content.contains(start));
        if(!content.contains(startText) || !content.contains(endText)){
            return "";
        }
        int startIndex = content.indexOf(startText) + startText.length();
        content = content.substring(startIndex);
        String target = content.substring(0, content.indexOf(endText));
        content = content.substring(content.indexOf(endText) + endText.length());
        return target.trim();

    }
}
