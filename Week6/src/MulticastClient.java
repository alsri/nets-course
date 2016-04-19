import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

public class MulticastClient {

	public static void main(String[] args) {
		try(MulticastSocket client = new MulticastSocket(MulticastServer.PORT);){
			InetAddress multicastGroup=InetAddress.getByName(MulticastServer.IP);
			client.joinGroup(multicastGroup);
			DatagramPacket packet= new DatagramPacket(new byte[512], 512); 
			String header;
			while(true){
				client.receive(packet);
				DataInputStream in = new DataInputStream(
						new ByteArrayInputStream(packet.getData(),packet.getOffset(),packet.getLength()));
				System.out.println((header=in.readUTF())+" "+new Date(in.readLong()));
				if (header.equals("finish"))
					break;
			}
		} catch (IOException e) {
			System.out.println("Some error appeared: "+e.getMessage());
			e.printStackTrace();
		}

	}

}
