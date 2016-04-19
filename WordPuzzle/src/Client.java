import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Client {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println("Welcome to word puzzle. Please state your name to join a match:");
		BufferedReader localIn= new BufferedReader(new InputStreamReader(System.in));
		String name=localIn.readLine();
		
		try(	Socket client= new Socket(InetAddress.getLocalHost(), Server.PORT);
				BufferedWriter out=new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),"UTF-8"));
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"))){
			out.write(name+"\r\n");
			out.flush();
			String message =in.readLine();
			if (message.startsWith("Not")){
				//not accepted
				System.out.println("The match is already full. Please try again later.");
			}else{
				System.out.println(message);
				ExecutorService es = Executors.newSingleThreadExecutor();
				ArrayList<Word> words= new ArrayList<>();
				ReadingThread rt= new ReadingThread(words);
				es.submit(rt);
				message= in.readLine();
				long start= System.currentTimeMillis();
				System.out.println(message);
				Thread.sleep(Server.TIME*1000);
				es.shutdownNow();
				System.out.println("Time expired");
				System.out.flush();
				es.awaitTermination(10, TimeUnit.SECONDS);
				StringBuilder allWords=new StringBuilder();
				for (Word w : words){
					if (w.getTime()-start<Server.TIME*1000){
						allWords.append(w.getWord()+";");
					}
				}
				message=allWords.toString()+"\r\n";
				out.write(message);
				out.flush();
				
				String results=in.readLine();
				System.out.println("Results are: "+results);
				
			}
			
		}catch (ConnectException e){
			System.out.println("The match is already full or server is down. Please try again later.");
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
	}

}
