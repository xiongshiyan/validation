package top.jfunc.validation;

import org.junit.Test;

/**
 * Created by ZhangGang on 2017/9/22.
 */
public class TestChinese {

    @Test
    public void test1() {
        ValidateValue.with("啊大神，阿斯蒂芬！").isChinese();
        try {
            ValidateValue.with(",asd").isChinese("c");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
