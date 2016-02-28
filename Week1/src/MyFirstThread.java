
public class MyFirstThread extends Thread {
	
	public void threadPrint(String message){
		System.out.println("Thread "+Thread.currentThread().getId()+": "+message);
	}
	
	public void printInfo(){
		//try {
			this.threadPrint("I am a new thread.");
			this.threadPrint(this.toString());
			//Thread.sleep(2000);
			this.threadPrint("My name is "+ this.getName());
			//Thread.sleep(2000);
			this.threadPrint("My priority is "+ this.getPriority());
		//} catch (InterruptedException e) {
			//e.printStackTrace();
		//}
		
		
	}
	
	public void run(){
		this.printInfo();
	}

	public static void main(String[] args) {
		
		for (int i=0;i<10;i++){
			MyFirstThread thread=new MyFirstThread();
			thread.run(); //not thread.run()!!!!!!
		}

	}

}
