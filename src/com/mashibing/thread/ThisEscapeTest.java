package com.mashibing.thread;

public class ThisEscapeTest {
    public static void main(String[] args) {
        EventSource eventSource = new EventSource();
        ThisEscape thisEscape = new ThisEscape(eventSource);
        ThisEscape.EventListener listener = eventSource.getListener();//this引用逸出
        listener.onEvent(null);
        thisEscape.setStringState("change thisEscape state...");
        
   		//--------演示一下内存泄漏---------//
        // 这里thisEscape = null ，但是listener这个内部类是被eventSource引用了，所以thisEscape被eventSource隐士的引用了，这里的this逸出
        // 就会导致内存泄漏
        thisEscape = null;//希望触发 GC 回收 thisEscape
//        consistentHold(listener);//但是在其他代码中长期持有listener引用
    }
}