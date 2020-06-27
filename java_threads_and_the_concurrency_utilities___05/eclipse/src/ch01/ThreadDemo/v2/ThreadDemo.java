package ch01.ThreadDemo.v2;

public class ThreadDemo {
	public static void main(String[] args) throws InterruptedException {
		Runnable runnableInterrupt = () -> {
			String name = Thread.currentThread().getName();
			int count = 0;
			while (!Thread.interrupted())
				System.out.println(name + ": " + count++);
		};

		Thread thdA = new Thread(runnableInterrupt);
		Thread thdB = new Thread(runnableInterrupt);
		thdA.start();
		thdB.start();

//		Thread.currentThread().sleep(1);
		
		while (true) {
			double n = Math.random();
			if (n >= 0.4 && n <= 0.5)
				break;
		}

		thdA.interrupt();
		thdB.interrupt();
	}
}