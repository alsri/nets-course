
public class B implements Runnable{
	private Counter c;
	
	public B(Counter c){
		this.c=c;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread()+": "+c.getCount());
	}
}
