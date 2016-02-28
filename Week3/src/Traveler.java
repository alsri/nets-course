import java.util.Random;

public class Traveler implements Runnable{
	private int id;
	public Traveler(int id){
		this.id=id;
	}
	private void print(String message){
		System.out.println(System.currentTimeMillis()+" Traveler "+this.id+
				" in thread "+Thread.currentThread().getId()+": "+message);
	}
	
	@Override
	public void run() {
		Random rand = new Random(System.currentTimeMillis());
		try{
			this.print("I am buying my ticket");
			Thread.sleep(rand.nextInt(1000));
			this.print("Got my ticket");
		}catch (InterruptedException e){
			this.print("Something went wrong");
		}	
	}
	
}
