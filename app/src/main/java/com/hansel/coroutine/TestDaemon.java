package com.hansel.coroutine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 守护线程test
 */
public class TestDaemon {

    public static void main(String[] args) throws InterruptedException {

        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ExecutorService pool = Executors.newCachedThreadPool();
                Thread childThread = new Thread(new ChildRunnable(Thread.currentThread()));
                //thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。
                // 你不能把正在运行的常规线程设置为守护线程
                childThread.setDaemon(true);
                childThread.start();
                // 包一层Executors,会把守护线程调整为用户线程
                pool.execute(childThread);
                pool.shutdown();
                System.out.println(" I m " + Thread.currentThread().getName() + " ... ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        mainThread.start();

        //不是说当子线程是守护线程，主线程结束，子线程就跟着结束，
        // 这里的前提条件是：当前jvm应用实例中没有用户线程继续执行，如果有其他用户线程继续执行，那么后台线程不会中断
        // 例如下面的用户Thread继续执行，childThread就不会停止
       Thread otherUserThread = new Thread(new Runnable() {
           @Override
           public void run() {
               while (true){
                   System.out.println(" i am other thread ");
                   try {
                       TimeUnit.MILLISECONDS.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
       });
//       otherUserThread.start();
    }
}

class ChildRunnable implements Runnable {

    private final Thread mainThread;

    // 传入mainThread实例
    public ChildRunnable(Thread mainThread){
        this.mainThread = mainThread;
    }

    @Override
    public void run() {
        // 如果需要在主线程结束时停止子线程，使用判断isActive
        while (true/*mainThread.isAlive()*/) {
            try {
                System.out.println(" I m Child Thread ...");
                // 包装Thread.sleep()
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
