package com.wangkun.thread.utils;

public class Accout {
    private long balance;

    public Accout(){
        balance = 100;
    }
    public Accout(long balance){
        this.balance = balance;
    }
    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {

        this.balance = balance;
    }

    public void delBalance(long num){
        if(balance - num < 0){
            return;
        }
        balance -= num;
    }

    public void addBalance(long num){
        balance += num;
    }
}
