
public class MyFirstThread extends Thread {
	
	public void threadPrint(String message){
		System.out.println("Thread "+this.getId()+": "+message);
	}
	
	public void printInfo(){
		this.threadPrint("I am a new thread.");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.threadPrint("My name is "+ this.getName());
		this.threadPrint("My priority is "+ this.getPriority());
		
	}
	
	public void run(){
		this.printInfo();
	}

	public static void main(String[] args) {
		
		for (int i=0;i<10;i++){
			MyFirstThread thread=new MyFirstThread();
			thread.start();
		}

	}

}
