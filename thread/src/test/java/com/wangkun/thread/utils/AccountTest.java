package com.wangkun.thread.utils;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class AccountTest {
    static Accout[] accouts = new Accout[10];
    static {
        for(int i = 0; i < 10 ; i++){
            accouts[i] = new  Accout(10000000);
        }
    }
    @Test
    public void accountTest(){
        for (int i = 0; i < 100; i ++){
            int finalI = i;
            new Thread( () -> {
                Random random = new Random(finalI);
                int from = random.nextInt(10);
                int to = random.nextInt(10);
                while(from == to){
                    to = random.nextInt(10);
                }
                AccountService.transfMoneySyn(accouts[from],accouts[to],1);


            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
