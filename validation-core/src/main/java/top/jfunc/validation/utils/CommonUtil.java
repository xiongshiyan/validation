package top.jfunc.validation.utils;

import top.jfunc.common.utils.ArrayUtil;
import top.jfunc.common.utils.CollectionUtil;
import top.jfunc.common.utils.StrUtil;

import java.util.Collection;
import java.util.Map;

/**
 * Created by ZhangGang on 2017/8/31.
 */
public class CommonUtil {
    public static boolean isNull(String str) {
        return StrUtil.isEmpty(str);
    }

    public static boolean isNull(Collection<?> collection) {
        return CollectionUtil.isEmpty(collection);
    }

    public static boolean isNull(Map<?, ?> paramMap) {
        return null == paramMap || paramMap.isEmpty();
    }

    public static boolean isNull(Object[] array) {
        return null == array || array.length == 0;
    }
}
