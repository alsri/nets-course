import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ServerTask implements Runnable {

	private ArrayList<Player> players;
	private ArrayList<String> dictionary;
	private Player player;
	private Lock playerLock;
	private Condition playerCondition;
	private CyclicBarrier evaluationBarrier;
	private Boolean accepted;
	private String letters;
	
	ServerTask(Socket client,ArrayList<Player> players,
			Lock lock,Condition cond,List<String> letters,
			ArrayList<String> dict,CyclicBarrier evaluationBarrier){
		this.dictionary=dict;
		this.players=players;
		this.letters=String.join("", letters);
		this.playerLock=lock;
		this.playerCondition=cond;
		this.evaluationBarrier=evaluationBarrier;
		this.player= new Player(client,this.dictionary,this.letters);
		this.playerLock.lock();
		if (this.players.size()<(Server.NPLAYERS)){
			this.accepted=true;
			this.players.add(player);
			this.playerCondition.signalAll();
		}else{
			this.accepted=false;
		}
		
		this.playerLock.unlock();
	}
	@Override
	public void run() {
		try{
			if (accepted){
				// get name from client
				BufferedReader in = this.player.getReader();
				this.player.setName(in.readLine());
				System.out.println("Accepted player "+this.player.getName());
				
				//first response: wait for other players
				BufferedWriter out= player.getWriter();
				out.write("Accepted, "+player.getName()+". Waiting for"
						+ " other players to join...Get ready to start\r\n");
				out.flush();
				
				this.playerLock.lock();
				while (this.players.size()<Server.NPLAYERS){
					this.playerCondition.await();
				}
				this.playerLock.unlock();
				
				//now there are all the players, start the game by sending letters
				out.write("Game started, you have "+Server.TIME+" seconds, "
						+ "the letters are: "+this.letters+"\r\n");
				out.flush();
				
				//now wait for list of words 
				String words=in.readLine();
				System.out.println("Received words: "+words);
				ArrayList<String> awords=new ArrayList<>();
				for (String w : words.split(";"))
					awords.add(w);
				this.player.setWords(awords);
				
				//evaluate and wait for others to be evaluated
				this.player.evaluate();
				this.evaluationBarrier.await();
				
				//send results
				StringBuilder results= new StringBuilder();
				for (Player p : this.players){
					results.append(p.getName());
					results.append(":");
					results.append(p.getScore());
					results.append(";");
				}
				results.append("\r\n");
				System.out.println("Sending results: "+results.toString());
				out.write(results.toString());
				out.flush();
				
				//close player
				player.close();
				
			} else{
				//extra player, has to stop
				BufferedReader in = this.player.getReader();
				this.player.setName(in.readLine());
				
				BufferedWriter out= player.getWriter();
				out.write("Not accepted, "+player.getName()+". Try again later.\r\n");
				out.flush();
				player.close();
			}
				
			
		} catch (IOException e){
			e.printStackTrace();
			this.evaluationBarrier.reset();
		} catch (InterruptedException e) {
			e.printStackTrace();
			this.evaluationBarrier.reset();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
			System.out.println("Game not valid, a player encountered an error");
		}
		
		
	}
	
}
