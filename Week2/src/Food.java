import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Food {
	
	private ArrayList<String> food;
	private ReentrantLock lock;
	private Condition foodFull, foodEmpty; 
	
	public Food(){
		this.food=new ArrayList<String>();
		this.lock=new ReentrantLock();
		this.foodFull= this.lock.newCondition();
		this.foodEmpty= this.lock.newCondition();
	}
	
	public ReentrantLock getLock() {
		return lock;
	}

	public Condition getFoodEmpty() {
		return foodEmpty;
	}

	public Condition getFoodFull() {
		return foodFull;
	}

	public  boolean isEmpty() throws InterruptedException {
		
		this.lock.lockInterruptibly();
		boolean result=(food.size()==0);
		this.lock.unlock();
		return result;
	}
	
	public  boolean isFull() throws InterruptedException {
		
		this.lock.lockInterruptibly();
		boolean result=(food.size()==2);
		this.lock.unlock();
		return result;
	}

	public  void cook(String food) throws InterruptedException {
		this.lock.lockInterruptibly();
		this.food.add(food);
		this.lock.unlock();
	}
	
	public  void eat() throws InterruptedException{
		this.lock.lockInterruptibly();
		this.food.remove(this.food.size()-1);
		this.lock.unlock();
	} 



}
