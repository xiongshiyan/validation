# validation
<p>轻量级服务端校验工具</p>
<p>支持Bean注解校验、常用校验功能齐全、使用简便</p>
<p>支持使用AOP+注解的参数校验</p>

##NOTE 
validation-core是在项目 https://gitee.com/fetech-framework/smart-validator 
基础上做了以下改动生成的，使用方式保持基本一致：
1. 命名优化，比如ValidateUtils->ValidateValue，is->with使意义更明确
2. 正则表达式使用Pattern缓存
3. 去除ParamException异常，使用IllegalArgumentException替代
4. 小bug修复

## 一、功能简介
主要提供便捷的后台数据校验功能，支持单个字段或参数校验，也支持通过注解校验对象，用法简单。<br>
提供基本的非空、长度、大小等校验方法，也提供一些特殊的正则校验、身份证、电话、邮箱、IP等校验方法。

## 二、用法介绍
目前提供以下校验方法，支持后续持续扩展

  | 注解        | 说明    |
  | :---------: | :------ |
  | NotNull | 非空校验 |
  | Max | 最大值校验 |
  | Min | 最小值校验 |
  | MaxLength | 最大长度校验，支持集合、数组长度校验 |
  | MinLength | 最大长度校验，支持集合、数组长度校验 |
  | IdCard | 身份证校验 |
  | Email | 邮箱格式校验 |
  | Phone | 手机号校验 |
  | IP | IP地址校验 |
  | Chinese | 中文校验 |
  | English | 英文校验 |
  | Regex | 自定义正则校验 |
  | Date | 日期格式校验 |

### 1. 单个参数验证
```java
ValidateValue.with("a").notNull();
 
ValidateValue.with("test").maxLength(20).minLength(4);
 
ValidateValue.with(50).min(20).max(60);
```

通过and()支持连写（连写直接切换校验对象）

```java
ValidateValue.with("a").notNull().and("test").maxLength(20).minLength(4).and(50).min(20).max(60);
```
支持自定义错误信息

```java
ValidateValue.with("test").maxLength(20,"最大长度不能超过20个字").minLength(4,"最小长度不能少于4个字");
```
### 2. 校验整个对象（通过注解）
在类的属性上定义注解，同时支持自定义错误信息
```java
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
    
    //get... set..
}
```

然后调用ValidateValue.check()方法即可

```java
try {
    //....
    ValidateValue.check(user);
    //.....
}catch (IllegalArgumentException e){
    throw e;
}catch (Exception e){
    //...
}
```

同样支持连写

```java
ValidateValue.check(user).and("2017-06-05").isDateMatch("yyyy-MM-dd");
```

### 3.校验不通过时处理
校验不通过会抛出IllegalArgumentException（运行时异常）<br>

使用时一般不需要特殊处理，由于后台校验是安全性校验，一般用于拦截非法操作，所以不用友好提示，所以推荐不做任何捕获或者特殊处理，如外层有catch，建议单独捕获后向上抛出。<br>
如果想做异常捕获，也可以自行在代码中添加try/catch（不推荐），或者添加全局的拦截器捕获该类异常。<br>

### 4.在SpringMVC项目中使用
1. 参照 [top.jfunc.validation.spring.ParamValidateAspect](https://gitee.com/xxssyyyyssxx/validation/blob/master/validation-spring/src/main/java/top/jfunc/validation/spring/ParamValidateAspect.java) 编写自己的切面
2. 参照 [top.jfunc.validation.spring.RangeValidator](https://gitee.com/xxssyyyyssxx/validation/blob/master/validation-spring/src/main/java/top/jfunc/validation/spring/RangeValidator.java) 编写针对某个方法的校验器，并放入Spring容器
3. 在方法上添加 [top.jfunc.validation.spring.Validated](https://gitee.com/xxssyyyyssxx/validation/blob/master/validation-spring/src/main/java/top/jfunc/validation/spring/Validated.java) 指定校验器，如果校验出错就会抛出 IllegalArgumentException ，可以在统一异常处理处理之

### 三、部署说明
```
    compile "top.jfunc.validation:validation-core:1.0"
    compile "top.jfunc.validation:validation-spring:1.0"
```