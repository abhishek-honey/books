package ch08.PC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueTest {

	public static void main(String[] args) {
		final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(1);

		Runnable producer = () -> {
			try {
				String name = Thread.currentThread().getName();
				Integer i = (int) (Math.random() * 10);
				System.out.println(name + " producing " + i);
				blockingQueue.put(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Runnable consumer = () -> {
			try {
				String name = Thread.currentThread().getName();
				Integer take = blockingQueue.take();
				System.out.println(name + " consuming " + take);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Thread tp1 = new Thread(producer);
		Thread tp2 = new Thread(producer);
		Thread tp3 = new Thread(producer);
		Thread tp4 = new Thread(producer);

		Thread tc1 = new Thread(consumer);
		Thread tc2 = new Thread(consumer);
		Thread tc3 = new Thread(consumer);
		Thread tc4 = new Thread(consumer);

		final ExecutorService executor = Executors.newFixedThreadPool(10);
		executor.execute(tp1);
		executor.execute(tp2);
		executor.execute(tp3);
		executor.execute(tp4);

		executor.execute(tc1);
		executor.execute(tc2);
		executor.execute(tc3);
		executor.execute(tc4);

		executor.shutdown();
	}

}
