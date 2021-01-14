package com.mashibing.thread;

public class TestThreadGroup {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        ThreadGroup tg1=new ThreadGroup("thread_group1");  
        Thread thread1=new Thread(tg1,"thread1");  
          
        Thread thread2=new Thread(tg1,"thread2");  
          
        tg1.list();  
          
        tg1.setDaemon(true);  
        System.out.println(tg1.getName()+"的父线程组是"+tg1.getParent().getName());  
          
        thread1.start();  
        Thread[] threadArray1=new Thread[tg1.activeCount()];  
          
        tg1.enumerate(threadArray1,false);  
          
        System.out.println(tg1.getName()+"的活动线程有"+threadArray1.length);  
          
        ThreadGroup[] threadGroupArray1=new ThreadGroup[tg1.activeGroupCount()];  
        tg1.enumerate(threadGroupArray1, false);  
          
        System.out.println(tg1.getName()+"的活动线程组有"+threadGroupArray1.length);  
          
          
        ThreadGroup tg2=new ThreadGroup(tg1,"thread_group2");  
        Thread thread3=new Thread(tg2,"thread3");  
        Thread thread4=new Thread(tg2,"thread4");  
          
        tg2.list();  
          
        tg2.setDaemon(true);  
        System.out.println(tg2.getName()+"的父线程组是"+tg2.getParent().getName());  
          
        Thread[] threadArray2=new Thread[tg2.activeCount()];  
          
        tg2.enumerate(threadArray2,false);  
        System.out.println(tg2.getName()+"的活动线程有"+threadArray2.length);  
          
        ThreadGroup[] threadGroupArray2=new ThreadGroup[tg2.activeGroupCount()];  
        tg2.enumerate(threadGroupArray2, false);  
          
        System.out.println(tg2.getName()+"的活动线程组有"+threadGroupArray2.length);  
          
    }  
  
}