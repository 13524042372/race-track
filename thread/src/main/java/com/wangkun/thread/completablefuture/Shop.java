package com.wangkun.thread.completablefuture;

import java.util.Random;

public class Shop {
    private String name;

    public Shop(String name){
        this.name = name;
    }
    Random random = new Random();
    public double getPrice() {
        return calculate(name);
    }
    private void delay(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private double calculate(String productName){
        delay();
        return random.nextDouble() * productName.charAt(0) + productName.charAt(1);
    }

    public String getName() {
        return name;
    }

}
