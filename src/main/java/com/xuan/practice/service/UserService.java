package com.xuan.practice.service;

import com.xuan.practice.mapper.UserMapper;
import com.xuan.practice.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public String userLogin(String username,String password){
        User user= userMapper.getUserByID(username);
        if (user == null){
            return "the id does not exist";
        }else {
            if (password.equals(user.getPassword())){
                return "success";
            }else{
                return "wrong password";
            }
        }
    }

}
