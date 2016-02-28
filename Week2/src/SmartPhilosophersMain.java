import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SmartPhilosophersMain {

	public static void main(String[] args) {
		int N=5;

		Chopstick[] chopsticks = new Chopstick[N];
		SmartPhilosopher[] phils= new SmartPhilosopher[N];
		Thread[] threads = new Thread[N];
		try{
			//first create the chopsticks
			for (int i=0;i<N;i++)
				chopsticks[i]= new Chopstick(i);
			
			//then create the philosophers with 2 chopsticks each
			for (int i=0;i<N;i++){
				phils[i]= new SmartPhilosopher(chopsticks[i], chopsticks[(i+1)%N]);
				threads[i]= new Thread(phils[i]);
			}
			for (Thread t: threads){
				t.start();
			}
			//After a while stop all philosophers
			Thread.sleep(40000);
			for (Thread t: threads){
				t.interrupt();
			}
			
		}catch (InterruptedException e){}
	
	}

}
