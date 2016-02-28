
public class Child implements Runnable {

	Food food;

	public Child(Food food){
		this.food=food;
	}
	private void print(String message){
		System.out.println(System.currentTimeMillis()/1000+ "- Child"
	+Thread.currentThread().getId()+": "+message);
	}
	@Override
	public void run() {
		try{
			this.print("Just born");
			while(true){
				this.print("I am hungry, I want to eat.");
				this.food.getLock().lockInterruptibly();
				while (this.food.isEmpty()){
					this.print("There is no food :(");
					food.getFoodFull().await();
				}
				this.print("Yay, eating now :)");
				food.eat();
				food.getFoodEmpty().signal();
				this.food.getLock().unlock();
				this.print("Going back to sleep");
				Thread.sleep(1000);
			}
		}catch (InterruptedException e){
			this.print("Interrupted");
			return;
		}
	}	
}
