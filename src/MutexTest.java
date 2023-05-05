import java.util.concurrent.Semaphore;
public class MutexTest {
	static Semaphore semaphore = new Semaphore(1);

	static class MyLockerThread extends Thread {

		String name = "";

		MyLockerThread(String name) {
			this.name = name;
		}

		public void run() {

			try {

				System.out.println(name + " : liberando bloqueio...");
				System.out.println(name + " : permissão de semafaro disponivel agora: " 
								+ semaphore.availablePermits());

				semaphore.acquire();
				System.out.println(name + " : obteve a permissão!");

				try {

					for (int i = 1; i <= 5; i++) {

						System.out.println(name + " : realizando operação " + i 
								+ ", permissão de semafaro disponivel agora : "
								+ semaphore.availablePermits());

						// sleep 1 second
						Thread.sleep(1000);

					}

				} finally {

					// calling release() after a successful acquire()
					System.out.println(name + " : liberando bloqueio...");
					semaphore.release();
					System.out.println(name + " : permissão de semafaro disponivel agora: " 
								+ semaphore.availablePermits());

				}

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		}
	}
	public static void main(String[] args) {

		System.out.println("Total de licenças de semáforo disponíveis : " 
				+ semaphore.availablePermits());

		MyLockerThread t1 = new MyLockerThread("A");
		t1.start();

		MyLockerThread t2 = new MyLockerThread("B");
		t2.start();

		MyLockerThread t3 = new MyLockerThread("C");
		t3.start();

		MyLockerThread t4 = new MyLockerThread("D");
		t4.start();

		MyLockerThread t5 = new MyLockerThread("E");
		t5.start();

		MyLockerThread t6 = new MyLockerThread("F");
		t6.start();

	}
	
}
