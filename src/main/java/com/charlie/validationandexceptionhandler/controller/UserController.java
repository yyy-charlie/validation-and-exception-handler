package com.charlie.validationandexceptionhandler.controller;

import com.charlie.validationandexceptionhandler.common.Const;
import com.charlie.validationandexceptionhandler.entity.User;
import com.charlie.validationandexceptionhandler.service.UserService;
import com.charlie.validationandexceptionhandler.vo.BaseResponse;
import com.charlie.validationandexceptionhandler.vo.TableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @RequestMapping("register")
    public User registerUser(@RequestBody @Validated(User.Register.class) User user) {
        request.setAttribute(Const.RETURN_MESSAGE, "新增用户信息");
        return userService.addUser(user);
    }

    @RequestMapping("update")
    public Boolean updateUser(@RequestBody @Validated(User.Update.class) User user) {
        request.setAttribute(Const.RETURN_MESSAGE, "更新用户信息");
        return userService.updateUser(user);
    }

    @RequestMapping("delete")
    public Boolean deleteUser(@RequestBody @Validated(User.Update.class) User user) {
        request.setAttribute(Const.RETURN_MESSAGE, "删除用户");
        //调用service省略
        return false;
    }

    @RequestMapping("getByCondition")
    public TableResult getUserByCondition(@RequestBody User user) {
        request.setAttribute(Const.RETURN_MESSAGE, "根据条件获取用户信息");
        List<User> users = new ArrayList<>();
        users.add(user);
        return new TableResult<>(1, users);
    }

}
