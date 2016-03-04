import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Nanny implements Runnable{

	Food food;
	
	
	public Nanny(Food food){
		this.food=food;
	}
	
	public static void main(String[] args) {
		try{
			Food food= new Food();
			ArrayList<Thread> threads= new ArrayList<Thread>();
			for (int i=0 ;i<3;i++){
				threads.add(new Thread(new Nanny(food)));
			}
			for (int i=0 ;i<4;i++){
				threads.add(new Thread(new Child(food)));
			}
			for (Thread t : threads)
				t.start();
			Thread.sleep(20000);
			for (Thread t : threads)
				t.interrupt();
		} catch(InterruptedException e){}
		
	}

	private void print(String message){
		System.out.println(System.currentTimeMillis()/1000+ " - Nanny "
	+Thread.currentThread().getId()+": "+message);
	}
	
	@Override
	public void run() {
		try{
			this.print("Created");
			while(true){
				this.food.getLock().lockInterruptibly();
				try{
					while(this.food.isFull()){
						this.print("Waiting for one baby to eat");
						this.food.getFoodEmpty().await();
					}
					this.print("Plate empty, cooking");
					this.food.cook("Soup");
					this.food.getFoodFull().signal();
				} finally{
					this.food.getLock().unlock();
				}
				this.print("Now resting a bit");
				Thread.sleep(1000);
			}
		}catch (InterruptedException e){
			this.print("Interrupted");
			return;
		}
	}
}

