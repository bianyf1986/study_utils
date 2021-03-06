package com.afeng.utils.javaBasis.multiThread.countDownLatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo { 
	
    final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    
    public static void main(String[] args) throws InterruptedException {
    	/*
    	 * 构造方法参数指定了计数的次数:
    	 * 两个工人的协作 
    	 */
        CountDownLatch latch=new CountDownLatch(2);
        Worker worker1=new Worker("zhang san", 5000, latch);  
        Worker worker2=new Worker("li si", 8000, latch);  
        worker1.start(); 
        worker2.start();
        /*
         * awaint方法，调用此方法会一直阻塞当前线程，直到计时器的值为0:
         * 等待所有工人完成工作  
         */
        latch.await();
        System.out.println("all work done at "+sdf.format(new Date()));  
    }  
      
      
    static class Worker extends Thread{  
        String workerName;   
        int workTime;  
        CountDownLatch latch;  
        public Worker(String workerName ,int workTime ,CountDownLatch latch){  
             this.workerName=workerName;  
             this.workTime=workTime;  
             this.latch=latch;  
        }  
        public void run(){  
            System.out.println("Worker "+workerName+" do work begin at "+sdf.format(new Date()));  
            doWork();//工作了  
            System.out.println("Worker "+workerName+" do work complete at "+sdf.format(new Date()));  
            /*
             * countDown方法，当前线程调用此方法，则计数减一:
             * 工人完成工作，计数器减一  
             */
            latch.countDown();
  
        }  
          
        private void doWork(){  
            try {  
                Thread.sleep(workTime);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}  