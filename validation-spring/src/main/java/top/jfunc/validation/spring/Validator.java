package top.jfunc.validation.spring;

/**
 * @author xiongshiyan at 2019/9/28 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public interface Validator {
    /**
     * 对传入的参数进行校验
     * @param params 传入的参数
     * @throws IllegalArgumentException 参数校验不过抛出异常
     */
    void validate(Object[] params) throws IllegalArgumentException;
}
