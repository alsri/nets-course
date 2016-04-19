import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
	public static final int NPLAYERS=2;
	public static final int PORT=2001;
	public static final int TIME=30;
	private ArrayList<String> dictionary=null;
	private ArrayList<Player> players=null;
	private List<String> letters;
	private Lock playerLock;
	private Condition playerCondition;
	private CyclicBarrier evaluationBarrier;
	
	public Server() throws IOException{
		//create dictionary
		this.dictionary=new ArrayList<>();
		BufferedReader in = new BufferedReader(new FileReader("dictionary.txt"));
		String line=null;
		while((line=in.readLine())!=null){
			this.dictionary.add(line);
		}
		//select one 6 letter word
		int index=ThreadLocalRandom.current().nextInt(dictionary.size());
		while(dictionary.get(index).length()<7){
			index++;
		}
		this.letters=Arrays.asList(this.dictionary.get(index).split(""));
		Collections.shuffle(this.letters);
		
		
		//initialize players
		this.players= new ArrayList<>(Server.NPLAYERS);
		
		//Initialize lock
		this.playerLock= new ReentrantLock();
		this.playerCondition= this.playerLock.newCondition();
		this.evaluationBarrier= new CyclicBarrier(Server.NPLAYERS);
	}
	
	public void run(){
		ArrayList<Future> results=new ArrayList<>(); 
		try(ServerSocket socket= new ServerSocket(Server.PORT)){
			while(this.players.size()<Server.NPLAYERS){
				Socket client=socket.accept();
				ServerTask task= new ServerTask(client, this.players,
						this.playerLock,this.playerCondition,this.letters,
						this.dictionary,this.evaluationBarrier);
				ExecutorService es = Executors.newSingleThreadExecutor();
				results.add(es.submit(task));
				es.shutdown();
			}	
		} catch (IOException e) {
			System.out.println("Error running server: "+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Server server;
		try {
			server = new Server();
			server.run();
		} catch (IOException e) {
			System.out.println("Error creating server: "+e.getMessage());
		}
	}
}
