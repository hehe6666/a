package com.xuan.practice;

import com.xuan.practice.mapper.UserMapper;
import com.xuan.practice.pojo.User;
import com.xuan.practice.util.JwtUtils;
import com.xuan.practice.util.RedisOperatorUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class PracticeApplicationTests {

    @Test
    void contextLoads() {
        String token = JwtUtils.createToken(new User("asdf","123456"),"hehe");
        System.out.println(token);

        JwtUtils.verifyToken(token,"hehe");

        System.out.println(JwtUtils.getClaimByName(token, "id").asString());
    }


    @Autowired
    private RedisOperatorUtils redis;
    @Test
    void redisTest(){
        redis.set("haha","jiuhze");
        System.out.println(redis.get("haha"));
        redis.set("haha","asdfsad阿斯顿发大水");
        System.out.println(redis.get("haha"));
    }

    @Autowired
    UserMapper userMapper;
    @Test
    public void testMysql(){
        System.out.println(userMapper.getAllUser());
    }
}


