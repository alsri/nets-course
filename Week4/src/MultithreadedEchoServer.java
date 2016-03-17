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

public class MultithreadedEchoServer {

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket()) {
			server.bind(new InetSocketAddress(InetAddress.getLocalHost(), 1500));
			while (true){
				try { //not try with resources
					Socket client=server.accept(); 
					EchoClientHandler handler = new EchoClientHandler(client);
					new Thread(handler).start();
				} catch (IOException e){
					System.out.println("Some error appeared");
				} 
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
