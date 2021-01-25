package com.wangkun.thread.xingneng;

import org.junit.jupiter.api.Test;
import sun.misc.Contended;

import java.util.concurrent.CountDownLatch;

/**
 * 伪共享测试类
 */
public class FalseShare {
    @Test
    public void falseShareBaseTest() throws InterruptedException {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(2);

        Count2 a = new Count2();
        new Thread(() -> {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(long i = 0 ; i < 10000_0000L; i++){
                a.a ++;
            }
            end.countDown();
        }).start();

        new Thread(() -> {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(long i = 0 ; i < 10000_0000L; i++){
                a.b ++;
            }
            end.countDown();

        }).start();

        Thread.sleep(100);

        long startTime = System.currentTimeMillis();
        start.countDown();
        end.await();
        System.out.println(System.currentTimeMillis() - startTime);


    }

    public class Count{
        volatile  public long a;
        volatile  public long b;
    }
    @Contended
    public class Count2{
        volatile  public long a;
        volatile  public long t1,t2,t3,t4,t5,t6,t7;

        volatile  public long b;
    }

    public class Count1{
        volatile  public long a;
        volatile  public long t1,t2,t3,t4,t5,t6,t7;

        volatile  public long b;
    }
}
