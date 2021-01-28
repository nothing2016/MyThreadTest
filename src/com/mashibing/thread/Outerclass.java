package com.mashibing.thread;

public class Outerclass {
    static class Inner{
        public void voice(){
            new Outerclass().test();
        }
    }
    public void test(){
        System.out.println("test()");
    }
    public static void main(String[] args) {//主函数调用voice()方法
        new Inner().voice();
    }
}