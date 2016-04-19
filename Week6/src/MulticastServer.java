import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;

public class MulticastServer {
	public static final int PORT=2000;
	public static final String IP="225.1.1.1";
	public static void main(String[] args) {
		String c="Current time: "; 
		try(MulticastSocket server = new MulticastSocket(MulticastServer.PORT);){
			server.setTimeToLive(1);
			server.setLoopbackMode(false);
			server.setReuseAddress(true);
			
			InetAddress multicastGroup=InetAddress.getByName(MulticastServer.IP);
			
			for (int i=0;i<10;i++){
				if (i==9)
					c="finish";
				ByteArrayOutputStream byteStream= new ByteArrayOutputStream();
				DataOutputStream out= new DataOutputStream(byteStream);
				out.writeUTF(c);
				out.writeLong(System.currentTimeMillis());
				byte[] data= byteStream.toByteArray();
				DatagramPacket packet= new DatagramPacket(data, data.length,
						multicastGroup, MulticastServer.PORT);
				server.send(packet);
				System.out.println("Sent packet");
				Thread.sleep(20000);
			}
		} catch (IOException e) {
			System.out.println("Some error appeared: "+e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("This should not happen: "+e.getMessage());
		}
		
	}

}
