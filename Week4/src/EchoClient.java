import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class EchoClient {

	public static void main(String[] args) {
		Socket socket=new Socket();
		BufferedReader reader=null;
		BufferedWriter writer=null;
		try{
			socket.setSoTimeout(100000);
			socket.setTcpNoDelay(true);
			socket.connect(new InetSocketAddress(InetAddress.getLocalHost(),1500));
			reader= new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			writer= new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			BufferedReader localReader= new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("Type 'exit' to stop, any message to send to server:");
			String reply="";
			String choice;
			while (!(choice= localReader.readLine().trim()).equals("exit"))
			{
					writer.write(choice+"\r\n");
					writer.flush();
					reply= reader.readLine();
					System.out.println("Server sent back: "+reply);
			}
			System.out.println("Communication ended by client");
		} catch (SocketException e) {
			System.out.println("Server closed connection or an error appeared.");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Server closed connection or an error appeared.");
		}finally{
			try {
				if (reader!=null) reader.close();
				if (writer!=null) writer.close();
				socket.close();
			}catch (IOException e) {}
		}
	}
}
