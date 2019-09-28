package top.jfunc.validation.spring;

import java.lang.annotation.*;

/**
 * 添加了此注解就表示要进行参数校验
 * @author xiongshiyan at 2018/11/2 , contact me with email yanshixiong@126.com or phone 15208384257
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validated {
    Class<? extends Validator>[] value();
}
