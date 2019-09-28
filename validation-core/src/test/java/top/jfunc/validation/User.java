package top.jfunc.validation;

import top.jfunc.validation.annotation.*;

import java.util.Date;

/**
 * Created by ZhangGang on 2017/9/8.
 */
public class User {

    @NotNull(msg = "姓名不能为空")
    @MaxLength(value = 20,msg = "姓名不能超过20个字")
    private String name;

    private Date birthday;

    @IdCard
    private String idcard;

    @Max(30)
    @Min(12)
    private int age;

    @Email
    @MaxLength(50)
    private String email;

    @Phone
    private String phone;

    @Regex("[1-9]([0-9]{5,11})")
    private String qq;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return (Date) birthday.clone();
    }

    public void setBirthday(Date birthday) {
        this.birthday = (Date) birthday.clone();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
