package com.xuan.practice.controller;

import com.xuan.practice.pojo.JsonResult;
import com.xuan.practice.pojo.User;
import com.xuan.practice.service.UserService;
import com.xuan.practice.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public JsonResult<String> login(@RequestBody User user){
        System.out.println(user.toString());
        String msg = userService.userLogin(user.getId(),user.getPassword());

        if (msg.equals("success")){
            String Token = JwtUtils.createToken(user,"hehe");
            return new JsonResult<String>(Token,200,"登录成功，请求时再把令牌放到header的toeken");
        }
        return new JsonResult<String>(null,200,msg);
    }

    @GetMapping("/test")
    public String hehe(){
        return "asdfsdafsdafsda";
    }
}
