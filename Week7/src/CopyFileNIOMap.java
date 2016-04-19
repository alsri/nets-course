import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CopyFileNIOMap {

	public static void main(String[] args) {
		long start= System.currentTimeMillis();
		int fileSize=2000000;//size in kB
		try (FileChannel inChannel= FileChannel.open(Paths.get("File"+fileSize+"k.dat"),
				StandardOpenOption.READ);
				FileChannel outChannel= FileChannel.open(Paths.get("File"+fileSize+"kCopyMap.dat"),
						StandardOpenOption.CREATE, StandardOpenOption.WRITE)){
			long size=inChannel.size();
			MappedByteBuffer mappedFile= inChannel.map(MapMode.READ_ONLY, 0, size);
			while( mappedFile.hasRemaining()){
				outChannel.write(mappedFile);
			}
			System.out.format("Copy completed.");
		} catch (IOException e) {
			System.out.println("Something went wrong: "+e.getMessage());
		}
		System.out.format("Ran %d milliseconds.%n",System.currentTimeMillis()-start);

	}

}
