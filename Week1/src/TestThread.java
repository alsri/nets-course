import java.util.Scanner;

public class TestThread 
{
	//static Boolean flag=true;
	static MyBoolean flag= new MyBoolean();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestRunnable r = new TestRunnable ();
		Thread t = new Thread (r);
		String temp;
		Scanner s = new Scanner (System.in);
		
		t.start();
		
		System.out.println("I-Interrompi il Thread\nS-Sblocca la risorsa");
		temp=s.nextLine();
		s.close();
		
		if (temp.equals("I"))
		{
			t.interrupt();
		}
		else if (temp.equals("S"))
			{
				synchronized (flag)
				{
					System.out.println("MAIN: flag="+flag);
				
				
					//flag=false;
					flag.set(false);
					System.out.println("MAIN: flag="+flag);
					flag.notify();
				}
				
				System.out.println ("MAIN: Thread sbloccato");
				//t.interrupt();
			}
		
		try
		{
			System.out.println ("MAIN: Attesa terminazione Thread");
			t.join();
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			e.getMessage();
			System.out.println("MAIN: Catch exception");
			return;
		}
		System.out.println ("MAIN: Done");
	}

}

class TestRunnable implements Runnable
{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println (Thread.currentThread());
		
		try 
		{
			synchronized (TestThread.flag)
			{
				//while (TestThread.flag)
				while (TestThread.flag.get())
				{
					System.out.println("Thread in attesa");
					TestThread.flag.wait();
				}
				System.out.println ("flag="+TestThread.flag);
			
				System.out.println ("Thread sbloccato");
			}
			//while (!Thread.currentThread().isInterrupted())
			//	System.out.println("Thread interrotto");
		} 
		catch (InterruptedException e) 
		{
			System.out.println("Thread interrotto durante wait");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return;
	}
	
}

class MyBoolean{
	private Boolean element;

	public MyBoolean(){
		this.element=true;
	}
	
	public Boolean get() {
		return this.element;
	}

	public void set(Boolean element) {
		this.element = element;
	}
}
