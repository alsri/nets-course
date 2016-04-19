import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReadProductFile {

	public static void main(String[] args) {
		try (FileChannel inChannel= FileChannel.open(Paths.get("Products.dat"),
				StandardOpenOption.READ)){
			ByteBuffer buffer= ByteBuffer.allocate(1024);
			while (inChannel.read(buffer)!=-1){
				buffer.flip();
				int nameLength=buffer.getInt();
				for (int j=0;j<nameLength;j++){
					System.out.print(buffer.getChar());
				}
				System.out.println(" costs "+buffer.getDouble());
				buffer.compact();
			}
			buffer.flip();
			while(buffer.hasRemaining()){
				int nameLength=buffer.getInt();
				for (int j=0;j<nameLength;j++){
					System.out.print(buffer.getChar());
				}
				System.out.println(" costs "+buffer.getDouble());
			}
		} catch (IOException e) {
			System.out.println("Something went wrong: "+e.getMessage());
		}


	}

}
