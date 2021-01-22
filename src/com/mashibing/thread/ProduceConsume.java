package com.mashibing.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者和消费者的演示，必须使用while和 notifyAll 原因参考：  https://www.jianshu.com/p/25e243850bd2?appinstall=0
 *
 * 如果使用if(Buffer.mBuf.isFull()),会导致IndexOutOfBoundsException()异常
 * 如果使用notify则很可能会导致死锁
 */
public class ProduceConsume {

    public void produce() {
        synchronized (this) {
            // 这里的Buffer没有任何的同步，怎么保证mBuf对象的可见性的呢？
            // 因为目前代码是正常的，所以只能先理解为: 只要synchronized修饰的方法或方法块，里面的所有对象数据都会进行同步，这样就保证了可见性
            while (Buffer.mBuf.isFull()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Buffer.mBuf.add();
            notifyAll();
        }
    }

    public void consume() {
        synchronized (this) {
            while (Buffer.mBuf.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Buffer.mBuf.remove();
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