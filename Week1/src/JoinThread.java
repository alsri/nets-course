import java.util.ArrayList;

public class JoinThread implements Runnable{

	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread()+": Just started");
			Thread.sleep((System.currentTimeMillis()%10)*1000);
			System.out.println(Thread.currentThread()+": Done");
		} catch (InterruptedException e) {}
	}
	
	public static void main(String[] str) throws InterruptedException{
		System.out.println(Thread.currentThread()+": Main thread started");
		ArrayList<Thread> threads= new ArrayList<Thread>();
		for (int i=0;i<10;i++){
			Thread t= new Thread(new JoinThread());
			threads.add(t);
			t.start();
		}
		System.out.println(Thread.currentThread()+": Waiting on other threads");
		for (Thread t : threads){
			t.join();
		}
		System.out.println(Thread.currentThread()+": All threads finished");
	}
	

}
