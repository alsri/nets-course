import java.util.concurrent.locks.Lock;

public class SmartPhilosopher implements Runnable{

	private Chopstick firstChopstick, secondChopstick;
	
	public SmartPhilosopher(Chopstick c1, Chopstick c2){
		if (c1.getPriority()>=c2.getPriority()){
			this.firstChopstick=c1;
			this.secondChopstick=c2;
		}
		else{
			this.firstChopstick=c2;
			this.secondChopstick=c1;
		}
	}
	
	public void print(String message){
		System.out.println(System.currentTimeMillis()+"-Philosopher "+Thread.currentThread().getId()+": "+message);
	}
	
	private void meditate() throws InterruptedException{
		this.print("Starting to meditate");
		//Thread.sleep((System.nanoTime()%10)*1000);
		Thread.sleep(5000);
		this.print("Finished meditating");
	}
	
	private void eat() throws InterruptedException{
		
		//first pick up first chopstick, then second 
		this.firstChopstick.lockInterruptibly();
		try{
			this.print("Got one chopstick");
			this.secondChopstick.lockInterruptibly();
			try{
				this.print("Got second chopstick");
				this.print("Starting to eat");
				//Thread.sleep((System.nanoTime()%10)*1000);
				Thread.sleep(5000);
				this.print("Finished eating");
			} finally{
			//release the chopsticks
				this.secondChopstick.unlock();
				this.print("Releasing second chopstick");
			}
		} finally {
			this.firstChopstick.unlock();
			this.print("Releasing first chopstick");
		}
		
	}
	
	@Override
	public void run() {
		try{
			while (true){
				//alternate between meditate and eat until interrupted
				this.eat();
				this.meditate();				
			}
		} catch (InterruptedException e){this.print("Interrupted, finishing"); return;}
		
	}

}
