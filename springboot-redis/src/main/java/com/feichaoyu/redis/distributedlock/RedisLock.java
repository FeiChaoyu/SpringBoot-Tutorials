package com.feichaoyu.redis.distributedlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Redis分布式锁的实现
 *
 * @author feichaoyu
 */
public class RedisLock implements AutoCloseable {

    @Autowired
    private RedisTemplate redisTemplate;

    private String key;

    private String value;

    private long expireTime;

    private TimeUnit timeUnit;

    public RedisLock(String key, long expireTime, TimeUnit timeUnit) {
        this.key = key;
        this.value = UUID.randomUUID().toString().replace("-", "");
        this.expireTime = expireTime;
        this.timeUnit = timeUnit;
    }

    public boolean lock() {
        return redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, timeUnit);
    }

    public boolean unlock() {
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        RedisScript<Boolean> redisScript = RedisScript.of(luaScript, Boolean.class);
        List<String> keys = Arrays.asList(key);
        return (Boolean) redisTemplate.execute(redisScript, keys, value);
    }

    @Override
    public void close() throws Exception {
        unlock();
    }
}
