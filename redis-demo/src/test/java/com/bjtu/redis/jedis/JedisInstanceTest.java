package com.bjtu.redis.jedis;

import org.junit.Test;

import com.bjtu.redis.JedisInstance;

import redis.clients.jedis.Jedis;

public class JedisInstanceTest {

    /**
     * 基本使用
     */
    @Test
    public void test() {
        Jedis jedis = JedisInstance.getInstance().getResource();
        jedis.set("name1", "test");
        String val = jedis.get("name1");
        System.out.println(val);
    }

}
