import java.util.Random;
import java.util.concurrent.Semaphore;

public class Traveler implements Runnable{
	private Semaphore ticketCounters;
	
	public Traveler(Semaphore ticketCounters){
		this.ticketCounters=ticketCounters;
	}
	
	private void print(String message){
		System.out.println(System.currentTimeMillis()+" Traveler "+Thread.currentThread().getId()+": "+message);
	}
	
	@Override
	public void run() {
		Random rand = new Random(System.currentTimeMillis());
		try{
			this.print("I am a new traveler");
			this.ticketCounters.acquire();
			this.print("I am buying my ticket");
			Thread.sleep(rand.nextInt(1000));
			this.print("Got my ticket");
			this.ticketCounters.release();
			this.print("Done, getting on board");
		}catch (InterruptedException e){
			this.print("Something went wrong");
		}	
	}
	
}
