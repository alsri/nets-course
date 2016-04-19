import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FtpClient {

	public static void main(String[] args) {
		try(SocketChannel client= SocketChannel.open(
				new InetSocketAddress(InetAddress.getLocalHost(),FtpServer.PORT));
				ReadableByteChannel in = Channels.newChannel(System.in);) {
			client.configureBlocking(false);
			ByteBuffer buffer= ByteBuffer.allocate(FtpServer.BLOCK_SIZE);
			in.read(buffer);
			String fileName= new String(buffer.array(),0,buffer.position());
			buffer.flip();
			client.write(buffer);
			buffer.clear();
			FileChannel out= FileChannel.open(Paths.get(fileName), 
					StandardOpenOption.CREATE,StandardOpenOption.WRITE);
			while(client.read(buffer)!=-1){
				buffer.flip();
				out.write(buffer);
				buffer.compact();
			}
			out.close();
			System.out.println("Finished transfering file");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
