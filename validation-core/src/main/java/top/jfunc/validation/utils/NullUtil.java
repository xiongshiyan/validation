package top.jfunc.validation.utils;

import top.jfunc.common.utils.CollectionUtil;
import top.jfunc.common.utils.StrUtil;

import java.util.Collection;
import java.util.Map;

/**
 * @author xiongshiyan
 */
public class NullUtil {
    public static boolean isNull(String str) {
        return StrUtil.isEmpty(str);
    }

    public static boolean isNull(Collection<?> collection) {
        return CollectionUtil.isEmpty(collection);
    }

    public static boolean isNull(Map<?, ?> paramMap) {
        return null == paramMap || paramMap.isEmpty();
    }

    public static <T> boolean  isNull(T[] array) {
        return null == array || array.length == 0;
    }
}
