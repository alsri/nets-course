import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CopyFileNIOBuffer {

	public static void main(String[] args) {
		long start= System.currentTimeMillis();
		int fileSize=2000000;//size in kB
		int bufferSize=1000;//size in kB
		try (FileChannel inChannel= FileChannel.open(Paths.get("File"+fileSize+"k.dat"),
				StandardOpenOption.READ);
				FileChannel outChannel= FileChannel.open(Paths.get("File"+fileSize+"kCopyBuff.dat"),
						StandardOpenOption.CREATE, StandardOpenOption.WRITE)){
			ByteBuffer buffer= ByteBuffer.allocate(1024*bufferSize);
			IntBuffer bufferi= IntBuffer.allocate(10);
			System.out.println(bufferi);
			
			while(inChannel.read(buffer)!=-1){
				buffer.flip();
				outChannel.write(buffer);
				buffer.clear();
			}
			System.out.format("Copy completed.");
		} catch (IOException e) {
			System.out.println("Something went wrong: "+e.getMessage());
		}
		System.out.format("Ran %d milliseconds.%n",System.currentTimeMillis()-start);

	}

}
