
public class Food {
	String food;
	
	public synchronized boolean isEmpty() {
		return food.equals("");
	}

	public synchronized void cook(String food) {
		this.food = food;
	}
	
	public synchronized void eat(){
		this.food="";
	} 

	public Food(){
		this.food="";
	}
	
}
