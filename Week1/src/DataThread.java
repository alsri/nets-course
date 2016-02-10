
public class DataThread implements Runnable{

	private static String sharedString=null;
	private String nonSharedString;
	
	public DataThread(){
		nonSharedString="";
	}
	
	public void run(){
		System.out.println(Thread.currentThread().getName()+": Setting my strings to be equal to my name.");
		DataThread.sharedString=Thread.currentThread().getName();
		this.nonSharedString=Thread.currentThread().getName();
		System.out.println(Thread.currentThread().getName()+": The strings are "
				+this.nonSharedString+"(non shared)"+DataThread.sharedString+"(shared)");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		System.out.println(Thread.currentThread().getName()+": After sleeping the strings are "
				+this.nonSharedString+"(non shared)"+DataThread.sharedString+"(shared)");
	}
	
	public static void main(String[] args) {
		DataThread d1=new DataThread();
		DataThread d2=new DataThread();
		Thread t1= new Thread(d1);
		Thread t2= new Thread(d2);
		t1.start();
		t2.start();
		

	}

}
