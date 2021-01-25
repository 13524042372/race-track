package com.wangkun.thread.utils;

public class AccountService {

    /**
     * 用synchronized 来进行加锁操作
     *  容易产生死锁
     * @param fromAccount
     * @param toAccount
     * @param num
     */
    public static  void transfMoneySyn(Accout fromAccount,Accout toAccount,long num){
        synchronized (fromAccount){
            synchronized (toAccount) {
                fromAccount.delBalance(num);
                toAccount.addBalance(num);
            }
        }
    }

    public static void transfMoneyByTryWhile(Accout fromAccount,Accout toAccount,long num){
        for(;;){

        }
    }
}
