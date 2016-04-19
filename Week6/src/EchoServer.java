import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
	static final int MAX_LENGTH=512;
	static final int PORT=2000;
	static final int THREADS=5;
	public static void main(String[] args) {
		
		try (DatagramSocket server= new DatagramSocket(PORT);){
			ExecutorService es = Executors.newFixedThreadPool(THREADS);
			ArrayList<EchoServerTask> tasks= new ArrayList<>();
			for (int i=0;i<THREADS;i++){
				tasks.add(new EchoServerTask(server));
			}
			es.invokeAll(tasks);
		} catch (SocketException e) {
			System.err.println("Error creating socket: "+e.getMessage());
		} catch (InterruptedException e) {
			System.err.println("This should never happen: "+e.getMessage());
		}
	}
}
