package ch05.CalculateE.v2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CalculateE {
	final static int LASTITER = 17;

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(1);

		Callable<Boolean> callable = () -> {
			try {
//				Time taking task
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.err.println("InterruptedException while waiting");
			}
			return true;
		};

		Future<Boolean> taskFuture = executor.submit(callable);

//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

			try {
				taskFuture.get(1000, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				System.err.println("InterruptedException while waiting");
			} catch (ExecutionException e) {
				System.err.println("ExecutionException while waiting");
			} catch (TimeoutException e) {
				System.err.println("TimeoutException while waiting");
			}

		try {
			System.out.println(taskFuture.get());
		} catch (ExecutionException ee) {
			System.err.println("task threw an exception");
			System.err.println(ee);
		} catch (InterruptedException ie) {
			System.err.println("interrupted while waiting");
		}
		
		executor.shutdownNow();
	}
}
