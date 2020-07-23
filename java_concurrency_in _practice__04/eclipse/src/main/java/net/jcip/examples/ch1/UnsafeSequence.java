package net.jcip.examples.ch1;

import net.jcip.annotations.*;

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
     * @throws InterruptedException 
     */
    public int getNext() throws InterruptedException {
        Thread.currentThread().sleep(100);
        return value++;
    }
    
    public static void main(String[] args) {
    	UnsafeSequence unsafeSequence = new UnsafeSequence();
		Runnable runnable = () -> {
			int i = -1;
			try {
				i = unsafeSequence.getNext();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i);
		};
		
		for(int i = 0 ; i < 1000 ; i++) {
			Thread t1 = new Thread(runnable);
			t1.start();
		}
	}
}
