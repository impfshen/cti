package com.shen.springbootwechat.Util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class TaskUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public Set<String> keys(){
        try {
            return redisTemplate.keys("*");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<String> keys(String str){
        try {
            return redisTemplate.keys(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean hasKey(String key) {
        try {
            Boolean exist = redisTemplate.hasKey(key);
            if(exist == null)
                return false;
            else
                return exist;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object HashGet(String key, String hashKey){
        try {
            return redisTemplate.opsForHash().get(key, hashKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean HashSet(String key, String hashKey, String value){
        try {
            redisTemplate.opsForHash().put(key,hashKey,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean HashDel(String key, String hashKey, String value){
        try {
            redisTemplate.opsForHash().delete(key,hashKey,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object ListGet(String key, long start, long end){
        try {
            return redisTemplate.opsForList().range(key,start,end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * mod=0 表示rightPush
     * mod=1 表示leftPush
     */
    public boolean ListAdd(String key, String value, int mod){
        try {
            if (mod == 0)
                redisTemplate.opsForList().rightPush(key,value);
            else
                redisTemplate.opsForList().leftPush(key,value);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * mod=0 表示rightPop
     * mod=1 表示leftPop
     */
    public boolean ListDel(String key, int mod){
        try {
            if (mod == 0)
                redisTemplate.opsForList().rightPop(key);
            else
                redisTemplate.opsForList().leftPop(key);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
