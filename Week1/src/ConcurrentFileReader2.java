import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ConcurrentFileReader2 implements Runnable {

	private BufferedReader reader;
	private BufferedWriter writer;
	
	public ConcurrentFileReader2(){
		try {
			this.writer=new BufferedWriter(new FileWriter("fileCopy.txt",true));
			this.reader=new BufferedReader(new FileReader("file.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			this.reader.close();
			this.writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
			for (int i=0;i<5;i++){
				ConcurrentFileReader2 r=new ConcurrentFileReader2();
				Thread t= new Thread(r);
				t.start();
			}

	}

}
