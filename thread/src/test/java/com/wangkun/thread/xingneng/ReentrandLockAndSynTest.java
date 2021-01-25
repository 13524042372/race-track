package com.wangkun.thread.xingneng;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrandLockAndSynTest {
    long  n = 0 ;
    /**
     * 测试syn 还有reent的性能
     * 分别开启以5个线程做1000000次加解锁
     * 分别开启以10个线程做1000000次加解锁
     * 分别开启以40个线程做1000000次加解锁
     * 分别开启以100个线程做1000000次加解锁

     */
    @Test
    public void reentradLockTest() throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        int threadNum = 10;

        Object object = new Object();
        final CountDownLatch endGate = new CountDownLatch(threadNum);
        for(int i = 0 ; i < threadNum ; i ++){
            int finalI = i;
            new Thread(() -> {
                try {
                    startGate.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j = 0; j < 10000_0000; j++){
                    synchronized(object) {
                        n += j;
                    }
                }
                System.out.println(n);
                endGate.countDown();
            }).start();
        }
        long startTime = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long cuTime = System.currentTimeMillis() - startTime;
        System.out.println(cuTime);

    }
    @Test
    public void reentradSynLockTest() throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        int threadNum = 10;
        final CountDownLatch endGate = new CountDownLatch(threadNum);
        ReentrantLock reentrantLocks = new  ReentrantLock();
        for(int i = 0 ; i < threadNum ; i ++){
            int finalI = i;
            int finalI1 = i;
            new Thread(() -> {
                try {
                    startGate.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j = 0; j < 10000_0000; j++){
                    reentrantLocks.lock();
                    n += j;

                    reentrantLocks.unlock();

                }
                System.out.println(n);

                endGate.countDown();
            }).start();
        }
        long startTime = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long cuTime = System.currentTimeMillis() - startTime;
        System.out.println(cuTime);

    }

}
