package com.rex.springboot.mapper;

import com.rex.springboot.bean.User;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    List<User> query();
}