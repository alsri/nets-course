import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

public class ReceiverThread implements Runnable{

	DatagramSocket socket;
	ReceiverThread(DatagramSocket sa){
		this.socket=sa;
	}
	@Override
	public void run() {
		try{
			this.socket.setSoTimeout(10000);
			DatagramPacket response= new DatagramPacket(
					new byte[EchoServer.MAX_LENGTH], EchoServer.MAX_LENGTH);
			while (!Thread.interrupted()){
				try{
					this.socket.receive(response);
					System.out.println("Received response: "+
							new String(response.getData(),0,response.getLength(),"UTF-8"));
					response.setLength(EchoServer.MAX_LENGTH);
				} catch(IOException e){}
			}
		} catch(SocketException e){
			System.err.println("Error with socket: "+e.getMessage());
		}
	}

}
