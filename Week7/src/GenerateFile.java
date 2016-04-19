import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class GenerateFile {

	public static void main(String[] args) {
		Random rand= new Random(System.currentTimeMillis());
		int size=2000000;//size in kb
		byte[] data= new byte[1024];
		rand.nextBytes(data);
		ByteBuffer buffer= ByteBuffer.wrap(data);
		try(FileChannel out = FileChannel.open(Paths.get("File"+size+"k.dat"),
				StandardOpenOption.WRITE, StandardOpenOption.CREATE)){
			for(int i=0;i<size;i++){
				out.write(buffer);
				buffer.rewind();
			}
		} catch (IOException e) {
			System.out.println("Something went wrong: "+e.getMessage());
		}
	}

}
