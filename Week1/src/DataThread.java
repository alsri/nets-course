
public class DataThread implements Runnable{

	private C1 trickyOne;
	private static String sharedString=null;
	private String nonSharedString;
	
	public DataThread(C1 tricky){
		this.nonSharedString="";
		this.trickyOne=tricky;
	}
	
	public void run() {
		try{
			String me=Thread.currentThread().getName();
			System.out.println(me+": Setting my strings to be equal to my name.");
			DataThread.sharedString=me;
			this.nonSharedString=me;
			this.trickyOne.getAttribute().setAttribute(me);
			System.out.println(me+": The strings are "
					+this.nonSharedString+" (non shared) "+DataThread.sharedString
					+" (shared) "+ this.trickyOne.getAttribute().getAttribute()+" (globally shared)");
			Thread.sleep(2000);
			System.out.println(me+": After sleeping the strings are "
					+ this.nonSharedString+" (non shared) "+DataThread.sharedString+" (shared) "
					+ this.trickyOne.getAttribute().getAttribute()+" (globally shared)");
		}catch(InterruptedException e){}
	}
	
	public static void main(String[] args) throws InterruptedException {
		C2 globalStr=new C2();
		globalStr.setAttribute("From main");
		C1 str1= new C1();
		C1 str2= new C1();
		str1.setAttribute(globalStr);
		str2.setAttribute(globalStr);
		DataThread d1=new DataThread(str1);
		DataThread d2=new DataThread(str2);
		Thread t1= new Thread(d1);
		Thread t2= new Thread(d2);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("In main global string in str1 is: "+str1.getAttribute().getAttribute());
		System.out.println("In main global string in str2 is: "+str2.getAttribute().getAttribute());
	}

}
