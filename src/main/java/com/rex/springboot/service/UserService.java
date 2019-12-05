package com.rex.springboot.service;

import com.rex.springboot.bean.User;

import java.util.List;

public interface UserService {
    List<User> query();

    User findUserById(String userId);
}
