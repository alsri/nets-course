import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoClientThreaded {

	public static void main(String[] args) {
		ExecutorService es= Executors.newSingleThreadExecutor();
		try(DatagramSocket client= new DatagramSocket();
				BufferedReader reader= new BufferedReader(
						new InputStreamReader(System.in))){
			es.execute(new ReceiverThread(client));
			SocketAddress server= new InetSocketAddress(
					InetAddress.getLocalHost(), EchoServer.PORT);
			String message;
			System.out.println("Enter a message to send or 'exit' to finish.");
			while(!(message=reader.readLine()).trim().toLowerCase().equals("exit")){
				byte[] bMessage=message.getBytes("UTF-8");
				DatagramPacket request= new DatagramPacket(bMessage, bMessage.length,server);
				try {
					client.send(request);
				} catch (IOException e) {
					System.err.println("Error in communication: "+e.getMessage());
				}
				System.out.println("Enter a message to send or 'exit' to finish.");
			}
			es.shutdownNow();
			System.out.println("Bye!");
		} catch (IOException e) {
			System.err.println("Error with socket: "+e.getMessage());
		}

	}

}
