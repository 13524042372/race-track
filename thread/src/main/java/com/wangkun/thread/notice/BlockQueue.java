package com.wangkun.thread.notice;

public class BlockQueue<T> {
    private int length = 100;

    private Object[] t = new Object[length];

    private int index = 1;

    /**
     *
     * @param t
     * @throws InterruptedException
     */
    public synchronized void add(T t) throws InterruptedException {
        if(isFull()){
            wait();
        }
        addElement(t);
        notifyAll();
    }

    private void addElement(T t){
        this.t[index++] = t;
    }

    public synchronized <T> T get() throws InterruptedException {
        if(isEmpty()){
            wait();
        }
        T t1 = getElement();
        notifyAll();
        return t1;
    }
    private <T> T getElement(){
        return (T)this.t[--index];
    }
    private synchronized boolean isEmpty(){
        return index == 1;
    }

    private boolean isFull(){
        return index == length + 1;
    }
}
