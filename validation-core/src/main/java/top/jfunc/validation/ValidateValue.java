package top.jfunc.validation;

import top.jfunc.common.utils.BeanUtil;
import top.jfunc.validation.annotation.*;
import top.jfunc.validation.core.ValidateCache;
import top.jfunc.validation.core.ValidateHandler;
import top.jfunc.validation.utils.NullUtil;
import top.jfunc.validation.utils.ReflectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * ValidateValue
 * @author xiongshiyan
 */
public class ValidateValue {

    private Object value;

    private ValidateValue(Object value) {
        this.value = value;
    }

    /**
     * 新建校验实例，传入目标对象
     *
     * @param value 校验对象
     * @return ValidateValue
     */
    public static ValidateValue with(Object value) {
        return new ValidateValue(value);
    }

    /**
     * 切换目标对象，不重新创建实例
     *
     * @param value 校验对象
     * @return ValidateValue
     */
    public ValidateValue and(Object value) {
        this.value = value;
        return this;
    }

    /**
     * 非空校验
     *
     * @return ValidateValue
     */
    public ValidateValue notNull() {
        return notNull(null);
    }

    /**
     * 非空校验
     *
     * @param msg 错误信息
     * @return ValidateValue
     */
    public ValidateValue notNull(String msg) {
        ValidateHandler.notNull(value, msg);
        return this;
    }

    /**
     * 正则校验
     *
     * @param regex 正则表达式
     * @return ValidateValue
     */
    public ValidateValue regex(String regex) {
        return regex(regex, null);
    }

    /**
     * 正则校验
     *
     * @param regex 正则表达式
     * @param msg   错误信息
     * @return ValidateValue
     */
    public ValidateValue regex(String regex, String msg) {
        ValidateHandler.regex(regex, value, msg);
        return this;
    }

    /**
     * 最大值校验
     *
     * @param max 最大值
     * @return ValidateValue
     */
    public ValidateValue max(Number max) {
        return max(max, null);
    }

    /**
     * 最大值校验
     *
     * @param max 最大值
     * @param msg 错误信息
     * @return ValidateValue
     */
    public ValidateValue max(Number max, String msg) {
        ValidateHandler.max(max, value, msg);
        return this;
    }

    /**
     * 最小值校验
     *
     * @param min 最小值
     * @return ValidateValue
     */
    public ValidateValue min(Number min) {
        return min(min, null);
    }

    /**
     * 最小值校验
     *
     * @param min 最小值
     * @param msg 错误信息
     * @return ValidateValue
     */
    public ValidateValue min(Number min, String msg) {
        ValidateHandler.min(min, value, msg);
        return this;
    }

    /**
     * 最大长度校验
     *
     * @param max 最大长度
     * @return ValidateValue
     */
    public ValidateValue maxLength(int max) {
        return maxLength(max, null);
    }

    /**
     * 最大长度校验
     *
     * @param max 最大长度
     * @param msg 错误信息
     * @return ValidateValue
     */
    public ValidateValue maxLength(int max, String msg) {
        ValidateHandler.maxLength(max, value, msg);
        return this;
    }

    /**
     * 最小长度校验
     *
     * @param min 最小长度
     * @return ValidateValue
     */
    public ValidateValue minLength(int min) {
        return minLength(min, null);
    }

    /**
     * 最小长度校验
     *
     * @param min 最小长度
     * @param msg 错误信息
     * @return ValidateValue
     */
    public ValidateValue minLength(int min, String msg) {
        ValidateHandler.minLength(min, value, msg);
        return this;
    }

    /**
     * 中文校验
     *
     * @return ValidateValue
     */
    public ValidateValue isChinese() {
        return isChinese(null);
    }

    /**
     * 中文校验
     *
     * @param msg 错误信息
     * @return ValidateValue
     */
    public ValidateValue isChinese(String msg) {
        ValidateHandler.isChinese(value, msg);
        return this;
    }

    /**
     * 英文校验
     *
     * @return ValidateValue
     */
    public ValidateValue isEnglish() {
        return isEnglish(null);
    }

    /**
     * 英文校验
     *
     * @param msg 错误信息
     * @return ValidateValue
     */
    public ValidateValue isEnglish(String msg) {
        ValidateHandler.isEnglish(value, msg);
        return this;
    }

    /**
     * 手机号校验
     *
     * @return ValidateValue
     */
    public ValidateValue isPhone() {
        return isPhone(null);
    }

    /**
     * 手机号校验
     *
     * @param msg 错误信息
     * @return ValidateValue
     */
    public ValidateValue isPhone(String msg) {
        ValidateHandler.isPhone(value, msg);
        return this;
    }

