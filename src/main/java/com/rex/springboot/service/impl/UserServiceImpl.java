package com.rex.springboot.service.impl;

import com.rex.springboot.bean.User;
import com.rex.springboot.mapper.UserMapper;
import com.rex.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> query() {
        return userMapper.query();
    }

    @Override
    public User findUserById(String userId) {
        return null;
    }
}
