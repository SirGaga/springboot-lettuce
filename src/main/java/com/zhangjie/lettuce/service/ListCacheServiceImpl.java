package com.zhangjie.lettuce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ListCacheServiceImpl {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

}
