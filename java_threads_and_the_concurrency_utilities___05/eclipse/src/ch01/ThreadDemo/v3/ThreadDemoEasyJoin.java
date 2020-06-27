package ch01.ThreadDemo.v3;

public class ThreadDemoEasyJoin {
	public static void main(String[] args) throws InterruptedException {

		long start = System.currentTimeMillis();

		Runnable someTaskThatTakesTime = () -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Thread Interrupted: "+ e.getMessage());
			}
		};

		Thread t = new Thread(someTaskThatTakesTime);
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			System.err.println("Thread Interrupted: "+ e.getMessage());
		}

		long end = System.currentTimeMillis();

		System.out.println((end - start) / 1000.0);
	}

}
