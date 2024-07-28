//package com.e2mg.redis;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//import java.util.concurrent.TimeUnit;
//
///**
// * 描述
// *
// * @author EdiwalMusk
// * @date 2023/3/4 8:54
// */
//@Slf4j
//@SpringBootTest
//public class TestRedis {
//
//    @Resource
//    private RedissonClient redissonClient;
//    @Resource
//    private RedissonClient redissonClient1;
//    @Resource
//    private RedissonClient redissonClient2;
//
//    private RLock lock;
//
//    @BeforeEach
//    public void setUp() {
//        RLock lock1 = redissonClient.getLock("order");
//        RLock lock2 = redissonClient1.getLock("order");
//        RLock lock3 = redissonClient2.getLock("order");
//        lock = redissonClient.getMultiLock(lock1, lock2, lock3);
//    }
//
//    /**
//     * 测试锁
//     * @throws InterruptedException
//     */
//    @Test
//    public void testRedissonLock() throws InterruptedException {
//        // RLock lock = redissonClient.getLock("anyLock");
//        boolean isLock = lock.tryLock(1, 10, TimeUnit.SECONDS);
//        if (isLock) {
//            System.out.println(1);
//            lock.unlock();
//        }
//    }
//
//    /**
//     * 测试可重入锁
//     */
//    @Test
//    public void testReentrantLock() {
//        // RLock lock = redissonClient.getLock("reentrantLock");
//        lock.tryLock();
//        try {
//            log.info("testReentrantLock");
//            testReentrantLock_(lock);
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    void testReentrantLock_(RLock lock) {
//        lock.tryLock();
//        try {
//            log.info("testReentrantLock_");
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    // @Test
//    // public void testSetUp() {
//    //
//    // }
//}
