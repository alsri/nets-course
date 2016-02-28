import java.util.concurrent.locks.Lock;

public class Philosopher implements Runnable{

	private Lock leftChopstick, rightChopstick;
	
	public Philosopher(Lock left, Lock right){
		this.leftChopstick=left;
		this.rightChopstick=right;
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
	
//	private void eat() throws InterruptedException{
//		this.print("Picking up chopsticks");
//		//first pick up the chopsticks, first left then right
//		this.leftChopstick.lockInterruptibly();
//
//		this.rightChopstick.lockInterruptibly();
//		this.print("Starting to eat");
//		//Thread.sleep((System.nanoTime()%10)*1000);
//		Thread.sleep(5000);
//		this.print("Finished eating");
//		//release the chopsticks
//		this.rightChopstick.lockInterruptibly();
//		this.leftChopstick.lockInterruptibly();
//		this.print("Left chopsticks");
//		
//	}
	
	private void eat() throws InterruptedException{
		boolean ate=false;
		while (!ate){
			//first pick up the chopsticks, first left then right
			if(this.leftChopstick.tryLock()){
				this.print("Picked up left chopstick");
				if (this.rightChopstick.tryLock()){
					this.print("Picked up right chopstick");
					//Thread.sleep((System.nanoTime()%10)*1000);
					ate=true;
					this.print("Eating");
					Thread.sleep(5000);
					this.rightChopstick.unlock();
					this.print("Dropped right chopstick");
				}
				else
				{
					this.print("Right chopstick not available.");
				}
				this.leftChopstick.unlock();
				this.print("Dropped left chopstick");
			}
			else
			{
				this.print("Left chopstick not available");
			}
			Thread.sleep(100);
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
