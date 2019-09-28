package top.jfunc.validation;

import org.junit.Test;

/**
 * Created by ZhangGang on 2017/9/14.
 */
public class TestMax {

    @Test
    public void testMax() {
        ValidateValue.with("asdasd").minLength(2).maxLength(30);
    }

    @Test
    public void testMax2() {
        ValidateValue.with("asdasd").maxLength(20).minLength(3);
    }
}
