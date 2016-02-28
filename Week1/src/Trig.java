
public class Trig implements Runnable {

	private double result;
	
	private int operation;
	private double number;
	
	public Trig(int operation,double number){
		this.operation=operation;
		this.number=number;
		this.result=0;
	}
	
	public double getResult(){
		return this.result;
	}
	
	public void run(){
		System.out.println(Thread.currentThread()+" started.");
		switch (operation){
		case 1:
			this.result=Math.sin(number);
			break;
		case 2:
			this.result=Math.cos(number);
			break;
		case 3:
			this.result=Math.tan(number);
			break;
		}
		System.out.println(Thread.currentThread()+" finished.");
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		double n1=10, n2=4, n3=6;
		Trig task1=new Trig(1,n1);
		Trig task2=new Trig(2,n2);
		Trig task3=new Trig(3,n3);
		Thread t1=new Thread(task1);
		Thread t2=new Thread(task2);
		Thread t3=new Thread(task3);
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
		System.out.println("Result is "+(task1.getResult()+task2.getResult()+task3.getResult()));
		
		
		

	}

}
