package top.jfunc.validation.spring;

import org.aspectj.lang.JoinPoint;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;

/**
 * @author xiongshiyan at 2019/9/28 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class ValidateUtil {
    /**
     * 在切面方法前调用此方法，根据注解{@link Validated}指定的{@link Validator}校验方法的参数
     * @param applicationContext ApplicationContext
     * @param pjp JoinPoint 在此切入点之前调用
     */
    public static void validateJoinPointParams(ApplicationContext applicationContext , JoinPoint pjp) {
        //目标方法
        Method method = AnnotationUtil.getMethod(pjp);
        Validated validated = AnnotationUtil.getValidated(method);
        //存在@Validated注解
        if(null != validated){
            Class<? extends Validator>[] validatorClasses = validated.value();
            for (Class<? extends Validator> validatorClass : validatorClasses) {
                Validator validator = applicationContext.getBean(validatorClass);
                validator.validate(pjp.getArgs());
            }
        }
    }
}
