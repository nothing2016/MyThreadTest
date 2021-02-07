package com.mashibing.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者和消费者的演示，必须使用while和 notifyAll 原因参考：  https://www.jianshu.com/p/25e243850bd2?appinstall=0
 *
 * 如果使用if(Buffer.mBuf.isFull()),会导致IndexOutOfBoundsException()异常
 * 如果使用notify则很可能会导致死锁
 *
 */
public class ProduceConsume {

    private final Buffer buffer = new Buffer();
    public void produce() {
        synchronized (this) {
            // 这里锁住了this,buffer是this的一个成员变量，当然也就锁住了，并保持了可见性
            while (buffer.isFull()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            buffer.add();
            notifyAll();
        }
    }

    public void consume() {
        synchronized (this) {
            while (buffer.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            buffer.remove();
            notifyAll();
        }
    }

    public static void main(String[] args) {
        ProduceConsume sth = new ProduceConsume();
        Runnable runProduce = new Runnable() {
            int count = 100;

            @Override
            public void run() {
                while (count-- > 0) {
                    System.out.println(Thread.currentThread() + "生产者 count = " + count);
                    sth.produce();
                }
            }
        };
        Runnable runConsume = new Runnable() {
            int count = 100;

            @Override
            public void run() {
                while (count-- > 0) {
                    System.out.println(Thread.currentThread() + "消费者 count = " + count);
                    sth.consume();
                }
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runConsume).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(runProduce).start();
        }
    }
}