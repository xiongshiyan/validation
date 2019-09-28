package top.jfunc.validation.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ZhangGang on 2017/8/31.
 */
public class RegexUtil {
    public static boolean test(Pattern pattern, String value) {
        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}
