package ch02.ID.v2;

import java.util.Date;

public class ID {
	private int counter; // initialized to 0 by default

	public synchronized int getID() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print(new Date()+"\n");
		return counter++;
	}

	public static void main(String[] args) {
		ID id = new ID();
		Runnable getID = () -> {
			System.out.println(id.getID());
		};
		
		Thread t1 = new Thread(getID);
		Thread t2 = new Thread(getID);
		
		t1.start();
		t2.start();
//		System.out.println(id.getID());
	}
}