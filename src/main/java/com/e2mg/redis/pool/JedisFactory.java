package com.e2mg.redis.pool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * 描述
 *
 * @author EdiwalMusk
 * @date 2023/3/5 19:05
 */
public class JedisFactory {

    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMaxTotal(8);
        jedisPoolConfig.setMaxWait(Duration.ofSeconds(10));
        jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
