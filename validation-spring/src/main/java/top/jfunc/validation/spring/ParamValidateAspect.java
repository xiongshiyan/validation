package top.jfunc.validation.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author xiongshiyan
 * 示例：参数校验切面
 * 使用者可以通过定义自己的切面,在调用方法之前调用{@see ValidateUtil#validateJoinPointParams}
 */
@Aspect
@Component
public class ParamValidateAspect implements Ordered , ApplicationContextAware{
    private ApplicationContext applicationContext;


    @Pointcut("execution(public * top.jfunc.validation.controller..*.*(..))")
    public void webParamValid(){}

    @Around(value = "webParamValid()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        ValidateUtil.validateJoinPointParams(applicationContext , pjp);
        return pjp.proceed();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}