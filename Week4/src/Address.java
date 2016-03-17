import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Address {

	public static void main(String[] args) {
		String stringAddress="131.114.18.105";
		String name="elearning.unipi.it";
		byte[] rawAddress={(byte)216,58,(byte)212,68};
		try {
			InetAddress address = InetAddress.getByName(name);
			System.out.println("Found "+address.getHostName()+
					" with IP "+address.getHostAddress());
			address = InetAddress.getByName(stringAddress);
			System.out.println("Found "+address.getHostName()+
					" with IP "+address.getHostAddress());
			address = InetAddress.getByAddress(rawAddress);
			System.out.println("Found "+address.getHostName()+
					" with IP "+address.getHostAddress());
			address = InetAddress.getLocalHost();
			System.out.println("Running on "+address.getHostName()+
					" with IP "+address.getHostAddress());
			address = InetAddress.getLoopbackAddress();
			System.out.println("Local "+address.getHostName()+
					" with IP "+address.getHostAddress());
		} catch (UnknownHostException e) {
			System.out.println("No such host: "+name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
