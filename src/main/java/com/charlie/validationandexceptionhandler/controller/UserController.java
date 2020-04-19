package com.charlie.validationandexceptionhandler.controller;

import com.charlie.validationandexceptionhandler.entity.User;
import com.charlie.validationandexceptionhandler.service.UserService;
import com.charlie.validationandexceptionhandler.vo.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ycn
 * @version 1.0.0
 * @ClassName UserController.java
 * @Description TODO
 * @createTime 2020-04-19 17:52:00
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("register")
    public User registerUser(@RequestBody @Validated(User.Register.class) User user) {
        return userService.addUser(user);
    }

    @RequestMapping("update")
    public Boolean updateUser(@RequestBody @Validated(User.Update.class) User user, HttpServletRequest request) {
        request.setAttribute("message","更新用户信息");
        return userService.updateUser(user);
    }

}
