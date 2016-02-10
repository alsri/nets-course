import java.io.*;
import java.util.ArrayList;

public class WordSearch implements Runnable{

	public static BufferedReader file;
	public static String key;
	public static ArrayList<Thread> workers;
	
	public void run(){
		

		System.out.println(Thread.currentThread().getName()+": started search.");
		String line="";
		int lineCount=0;
		try{
			while (line !=null){
			//check if no one else found it already
			if (Thread.interrupted()){
				throw new InterruptedException();
			}
			line=WordSearch.file.readLine();
			lineCount++;
			if (line!=null && line.toLowerCase().contains(key)){
				System.out.println(Thread.currentThread().getName()+": found the key!!!!");
				for (Thread t : WordSearch.workers){
					t.interrupt();//interrupting everyone including myself
				}
			}
		}
		
		System.out.println(Thread.currentThread().getName()+": ended search, key not found after parsing "+(lineCount-1)+" lines");
		}catch (IOException e){
			System.out.println(e.getMessage());
		}catch (InterruptedException e){
			System.out.println(Thread.currentThread().getName()+": Key was found. I parsed "+(lineCount-1)+" lines");
		}
		
	}
	
	public static void main(String[] args) {	
		try {
				//first open file for all threads
				WordSearch.file= new BufferedReader(new FileReader("largeFile.txt"));
				WordSearch.key="voci";
				WordSearch.workers= new ArrayList<Thread>();
				//now create threads
				for (int i =0 ; i<5; i++){
					WordSearch runnable= new WordSearch();
					Thread t= new Thread(runnable);
					WordSearch.workers.add(t);
				}
				//now start threads
				for (Thread t : WordSearch.workers){
					t.start();
				}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
