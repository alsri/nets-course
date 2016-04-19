import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CopyFileNIOTransfer {

	public static void main(String[] args) {
		long start= System.currentTimeMillis();
		int fileSize=2000000;//size in kB
		try (FileChannel inChannel= FileChannel.open(Paths.get("File"+fileSize+"k.dat"),
				StandardOpenOption.READ);
				FileChannel outChannel= FileChannel.open(Paths.get("File"+fileSize+"kCopyTransf.dat"),
						StandardOpenOption.CREATE, StandardOpenOption.WRITE)){
			long totalBytesTransfered=0;
			long size=inChannel.size();
			while( totalBytesTransfered<size){
				totalBytesTransfered+=inChannel.transferTo(totalBytesTransfered, 
						inChannel.size()-totalBytesTransfered, outChannel);
			}
			System.out.format("Copy completed.");
		} catch (IOException e) {
			System.out.println("Something went wrong: "+e.getMessage());
		}
		System.out.format("Ran %d milliseconds.%n",System.currentTimeMillis()-start);


	}

}
