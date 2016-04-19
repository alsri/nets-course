import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CreateProductFile {

	public static void main(String[] args) {
		String[] names={"milk","bread","chocolate bar","butter"};
		double[] prices={1.3,2.5,2.1,4.5};
		int repeat= 10000000;
		try (FileChannel outChannel= FileChannel.open(Paths.get("Products.dat"),
						StandardOpenOption.CREATE, StandardOpenOption.WRITE)){
			ByteBuffer buffer= ByteBuffer.allocate(1024);
			
			for (int i=0;i<names.length;i++){
				buffer.putInt(names[i].length());
				for (int j=0;j<names[i].length();j++){
					buffer.putChar(names[i].charAt(j));
				}
				buffer.putDouble(prices[i]);
			}
			for (int k=0;k<repeat;k++){
				buffer.flip();
				while(buffer.hasRemaining()){
					outChannel.write(buffer);
				}
			}
		} catch (IOException e) {
			System.out.println("Something went wrong: "+e.getMessage());
		}

	}

}
