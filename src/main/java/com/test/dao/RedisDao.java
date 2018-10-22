package com.test.dao;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @className:
 * @Description:
 * @auther:ynhj
 * @date:15:24 2018-10-19
 * @version: ver 1.0
 */
@Repository
public class RedisDao {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> template;

    public void setKey(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value);
    }

    public String getValue(String key){
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }
}
