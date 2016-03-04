
public class Reader implements Runnable{

	Counter c;
	
	public Reader(Counter c){
		this.c=c;
	}
	public void print(String message){
		System.out.format("%n%d Reader %d:%s",System.nanoTime(),
				Thread.currentThread().getId(),message);
	}
	@Override
	public void run() {
			this.print("created");
			this.print("reading c="+this.c.getCount());
			this.print("finished reading");
	}

}
