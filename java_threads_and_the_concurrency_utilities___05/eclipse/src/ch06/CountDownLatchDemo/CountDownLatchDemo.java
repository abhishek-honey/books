package ch06.CountDownLatchDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
	final static int NTHREADS = 3;

	public static void main(String[] args) {
		final CountDownLatch startSignal = new CountDownLatch(1);
		final CountDownLatch doneSignal = new CountDownLatch(NTHREADS);

		Runnable r = () -> {
			try {
				report("Entered countdown latch run()");
				startSignal.await(); // wait until told to ...
				report("Countdown is doing some work"); // ... proceed
				doneSignal.countDown(); // reduce count on which main thread is waiting
			} catch (InterruptedException ie) {
			}
		};
		
		ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
		for (int i = 0; i < NTHREADS; i++)
			executor.execute(r);
		try {
			System.out.println("main thread doing something");
			Thread.sleep(1000); // sleep for 1 second
			startSignal.countDown(); // let all threads proceed
			System.out.println("main thread doing something else");
			doneSignal.await(); // wait for all threads to finish
			executor.shutdownNow();
		} catch (InterruptedException ie) {
			System.err.println(ie);
		}
	}

	static void report(String s) {
		System.out.println(System.currentTimeMillis() + ": " + Thread.currentThread() + ": " + s);
	}
}