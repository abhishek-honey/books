package ch04.ThreadLocalDemo;

public class ThreadLocalDemo {
	private static volatile ThreadLocal<String> userID = new ThreadLocal<>();

	public static void main(String[] args) {
		Runnable r = () -> {
			String name = Thread.currentThread().getName();
			if (name.equals("A"))
				userID.set("foxtrot");
			else
				userID.set("charlie");
			System.out.println(name + " " + userID.get());
		};
		
		Thread thdA = new Thread(r);
		thdA.setName("A");
		Thread thdB = new Thread(r);
		thdB.setName("B");
		thdA.start();
		thdB.start();
	}
}