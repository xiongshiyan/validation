package top.jfunc.validation.core;

import top.jfunc.validation.utils.NullUtil;

import java.util.Collection;
import java.util.Map;

/**
 * ValidateHandler
 * Created by ZhangGang on 2016/9/27.
 */
public class ValidateHandler {

    public static void notNull(Object value, String msg) {
        try {
            ValidateProcess.notNull(value);
            if (value instanceof String) {
                ValidateProcess.notNull((String) value);
            } else if (value instanceof Number) {
                ValidateProcess.notNull((Number) value);
            } else if (value instanceof Collection) {
                ValidateProcess.notNull((Collection) value);
            } else if (value instanceof Map) {
                ValidateProcess.notNull((Map) value);
            } else if (value instanceof Object[]) {
                ValidateProcess.notNull((Object[]) value);
            }
        } catch (IllegalArgumentException e) {
            errorMsgHandler(e, msg);
        }
    }

    public static void regex(String regex, Object value, String msg) {
        try {
            if (null == value) {
                return;
            }
            ValidateProcess.regex(regex, value.toString());
        } catch (IllegalArgumentException e) {
            errorMsgHandler(e, msg);
        }
    }

    public static void max(Number max, Object value, String msg) {
        try {
            if (null == value || !(value instanceof Number)) {
                return;
            }
            if (value instanceof Integer) {
                ValidateProcess.max(max.intValue(), (Integer) value);
            } else if (value instanceof Long) {
                ValidateProcess.max(max.longValue(), (Long) value);
            } else if (value instanceof Double) {
                ValidateProcess.max(max.doubleValue(), (Double) value);
            } else if (value instanceof Float) {
                ValidateProcess.max(max.floatValue(), (Float) value);
            } else if (value instanceof Short) {
                ValidateProcess.max(max.shortValue(), (Short) value);
            } else if (value instanceof Byte) {
                ValidateProcess.max(max.byteValue(), (Byte) value);
            }
        } catch (IllegalArgumentException e) {
            errorMsgHandler(e, msg);
        }
    }

    public static void maxLength(int max, Object value, String msg) {
        try {
            if (null == value) {
                return;
            }
            if (value instanceof String) {
                ValidateProcess.maxLength(max, (String) value);
            } else if (value instanceof Collection) {
                ValidateProcess.maxLength(max, (Collection) value);
            } else if (value instanceof Map) {
                ValidateProcess.maxLength(max, (Map) value);
            } else if (value instanceof Object[]) {
                ValidateProcess.maxLength(max, (Object[]) value);
            }
        } catch (IllegalArgumentException e) {
            errorMsgHandler(e, msg);
        }
    }

    public static void minLength(int min, Object value, String msg) {
        try {
            if (null == value) {
                return;
            }
            if (value instanceof String) {
                ValidateProcess.minLength(min, (String) value);
            } else if (value instanceof Collection) {
                ValidateProcess.minLength(min, (Collection) value);
            } else if (value instanceof Map) {
                ValidateProcess.minLength(min, (Map) value);
            } else if (value instanceof Object[]) {
                ValidateProcess.minLength(min, (Object[]) value);
            }
        } catch (IllegalArgumentException e) {
            errorMsgHandler(e, msg);
        }
    }


    public static void min(Number min, Object value, String msg) {
        try {
            if (null == value || !(value instanceof Number)) {
                return;
            }
            if (value instanceof Integer) {
                ValidateProcess.min(min.intValue(), (Integer) value);
            } else if (value instanceof Long) {
                ValidateProcess.min(min.longValue(), (Long) value);
            } else if (value instanceof Double) {
                ValidateProcess.min(min.doubleValue(), (Double) value);
            } else if (value instanceof Float) {
                ValidateProcess.min(min.floatValue(), (Float) value);
            } else if (value instanceof Short) {
                ValidateProcess.min(min.shortValue(), (Short) value);
            } else if (value instanceof Byte) {
                ValidateProcess.min(min.byteValue(), (Byte) value);
            }
        } catch (IllegalArgumentException e) {
            errorMsgHandler(e, msg);
        }
    }

    public static void isChinese(Object value, String msg) {
        try {
            if (null == value) {
                return;
            }
            ValidateProcess.chinese(value.toString());
        } catch (IllegalArgumentException e) {
            errorMsgHandler(e, msg);
        }
    }

    public static void isEnglish(Object value, String msg) {
        try {
            if (null == value) {
                return;
            }
            ValidateProcess.english(value.toString());
        } catch (IllegalArgumentException e) {
            errorMsgHandler(e, msg);
        }
    }

    public static void isPhone(Object value, String msg) {
        try {
            if (null == value) {
                return;
            }
            ValidateProcess.phone(value.toString());
        } catch (IllegalArgumentException e) {
            errorMsgHandler(e, msg);
        }
    }

    public static void isEmail(Object value, String msg) {
        try {
            if (null == value) {
                return;
            }
            ValidateProcess.email(value.toString());
        } catch (IllegalArgumentException e) {
            errorMsgHandler(e, msg);
        }
    }

    public static void isDateMatch(String format, Object value, String msg) {
        try {
            if (null != value) {
                ValidateProcess.date(format, value.toString());
            }
        } catch (IllegalArgumentException e) {
            errorMsgHandler(e, msg);
        }
    }

    public static void isIdCard(Object value, String msg) {
        try {
            if (null == value) {
                return;
            }
            ValidateProcess.idCard(value.toString());
        } catch (IllegalArgumentException e) {
            errorMsgHandler(e, msg);
        }
    }

    public static void isIp(Object value, String msg) {
        try {
            if (null == value) {
                return;
            }
            ValidateProcess.isIp(value.toString());
        } catch (IllegalArgumentException e) {
            errorMsgHandler(e, msg);
        }
    }

    private static void errorMsgHandler(IllegalArgumentException e, String msg) {
        if (NullUtil.isNull(msg)) {
            throw e;
        }
        throw new IllegalArgumentException(msg);
    }
}
