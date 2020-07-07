package ch06.CountDownLatchDemo;

public class CustomCountDownLatchUtility {
	public static void main(String[] args) {
		CustomCountDownLatch countDownLatch = new CustomCountDownLatch(3);

		Runnable runnableCustomCDL = () -> {
			System.out.println("Working : " + Thread.currentThread().getName());
			countDownLatch.countDown();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Thread t1 = new Thread(runnableCustomCDL, "T1");
		Thread t2 = new Thread(runnableCustomCDL, "T2");
		Thread t3 = new Thread(runnableCustomCDL, "T3");

		t1.start();
		t2.start();
		t3.start();

		try {
			System.out.println("Waiting for all the threads to finish");
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Finished work load of all the threads.");

	}
}

class CustomCountDownLatch {
	int counter;

	public CustomCountDownLatch(int counter) {
		this.counter = counter;
	}

	public synchronized void await() throws InterruptedException {
		if (counter > 0) {
			wait();
		}
	}

	/**
	 * method will decrement the counter by 1 each time
	 */
	public synchronized void countDown() {
		counter--;
		if (counter == 0) {
			notifyAll();
		}
	}
}