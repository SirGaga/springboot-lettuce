package com.zhangjie.lettuce.service;

import com.zhangjie.lettuce.domain.User;
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

    public User getUserById(String id){
        String key = "user:"+id;
        if(redisTemplate.opsForHash().hasKey("user",id)){
            logger.info("redis中存在key："+key);
        }else{
            logger.info("从mysql中查询数据");
            User user = new User();
            user.setId(id);
            user.setAge(30);
            user.setName("李四");
            redisTemplate.opsForHash().put("user",id,user);
        }
        return (User)redisTemplate.opsForHash().get("user",id);
    }
}
