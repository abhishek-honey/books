package ch01.ThreadDemo.v1;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo
{
	static AtomicInteger atomicInteger = new AtomicInteger(0);
   public static void main(String[] args)
   {
	   System.out.println(Runtime.getRuntime().toString());
      Runnable r = () -> {
                         Thread thd = Thread.currentThread();
                         while (atomicInteger.addAndGet(1) < 20){
                            System.out.printf("%s is %salive and in %s " +
                                              "state.%n",
                                              thd.getName(), 
                                              thd.isAlive() ? "" : "not ", 
                                              thd.getState());
                         }
                   };
                   
      atomicInteger.getAndSet(0);
      var t1 = new Thread(r, "Thread_1");
      System.out.printf("%s is %salive and in %s state%n",
                        t1.getName(), 
                        t1.isAlive() ? "" : "not ", 
                        t1.getState());
      
      var t2 = new Thread(r);
      t2.setName("Thread_2");
      
      System.out.printf("%s is %salive and in %s state%n",
                        t2.getName(), 
                        t2.isAlive() ? "" : "not ", 
                        t2.getState());
      t1.start();
      t2.start();
   }
}