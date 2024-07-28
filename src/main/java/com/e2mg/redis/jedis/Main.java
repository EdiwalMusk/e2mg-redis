package com.e2mg.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.Set;
import java.util.UUID;

public class Main {
    private static Jedis jedis;

    private static String toSetKey(int i) {
        return String.format("set:key:%d", i);
    }

    private static void setSunion() {
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < 1000; i++) {
            pipeline.del(toSetKey(i));
            pipeline.sadd(toSetKey(i), UUID.randomUUID().toString());
        }
        pipeline.syncAndReturnAll();
        pipeline.close();
    }

    private static void suion() {
        int len = 1000;
        String[] keys = new String[len];
        for (int i = 0; i < len; i++) {
            keys[i] = toSetKey(i);
        }
        Set<String> set = jedis.sunion(keys);
        System.out.println(set.size());
    }

    private static void addKey(int n) {
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < n; i++) {
            pipeline.set(UUID.randomUUID().toString(), "1");
            if (i % 10000 == 0) {
                pipeline.syncAndReturnAll();
            }
        }
    }

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("localhost", 6389);
        jedis = jedisPool.getResource();
//            jedis.set(UUID.randomUUID().toString(), "1");
//            for (int i = 0; i < 1000; i++) {
//                jedis.sadd("set1000", UUID.randomUUID().toString());
//            }
//            for (int i = 0; i < 10000; i++) {
//                jedis.sadd("set10000", UUID.randomUUID().toString());
//            }
//            for (int i = 0; i < 100000; i++) {
//                jedis.sadd("set100000", UUID.randomUUID().toString());
//            }
//        Pipeline pipeline = jedis.pipelinedds();
////            for (int i = 0; i < 1000000; i++) {
////                pipeline.sadd("set1000000", UUID.randomUUID().toString());
////            }
//        for (int i = 0; i < 10000000; i++) {
//            pipeline.sadd("set10000000", UUID.randomUUID().toString());
//        }
//        pipeline.syncAndReturnAll();
//        pipeline.close();
//        setSunion();
//        suion();
        addKey(24000000);
    }
}
