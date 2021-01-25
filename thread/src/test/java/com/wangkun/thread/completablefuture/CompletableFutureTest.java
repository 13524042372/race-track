package com.wangkun.thread.completablefuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

/**
 * 对 CompeletableFuture 基本Api 进行测试
 */
public class CompletableFutureTest {
    @Test
    public void test1() throws InterruptedException {
        System.out.println(Thread.currentThread().getId());
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenComplete((a,e) -> {
            System.out.println("whenComplete : " + Thread.currentThread().getId());

        });
        //System.out.println("执行完成");

        Thread.sleep(1000);
    }
}
