import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FtpServer {

	public final static int PORT=2000;
	public final static int BLOCK_SIZE=1024;

	public static void main(String[] args) {
		try(ServerSocketChannel server= ServerSocketChannel.open() ){
			server.bind(new InetSocketAddress(InetAddress.getLocalHost()	, FtpServer.PORT));
			Selector selector = Selector.open();
			server.configureBlocking(false);
			server.register(selector, SelectionKey.OP_ACCEPT);
			while (true){
				selector.select();
				for( SelectionKey key : selector.selectedKeys()){
					if(key.isAcceptable()){
						SocketChannel client=( (ServerSocketChannel)key.channel()).accept();
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_READ);
					}
					if (key.isReadable()){
						ByteBuffer buffer= ByteBuffer.allocate(FtpServer.BLOCK_SIZE);
						SocketChannel client=(SocketChannel)key.channel();
						client.read(buffer);
						String fileName= new String(buffer.array(), 0, buffer.position());
						buffer.clear();
						FileChannel file= FileChannel.open(Paths.get(fileName), StandardOpenOption.READ);
						ArrayList<Object> attachment= new ArrayList<>();
						attachment.add(buffer);
						attachment.add(file);
						client.register(selector, SelectionKey.OP_WRITE, attachment);
					}
					if (key.isWritable()){
						
					}
				}
			}
			
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}
