import java.net.*;
import java.io.*;
public class SocketProps{
	public static void main(String[] args) {

		Socket socket = new Socket();
		try {
			SocketAddress address = new InetSocketAddress("131.114.218.139", 8905);
			socket.setSoTimeout(100000);
			socket.connect(address,10000);
			System.out.println("Connected to " + socket.getInetAddress()
			+ " on port " + socket.getPort() + " from port "
			+ socket.getLocalPort() + " of "
			+ socket.getLocalAddress());
			
		} catch (UnknownHostException ex) {
		System.err.println("I can't find teh host ");
		} catch (SocketTimeoutException ex) {
		System.err.println("Host timed out ");
		}catch (IOException ex) {
		System.err.println(ex);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {}
		}
		
	}
}