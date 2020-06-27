package ch02.ID.v4;

import java.util.Date;

public class IDStatic {
	private static int counter; // initialized to 0 by default

	public static synchronized int getID() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print(new Date() + "\n");
		return counter++;
	}

	public static void main(String[] args) {
		IDStatic id = new IDStatic();
		Runnable getID = () -> {
			System.out.println(IDStatic.getID());
		};

		Thread t1 = new Thread(getID);
		Thread t2 = new Thread(getID);

		t1.start();
		t2.start();
		System.out.println(IDStatic.getID());
	}
}