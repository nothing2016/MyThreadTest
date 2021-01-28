package com.mashibing.thread;

public  class EventSource {
    ThisEscape.EventListener listener;//EventSource对象 持有外部类ThisEscape的 内部类EventListener 的引用
    public ThisEscape.EventListener getListener() {
        return listener;
    }
    public void registerListener(ThisEscape.EventListener listener) {
        this.listener = listener;
    }
}