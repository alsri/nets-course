
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolEchoServer {

	public static void main(String[] args) {
		ExecutorService es=null;
		try (ServerSocket server = new ServerSocket()) {
			server.setReceiveBufferSize(100);
			server.bind(new InetSocketAddress(InetAddress.getLocalHost(), 1500));
			es= Executors.newFixedThreadPool(100);
			while (true){
				try { //not try with resources
					Socket client=server.accept(); 
					EchoClientHandler handler = new EchoClientHandler(client);
					es.submit(handler);
				} catch (IOException e){
					System.out.println("Some error appeared");
				} 
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if (es != null)
				es.shutdown();
		}
	}
}
