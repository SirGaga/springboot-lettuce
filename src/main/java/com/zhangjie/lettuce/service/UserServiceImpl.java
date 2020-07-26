package com.zhangjie.lettuce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public String getString(String key){
        if(redisTemplate.hasKey(key)){
            logger.info("redis中存在key："+key);
        }else{
            String val = "学习lettuce";
            logger.info("从mysql中查询数据");
            redisTemplate.opsForValue().set(key,val);
            // 设置超时时间
            //redisTemplate.expire(key,28, TimeUnit.HOURS);
        }
        return redisTemplate.opsForValue().get(key).toString();
    }
}
