package com.xuan.practice.mapper;

import com.xuan.practice.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserMapper {
    ArrayList<User> getAllUser();

    User getUserByID(String username);
}
