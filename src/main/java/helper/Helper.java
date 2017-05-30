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

    public static String getTarget(String content, String start, String end){
        //System.out.println(content.contains(start));
        if(!content.contains(start) || !content.contains(end)){
            return "";
        }
        int startIndex = content.indexOf(start) + start.length();
        content = content.substring(startIndex);
        String target = content.substring(0, content.indexOf(end));
        content = content.substring(content.indexOf(end) + end.length());
        return target.trim();

    }
}
