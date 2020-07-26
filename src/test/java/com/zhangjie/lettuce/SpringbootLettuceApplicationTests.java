package com.zhangjie.lettuce;

import com.zhangjie.lettuce.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootLettuceApplicationTests {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void contextLoads() {
        System.out.println(userServiceImpl.getString("id"));
    }

}
