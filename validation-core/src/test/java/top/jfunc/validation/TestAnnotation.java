package top.jfunc.validation;

import org.junit.Test;

/**
 * Created by ZhangGang on 2017/9/8.
 */
public class TestAnnotation {

    @Test
    public void mm() {
        User user = new User();
        user.setName("aaaa");
        user.setAge(18);
        ValidateValue.check(user);
        ValidateValue.with(user.getName()).min(2).max(20);

        ValidateValue.check(user).and(user.getName()).min(2,"a").max(20,"b");





        try {
            //....
            ValidateValue.check(user);
            //.....
        }catch (IllegalArgumentException e){
            throw e;
        }catch (Exception e){
            //...
        }

        ValidateValue.check(user).and("2017-06-05").isDateMatch("yyyy-MM-dd");




    }


    @Test
    public void regex() {
        ValidateValue.with("157774105").regex("[1-9]([0-9]{5,11})").and("1").regex("[1-9]","b");
        ValidateValue.with("18627817977").isPhone("o").isPhone().and("张刚").isChinese().and("zhanggang").isEnglish().isEnglish("e")
                .and("gangzhang@fetech.com").isEmail("e").isEmail().and("157774105").regex("[1-9]([0-9]{5,11})").and("1").regex("[1-9]");
    }

    @Test
    public void date() {
        ValidateValue.with("2017-06-05 14:30:10").isDateMatch("yyyy-mm-dd HH:mm:ss","a")
                .and("2017-06-05").isDateMatch("yyyy-MM-dd")
                .and("2017年09月08号").isDateMatch("yyyy年MM月08号");







        ValidateValue.with("a").notNull();

        ValidateValue.with("test").maxLength(20).minLength(4);

        ValidateValue.with(50).min(20).max(60);


        ValidateValue.with("a").notNull().and("test").maxLength(20).minLength(4)
                .and(50).min(20).max(60);

        ValidateValue.with("a").notNull().and("test").maxLength(20,"maxL").minLength(4,"minL")
                .and(50).min(20).max(60);

        ValidateValue.with("test").maxLength(20,"最大长度不能超过20个字").minLength(4,"最小长度不能少于4个字");

    }

    @Test
    public void i() {
        ValidateValue.with("192.168.1.123").isIp("ip")
                .and("440304199010119094").isIdCard("id")
                .and("1.1.0.7").isIp()
                .and("21010219751115209X").isIdCard().isIdCard("id");
    }
}
