
public class Writer implements Runnable{
	Counter c;
	
	public Writer(Counter c){
		this.c=c;
	}
	public void print(String message){
		System.out.format("%n%d Writer %d:%s",System.nanoTime(),
				Thread.currentThread().getId(),message);
	}
	@Override
	public void run() {
			this.print("created");
			this.c.increment();
			this.print("finished incrementing c");	
	}

}
