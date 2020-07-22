package net.jcip.annotations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * UnsafeSequence
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class UnsafeSequence {
	private int value;

	/**
	 * Returns a unique value.
	 */
	public int getNext() {
		return value++;
	}

	public static void main(String[] args) {

		UnsafeSequence unsafeSequence = new UnsafeSequence();
		Runnable runnable = () -> {
			System.out.println(unsafeSequence.getNext());
		};

		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executor.submit(runnable);
		}
		executor.shutdown();
	}
}
