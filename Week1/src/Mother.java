
public class Mother implements Runnable{

	Food food;
	public Mother(Food food){
		this.food=food;
	}
	
	public static void main(String[] args) {
		try{
			Food food= new Food();
			Mother mother = new Mother(food);
			Child child= new Child(food);
			Thread motherThread=new Thread(mother);
			Thread childThread= new Thread(child);
			motherThread.start();
			childThread.start();
			Thread.sleep(20000);
			motherThread.interrupt();
			childThread.interrupt();
		} catch(InterruptedException e){}
		
	}

	private void print(String message){
		System.out.println(System.currentTimeMillis()/1000+ " - Mother: "+message);
	}
	
	@Override
	public void run() {
		try{
			this.print("Created");
			while(true){
				synchronized(this.food){
					while(!this.food.isEmpty()){
						this.print("Waiting for baby to eat");
						this.food.wait();
					}
					this.print("No food left, cooking");
					this.food.cook("Soup");
					this.food.notify();
				}
				this.print("Now resting a bit");
				Thread.sleep(10000);
			}
		}catch (InterruptedException e){
			this.print("Interrupted");
			return;
		}
	}

}


