
public class A  implements Runnable{

	private Counter c;
	
	public A(Counter c){
		this.c=c;
	}

	@Override
	public void run() {
		this.c.increment();		
	}
	
	public static void main(String[] args) {
		Counter c= new Counter();
		(new Thread(new A(c))).start();
		(new Thread(new B(c))).start();
		(new Thread(new A(c))).start();
		(new Thread(new B(c))).start();

	}

}
