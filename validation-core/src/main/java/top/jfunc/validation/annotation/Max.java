package top.jfunc.validation.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Max {
    int value();

    String msg() default "";
}