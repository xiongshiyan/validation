package top.jfunc.validation.spring;

import org.springframework.stereotype.Component;
import top.jfunc.validation.ValidateValue;

/**
 * 示例用法
 * 具体校验可以参看 {@link ValidateValue}
 * @author xiongshiyan at 2019/9/28 , contact me with email yanshixiong@126.com or phone 15208384257
 */
@Component
public class RangeValidator implements Validator{
    @Override
    public void validate(Object[] params) throws IllegalArgumentException {
        ValidateValue.with(params[0]).maxLength(10).minLength(1);
    }
}
