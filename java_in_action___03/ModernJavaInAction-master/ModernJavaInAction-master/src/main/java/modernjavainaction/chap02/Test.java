package modernjavainaction.chap02;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import modernjavainaction.chap02.FilteringApples.Apple;

public class Test {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> threadName = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return Thread.currentThread().getName();
			}
		});
		
		String futureValue = threadName.get();
		System.out.println(futureValue);
		
	}
	
	

}
