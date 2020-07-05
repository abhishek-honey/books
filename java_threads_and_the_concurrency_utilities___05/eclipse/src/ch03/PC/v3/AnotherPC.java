package ch03.PC.v3;

public class AnotherPC {
	volatile int i = 1;
	volatile Character c = 'a';
	volatile boolean state = true;

	synchronized void printAlphabet() {
		try {
			while (!state) {
				wait();
			}
		} catch (InterruptedException e) {
		}
		System.out.println(Thread.currentThread().getName() + "\t:\t" + c);
		state = false;
		c++;
		notifyAll();
	}

	synchronized void printNumbers() {
		try {
			while (state) {
				wait();
			}
		} catch (InterruptedException e) {
		}
		System.out.println(Thread.currentThread().getName() + "\t:\t" + i);
		state = true;
		i++;
		notifyAll();
	}

	public static void main(String[] args) {
		AnotherPC threadClass = new AnotherPC();

		Runnable runnable1 = () -> {
			int i = 0;
			while (i < 26) {
				threadClass.printAlphabet();
				i++;
			}

		};

		Runnable runnable2 = () -> {
			int i = 0;
			while (i < 26) {
				threadClass.printNumbers();
				i++;
			}

		};

		Thread t1 = new Thread(runnable1);
		t1.setName("Thread_Alphabet");

		Thread t2 = new Thread(runnable2);
		t2.setName("Thread_Number");

		t1.start();

		t2.start();
	}
}