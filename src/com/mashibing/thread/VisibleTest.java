package com.mashibing.thread;

/**
 *
 * 可见性测试
 * @author oudaming
 * @date 2021-01-26 10:32
 */
public class VisibleTest {

    public static void main(String[] args) {
        DataHandler dataHandler = new DataHandler();

        Thread subThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 100000;
                while(i-- > 0){
                    dataHandler.sub();
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                dataHandler.print();
            }
        },"减少线程");
        subThread.start();

        Thread addThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 100000;
                while(i-- > 0){
                    dataHandler.add();
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dataHandler.print();
            }
        },"添加线程");
        addThread.start();


        Thread printThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 10;
                while(i-- > 0){
                    dataHandler.print();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        },"printThread");
        printThread.start();



    }


    private static class Data{
        public int value;
        public static final Data instance = new Data();
    }

    /**
     * synchronized一旦同步在方法上，那么方法内的静态实例Data.instance 也会被同步，如果没有synchronized来修饰方法，
     * 那么值就会全部乱掉
     */
    private static class DataHandler{


        public synchronized int add(){
            Data.instance.value ++;
//            this.data.value = this.data.value + 1;
//            System.out.println(Thread.currentThread().getName() + "," + this.data.value);
            return Data.instance.value;
        }

        public synchronized int sub(){
            Data.instance.value --;
//            this.data.value = this.data.value - 1;
//            System.out.println(Thread.currentThread().getName() + "," + this.data.value);
            return Data.instance.value;
        }

        public synchronized void print(){
            System.out.println(Thread.currentThread().getName() + "," + Data.instance.value);
        }
    }

}


