package com.mashibing.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个手写的缓存对象，没有任何同步
 * @author oudaming
 * @date 2021-01-21 18:12
 */
public class Buffer {
    public static Buffer mBuf = new Buffer();
    private static final int MAX_CAPACITY = 1;
    private List innerList = new ArrayList<>(MAX_CAPACITY);

    void add() {
        if (isFull()) {
            throw new IndexOutOfBoundsException();
        } else {
            innerList.add(new Object());
        }
        System.out.println(Thread.currentThread().toString() + " add");

    }

    void remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        } else {
            innerList.remove(MAX_CAPACITY - 1);
        }
        System.out.println(Thread.currentThread().toString() + " remove");
    }

    boolean isEmpty() {
        return innerList.isEmpty();
    }

    boolean isFull() {
        return innerList.size() == MAX_CAPACITY;
    }
}
