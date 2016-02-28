import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ConcurrentFileReader1 implements Runnable {

	private BufferedReader reader;
	private BufferedWriter writer;
	public ConcurrentFileReader1(BufferedReader r, BufferedWriter w){
		this.reader=r;
		this.writer=w;
	}
	
	public void run(){
		System.out.println(Thread.currentThread().getId()+" running....");
		String line="";
		try {
			while (line!=null){
				line=this.reader.readLine();
				this.writer.write(Thread.currentThread().getId()+" read: "+line+"\n");
			}
			System.out.println(Thread.currentThread().getId()+" finished.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		try {
			ArrayList<Thread> threads= new ArrayList<Thread>();
			BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("fileCopy.txt"));
			for (int i=0;i<5;i++){
				ConcurrentFileReader1 r=new ConcurrentFileReader1(reader,writer);
				Thread t= new Thread(r);
				t.start();
				threads.add(t);
			}
			for (Thread t : threads){
				t.join();
			}
			//here I know all threads are done so I can close the file
			reader.close();
			writer.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

}
