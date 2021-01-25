package com.leeCode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author oudaming
 * @date 2021-01-25 13:55
 */
public class _1114PrintInOrder {

    class Foo {

        private Lock lock = new java.util.concurrent.locks.ReentrantLock();
        private Condition condition1 = lock.newCondition();
        private Condition condition2 = lock.newCondition();
        private Condition condition3 = lock.newCondition();
        private int should = 1;

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {

            lock.lock();
            try{
                while(should != 1){
                    condition1.await();
                }
                // printFirst.run() outputs "first". Do not change or remove this line.
                printFirst.run();
                should = 2;
                condition2.signal();
            }finally {
                lock.unlock();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            lock.lock();
            try{
                while(should != 2){
                    condition2.await();
                }
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                should = 3;
                condition3.signal();
            }finally {
                lock.unlock();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            lock.lock();
            try{
                while(should != 3){
                    condition3.await();
                }
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
                should = 1;
                condition1.signal();
            }finally {
                lock.unlock();
            }
        }
    }
}
