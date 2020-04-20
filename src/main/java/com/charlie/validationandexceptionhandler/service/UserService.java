package com.charlie.validationandexceptionhandler.service;

import com.charlie.validationandexceptionhandler.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author ycn
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2020-04-19 17:55:00
 */
@Service
public class UserService {

    public User addUser(User user) {
        //调用mapper，此处省略
        user.setId(343L);
        return user;
    }

    public boolean updateUser(User user) {
        //调用mapper，此处省略
        return true;
    }
}
