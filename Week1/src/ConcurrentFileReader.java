import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ConcurrentFileReader implements Runnable {

	static BufferedReader reader;
	
	public void run(){
		System.out.println(Thread.currentThread().getId()+" running....");
		String line;
		try {
			line = ConcurrentFileReader.reader.readLine();
			while (line!=null){
				System.out.println(Thread.currentThread().getId()+" read: "+line);
				line=ConcurrentFileReader.reader.readLine();
			}
			System.out.println(Thread.currentThread().getId()+" finished.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		try {
			ArrayList<Thread> threads= new ArrayList<Thread>();
			ConcurrentFileReader.reader = new BufferedReader(new FileReader("file.txt"));
			for (int i=0;i<5;i++){
				ConcurrentFileReader r=new ConcurrentFileReader();
				Thread t= new Thread(r);
				t.start();
				threads.add(t);
			}
			for (Thread t : threads){
				t.join();
			}
			//here I know all threads are done so I can close the file
			ConcurrentFileReader.reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

}
