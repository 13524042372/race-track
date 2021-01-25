package com.wangkun.thread.utils;

public class DeadLockCase {
    private static Object object1 = new Object();
    private static Object object2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (object1){
                System.out.println("Thread1 object1");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2){
                    System.out.println("Thread2 object2");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (object2){
                System.out.println("Thread2 object2");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1){
                    System.out.println("Thread1 object1");
                }
            }
        }).start();
    }

}
