package com.wangkun.thread.notice;

import org.junit.jupiter.api.Test;

public class NoticeTest {
    @Test
    public void noticeBlockTest() throws InterruptedException {
        BlockQueue<String> queue = new BlockQueue<>();

        new Thread(() -> {
            try {
                System.out.println( Thread.currentThread().getName()  + "waiting");

                System.out.println( Thread.currentThread().getName()  + " " + queue.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println( Thread.currentThread().getName()  + "start");
                Thread.sleep(10000);
                queue.add("1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(100000);
    }
}
