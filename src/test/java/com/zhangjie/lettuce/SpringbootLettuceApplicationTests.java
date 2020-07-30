package com.zhangjie.lettuce;

import com.zhangjie.lettuce.service.ListCacheServiceImpl;
import com.zhangjie.lettuce.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootLettuceApplicationTests {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ListCacheServiceImpl listCacheService;

    @Test
    void contextLoads() {
        System.out.println(userServiceImpl.getString("id"));
    }
    @Test
    void test() {
        System.out.println(userServiceImpl.getUserById("2"));
    }
    @Test
    void test2(){
        listCacheService.initArticle();
    }

    @Test
    void test3(){
        listCacheService.getTop5().forEach(System.out::println);
    }

    @Test
    void test4(){
        listCacheService.addArticle();
    }

}
