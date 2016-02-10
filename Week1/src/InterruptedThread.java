import java.util.Random;
import java.io.*;

public class InterruptedThread implements Runnable{

	public void run(){
		BufferedWriter w=null;
		try {
			//generate random numbers and write to file, until interrupted
			Random r = new Random(System.currentTimeMillis()*1000);
			 w= new BufferedWriter(new FileWriter("random.txt"));
			while(true){
				if (Thread.interrupted()){
					throw new InterruptedException("Interrupted outside sleep");
				}
				w.write(r.nextDouble()+",");
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			System.out.println("I was interruped, I am stopping ("+e.getMessage()+")");
		}
		finally{
			try {
				w.close();
			} catch (IOException e) {
			}
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
			if (r.nextDouble()<0.2){
				t.interrupt();
				interrupted=true;
			}
			
		}
	}
}
