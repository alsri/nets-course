
public class Child implements Runnable {

	Food food;

	public Child(Food food){
		this.food=food;
	}
	private void print(String message){
		System.out.println(System.currentTimeMillis()/1000+ "- Child: "+message);
	}
	@Override
	public void run() {
		try{
			this.print("Just born");
			while(true){
				this.print("I am hungry, I want to eat.");
				synchronized(food){
					while (this.food.isEmpty()){
						this.print("There is no food :(");
						food.wait();
					}
					this.print("Yay, eating now :)");
					food.eat();
					food.notify();
				}
				this.print("Going back to sleep");
				Thread.sleep(5000);
			}
		}catch (InterruptedException e){
			this.print("Interrupted");
			return;
		}
	}	
}
