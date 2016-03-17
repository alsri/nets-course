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

public class EchoServer {

	public static void main(String[] args) {
		try (ServerSocket server= new ServerSocket();) { 
			server.setReceiveBufferSize(100);
			server.bind(new InetSocketAddress(InetAddress.getLocalHost(), 1500));
			while (true){
				System.out.println("Waiting for clients");
				String message;
				try (Socket client=server.accept();
						BufferedReader reader=new BufferedReader(new InputStreamReader(
								client.getInputStream()));
						BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(
								client.getOutputStream()));){
					while ((message= reader.readLine() )!=null){
							System.out.println("Client sent: "+message);
							writer.write(message+"\r\n");
							writer.flush();
					}
				} catch (IOException e){
					System.out.println("Client closed connection or some error appeared");
				} 
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
