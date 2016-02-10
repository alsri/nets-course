
public class MyFirstRunnable implements Runnable{

	public void threadPrint(String message){
		System.out.println("Thread "+Thread.currentThread().getId()+": "+message);
	}
	
	public void printInfo(){
		try {
			this.threadPrint("I am a new thread.");
			this.threadPrint(Thread.currentThread().toString());
			Thread.sleep(2000);
			this.threadPrint("My name is "+ Thread.currentThread().getName());
			Thread.sleep(2000);
			this.threadPrint("My priority is "+ Thread.currentThread().getPriority());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void run(){
		this.printInfo();
	}
	
	public static void main(String[] args) {
		for (int i=0;i<10;i++){
			MyFirstRunnable runnable= new MyFirstRunnable();
			Thread thread=new Thread(runnable);
			thread.start();
		}

	}

}