    /**
     * 邮箱校验
     *
     * @return ValidateValue
     */
    public ValidateValue isEmail() {
        return isEmail(null);
    }

    /**
     * 邮箱校验
     *
     * @param msg 错误信息
     * @return ValidateValue
     */
    public ValidateValue isEmail(String msg) {
        ValidateHandler.isEmail(value, msg);
        return this;
    }

    /**
     * 自定义日期格式校验
     *
     * @param format 格式
     * @return ValidateValue
     */
    public ValidateValue isDateMatch(String format) {
        return isDateMatch(format, null);
    }

    /**
     * 自定义日期格式校验
     *
     * @param format 格式
     * @param msg    错误信息
     * @return ValidateValue
     */
    public ValidateValue isDateMatch(String format, String msg) {
        ValidateHandler.isDateMatch(format, value, msg);
        return this;
    }

    /**
     * 身份证校验
     *
     * @return ValidateValue
     */
    public ValidateValue isIdCard() {
        return isIdCard(null);
    }

    /**
     * 身份证校验
     *
     * @param msg 错误信息
     * @return ValidateValue
     */
    public ValidateValue isIdCard(String msg) {
        ValidateHandler.isIdCard(value, msg);
        return this;
    }

    /**
     * IP地址校验
     *
     * @return ValidateValue
     */
    public ValidateValue isIp() {
        return isIp(null);
    }

    /**
     * IP地址校验
     *
     * @param msg 错误信息
     * @return ValidateValue
     */
    public ValidateValue isIp(String msg) {
        ValidateHandler.isIp(value, msg);
        return this;
    }

    /**
     * 对象校验（通过注解）
     *
     * @param value 校验对象
     * @return ValidateValue
     */
    public static ValidateValue check(Object value) {
        ValidateValue validateValue = new ValidateValue(value);
        validateValue.notNull();
        Class classType = value.getClass();
        Set<Field> fieldSet = ValidateCache.getInstance().getFieldsByClass(classType);
        if (null == fieldSet) {
            fieldSet = ReflectUtil.getFieldsByClass(value.getClass());
            ValidateCache.getInstance().setClassFields(classType, fieldSet);
        }
        if (NullUtil.isNull(fieldSet)) {
            return validateValue;
        }
        for (Field field : fieldSet) {
            Annotation[] annotations = ValidateCache.getInstance().getAnnotationsByField(field);
            if (null == annotations) {
                annotations = field.getAnnotations();
                ValidateCache.getInstance().setFieldAnnotations(field, annotations);
            }
            if (NullUtil.isNull(annotations)) {
                return validateValue;
            }
            Object fieldValue;
            try {
                fieldValue = BeanUtil.get(value, field.getName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            for (Annotation annotation : annotations) {
                if (annotation instanceof NotNull) {
                    validateValue.and(fieldValue).notNull(((NotNull) annotation).msg());
                } else if (annotation instanceof Max) {
                    Max max = (Max) annotation;
                    validateValue.and(fieldValue).max(max.value(), max.msg());
                } else if (annotation instanceof Min) {
                    Min min = (Min) annotation;
                    validateValue.and(fieldValue).min(min.value(), min.msg());
                } else if (annotation instanceof MaxLength) {
                    MaxLength maxLength = (MaxLength) annotation;
                    validateValue.and(fieldValue).maxLength(maxLength.value(), maxLength.msg());
                } else if (annotation instanceof MinLength) {
                    MinLength minLength = (MinLength) annotation;
                    validateValue.and(fieldValue).minLength(minLength.value(), minLength.msg());
                } else if (annotation instanceof Email) {
                    validateValue.and(fieldValue).isEmail(((Email) annotation).msg());
                } else if (annotation instanceof Phone) {
                    validateValue.and(fieldValue).isPhone(((Phone) annotation).msg());
                } else if (annotation instanceof IdCard) {
                    validateValue.and(fieldValue).isIdCard(((IdCard) annotation).msg());
                } else if (annotation instanceof Regex) {
                    Regex regex = (Regex) annotation;
                    validateValue.and(fieldValue).regex(regex.value(), regex.msg());
                } else if (annotation instanceof Date) {
                    Date date = (Date) annotation;
                    String format = date.format();
                    validateValue.and(fieldValue).isDateMatch(format, date.msg());
                } else if (annotation instanceof Chinese) {
                    validateValue.and(fieldValue).isChinese(((Chinese) annotation).msg());
                } else if (annotation instanceof English) {
                    validateValue.and(fieldValue).isEnglish(((English) annotation).msg());
                } else if (annotation instanceof IP) {
                    validateValue.and(fieldValue).isIp(((IP) annotation).msg());
                }
            }
        }
        return validateValue;
    }

}
