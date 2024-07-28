package com.e2mg.redis;

import com.github.incu6us.redis.mock.core.JedisConnectionMockFactory;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.IOException;

public class TestEmbedRedis {

    @Test
    public void testNormal_when_start_redis() throws IOException, InterruptedException {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(new JedisConnectionMockFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        redisTemplate.opsForValue().set("a", "a");
        System.out.println(redisTemplate.opsForValue().get("a"));
    }
}
