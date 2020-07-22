package net.jcip.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Sequence
 *
 * @author Brian Goetz and Tim Peierls
 */

@ThreadSafe
public class Sequence {
    @GuardedBy("this") private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }
    

	public static void main(String[] args) {

		Sequence safeSequence = new Sequence();
		Runnable runnable = () -> {
			System.out.println(safeSequence.getNext());
		};

		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executor.submit(runnable);
		}
		executor.shutdown();
	}
}
