package com.service;

import com.baseService.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

public class ChainFactory {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IBaseService baseService;

    public BookServiceImpl getBookService() {
        return (BookServiceImpl) this.baseService;
    }

    public String chainCall() {
        Object t = new Object();
        String strHello = (String) redisTemplate.opsForHash().get(t,"hello");
        String sWorld = (String) redisTemplate.opsForHash().get(t,"world");

        int strLength = 0;
        if("hello".equals(strHello)){
            strLength = strHello.length();
            return strHello+strLength;
        }else{
            strLength = sWorld.length();
            return sWorld+strLength;
        }

    }
}
