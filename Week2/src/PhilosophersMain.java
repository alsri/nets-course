import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class PhilosophersMain {

	public static void main(String[] args) {
		int N=5;

		Lock[] chopsticks = new ReentrantLock[N];
		Philosopher[] phils= new Philosopher[N];
		Thread[] threads = new Thread[N];
		try{
			//first create the chopsticks
			for (int i=0;i<N;i++)
				chopsticks[i]= new ReentrantLock();
			
			//then create the philosophers with 2 chopsticks each
			for (int i=0;i<N;i++){
				phils[i]= new Philosopher(chopsticks[i], chopsticks[(i+1)%N]);
				threads[i]= new Thread(phils[i]);
			}
			for (Thread t: threads){
				t.start();
			}
			//After a while stop all philosophers
			Thread.sleep(2000);
			for (Thread t: threads){
				t.interrupt();
			}
			
		}catch (InterruptedException e){}
		
	}

}
