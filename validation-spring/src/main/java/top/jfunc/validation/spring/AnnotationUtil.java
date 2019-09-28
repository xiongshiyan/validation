package top.jfunc.validation.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author xiongshiyan at 2018/11/2 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class AnnotationUtil {
    /**
     * 通过目标对象获取方法实例
     */
    public static Method getMethod(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        //获取方法签名
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        try {
            method = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        } catch (Exception e) {
        }
        return method;
    }

    /**
     * 获取注解
     */
    public static Validated getValidated(Method method) {
        if(null == method){
            return null;
        }
        return method.isAnnotationPresent(Validated.class) ? method.getAnnotation(Validated.class) : null;
    }
}
