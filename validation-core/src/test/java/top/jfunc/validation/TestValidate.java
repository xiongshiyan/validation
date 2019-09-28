package top.jfunc.validation;

import org.junit.Test;

/**
 * Created by ZhangGang on 2017/9/8.
 */
public class TestValidate {

    @Test
    public void test() {
        User user = new User();
        user.setAge(18);
        user.setName("test");
        ValidateValue.with(user).notNull().and(user.getAge()).max(20).and(user.getName()).notNull();
    }

    @Test
    public void test2() {
        User user = new User();
        user.setAge(50);
        user.setName("test");
        try {
            ValidateValue.with(user).notNull("A").and(user.getAge()).max(20).and(user.getName()).notNull();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test3() {
        User user = new User();
        user.setAge(18);
        user.setName("test");
        ValidateValue.check(user);
    }

    @Test
    public void test4() {
        User user = new User();
        user.setAge(50);
        user.setName("test");
        try {
            ValidateValue.check(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
