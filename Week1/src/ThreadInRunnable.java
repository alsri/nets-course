import java.util.Random;

//creating a chain of threads
public class ThreadInRunnable implements Runnable {

	private double probability;
	
	public ThreadInRunnable(double probability){
		this.probability=probability;
	}
	
	public void run(){
		//with probability probability create a new thread
		System.out.println(Thread.currentThread().getId()+": Created");
		Random r = new Random(System.currentTimeMillis()*2000);
		double rn=r.nextDouble();
		//System.out.println(rn);
		if (rn<this.probability){
			System.out.println(Thread.currentThread().getId()+": Creating a new thread");
			ThreadInRunnable runnable= new ThreadInRunnable(this.probability*0.9);
			Thread t = new Thread(runnable);
			t.start();
			System.out.println(Thread.currentThread().getId()+": created thread "+t.getId());
		}
		else{
			System.out.println(Thread.currentThread().getId()+": Stoping the chain");
		}
		
	}
	
	public static void main(String[] args) {
		//start the chain with probability 1
		ThreadInRunnable runnable= new ThreadInRunnable(1.0);
		Thread t = new Thread(runnable);
		t.start();

	}

}
