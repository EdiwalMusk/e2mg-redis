package com.e2mg.redis;

import com.e2mg.redis.pool.JedisFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * 描述
 *
 * @author EdiwalMusk
 * @date 2023/3/4 8:54
 */
@Slf4j
@SpringBootTest
public class TestJedisPool {

    private Jedis jedis;

    @BeforeEach
    public void setUp() {
        jedis = JedisFactory.getJedis();
        jedis.select(0);
    }

    /**
     * 测试锁
     */
    @Test
    public void testGet() {
        jedis.set("a", String.valueOf(5));
        System.out.println(jedis.get("a"));
    }

    @Test
    public void testGetMany() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            JedisFactory.getJedis();
        }
    }

    @Test
    public void testSAdd100000() {
        for (int i = 0; i < 100000; i++) {
            jedis.sadd("set100000", UUID.randomUUID().toString());
        }
    }

    /**
     * 测试可重入锁
     */
    @AfterEach
    public void tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
