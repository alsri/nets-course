import java.util.Random;
import java.io.*;

public class InterruptedThread implements Runnable{

	public void run(){
		try {
			//generate random numbers and write to screen, until interrupted
			Random r = new Random(System.currentTimeMillis()*1000);
			while(true){
				if (Thread.interrupted()){
					throw new InterruptedException("Interrupted outside sleep");
				}
				System.out.print(r.nextDouble()+", ");
				//Thread.sleep(1000);
			}
		}
		catch (InterruptedException e) {
			System.out.println("\nI was interruped, I am stopping ("+e.getMessage()+")");
		}

	}
	
	public static void main(String args[]){
		InterruptedThread runnable= new InterruptedThread();
		Thread t = new Thread(runnable);
		t.start();
		boolean interrupted=false;
		Random r = new Random(System.currentTimeMillis()*1000);
		while (!interrupted){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if (r.nextDouble()<0.5){
				t.interrupt();
				interrupted=true;
			}
			
		}
	}
}
