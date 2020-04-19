package com.charlie.validationandexceptionhandler.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author ycn
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2020-04-19 17:52:00
 */
@Data
public class User {

    public interface Register {

    }

    public interface Update {

    }

    @NotNull(message = "用户ID不能为空", groups = Update.class)
    private Long id;

    @NotNull(message = "用户名不能为空",groups = Register.class)
    @Size(max = 20, message = "用户名不能超过20个字符")
    private String userName;

    @NotNull(message = "密码不能为空",groups = Register.class)
    @Size(max = 20, message = "密码不能超过20个字符")
    private String password;

    @NotNull(message = "邮箱不能为空",groups = Register.class)
    @Email(message = "邮箱格式不对")
    private String email;
}
