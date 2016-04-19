import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class EchoClient {

	public static void main(String[] args) {
		String message=null;
		try(DatagramSocket client= new DatagramSocket();
				BufferedReader reader= new BufferedReader(
						new InputStreamReader(System.in))){
			System.out.println("Enter a message to send or 'exit' to finish.");
			DatagramPacket response= new DatagramPacket(
					new byte[EchoServer.MAX_LENGTH], EchoServer.MAX_LENGTH);
			SocketAddress server= new InetSocketAddress(InetAddress.getLocalHost(), EchoServer.PORT);
			while(!(message=reader.readLine()).trim().toLowerCase().equals("exit")){
				byte[] bMessage=message.getBytes("UTF-8");
				DatagramPacket request= new DatagramPacket(bMessage, bMessage.length,server);
				try {
					client.send(request);
					client.receive(response);
					System.out.println("Received response: "+new String(response.getData(),0,response.getLength(),"UTF-8"));
					response.setLength(EchoServer.MAX_LENGTH);
				} catch (IOException e) {
					System.err.println("Error in communication: "+e.getMessage());
				}
				System.out.println("Enter a message to send or 'exit' to finish.");
			}
			System.out.println("Bye!");
		} catch (SocketException e) {
			System.err.println("Error creating socket: "+e.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.err.println("Wrong encoding: "+e.getMessage());
		} catch (IOException e) {
			System.err.println("Error reading from stdin: "+e.getMessage());
		}

	}

}
