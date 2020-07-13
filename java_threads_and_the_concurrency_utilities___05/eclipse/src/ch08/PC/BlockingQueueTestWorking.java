package ch08.PC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueTestWorking {

	public static void main(String[] args) {
		final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(1);

		Runnable producer = () -> {
			try {
				int iterator = 0;
				while (true) {
					if (iterator++ == 10)
						break;

					String name = Thread.currentThread().getName();
					Integer i = (int) (Math.random() * 10);
					blockingQueue.put(i);
					System.out.println(name + " Producing:-> " + i);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Runnable consumer = () -> {
			try {
				int iterator = 0;
				while (true) {
					if (iterator++ == 10)
						break;
					String name = Thread.currentThread().getName();
					Integer take = blockingQueue.take();
					System.out.println(name + " Consuming:<- " + take);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Thread threadProducer = new Thread(producer);


		final ExecutorService executor = Executors.newFixedThreadPool(10);
		executor.execute(threadProducer);
		
		Thread threadConsumer = new Thread(consumer);
		executor.execute(threadConsumer);

		executor.shutdown();
	}

}

//A produced by producer.
//A consumed by consumer.
//B consumed by consumer.
//B produced by producer.
//C produced by producer.
//C consumed by consumer.
//D consumed by consumer.
//D produced by producer.
//E produced by producer.
//E consumed by consumer.
//F consumed by consumer.
//F produced by producer.
//G produced by producer.
//G consumed by consumer.
//H produced by producer.
//H consumed by consumer.
//I produced by producer.
//I consumed by consumer.
//J consumed by consumer.
//J produced by producer.
//K consumed by consumer.
//K produced by producer.
//L consumed by consumer.
//L produced by producer.
//M consumed by consumer.
//M produced by producer.
//N consumed by consumer.
//N produced by producer.
//O produced by producer.
//P produced by producer.
//O consumed by consumer.
//P consumed by consumer.
//Q consumed by consumer.
//Q produced by producer.
//R produced by producer.
//R consumed by consumer.
//S consumed by consumer.
//S produced by producer.
//T produced by producer.
//T consumed by consumer.
//U consumed by consumer.
//U produced by producer.
//V produced by producer.
//V consumed by consumer.
//W consumed by consumer.
//W produced by producer.
//X produced by producer.
//X consumed by consumer.
//Y consumed by consumer.
//Y produced by producer.
//Z produced by producer.
//Z consumed by consumer.

