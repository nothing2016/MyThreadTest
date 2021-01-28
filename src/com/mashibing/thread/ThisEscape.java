package com.mashibing.thread;

import java.awt.*;

/**
 * 展示this溢出的情况
 * 内部类对象隐式持有外部类对象，可能会发生内存泄漏问题。
 */
public class ThisEscape {
    private int intState;//外部类的属性，当构造一个外部类对象时，这些属性值就是外部类状态的一部分
    private String stringState;

    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                // 这个时候，可以将外部类的this发布出去的，但是的this这个对象才初始化了一半
                System.out.println(ThisEscape.this.getIntState());
                doSomething(e);
            }
        });

        //执行到这里时，new 的EventListener就已经把ThisEscape对象隐式发布了，而ThisEscape对象尚未初始化完成
        intState = 10;//ThisEscape对象继续初始化....
        stringState = "hello";//ThisEscape对象继续初始化....

        //执行到这里时, ThisEscape对象才算初始化完成...
    }

    /**
     * EventListener 是 ThisEscape的 非静态 内部类
     */
    public abstract class EventListener {
        public abstract void onEvent(Event e);
    }

    private void doSomething(Event e) {
    }

    public int getIntState() {
        return intState;
    }

    public void setIntState(int intState) {
        this.intState = intState;
    }

    public String getStringState() {
        return stringState;
    }

    public void setStringState(String stringState) {
        this.stringState = stringState;
    }
}