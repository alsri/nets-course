import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ModifyFileNIO {

	public static void main(String[] args) {
		long start= System.currentTimeMillis();
		String prodName= "chocolate bar";
		double newPrice=2.7;
		try (FileChannel inChannel= FileChannel.open(Paths.get("Products.dat"),
				StandardOpenOption.READ,StandardOpenOption.WRITE)){
			long size=inChannel.size();
			MappedByteBuffer mappedFile= inChannel.map(MapMode.READ_WRITE, 0, size);
			while( mappedFile.hasRemaining()){
				int nameSize=mappedFile.getInt();
				StringBuilder name=new StringBuilder();
				for(int i=0;i<nameSize;i++)
					name.append(mappedFile.getChar());
				if (name.toString().equals(prodName)){
					//found the product, can overwrite the price
					mappedFile.putDouble(newPrice);
				} else {
					mappedFile.getDouble();
				}
				
			}
			//mappedFile.force();
			System.out.format("Change completed.");
		} catch (IOException e) {
			System.out.println("Something went wrong: "+e.getMessage());
		}
		System.out.format("Ran %d milliseconds.%n",System.currentTimeMillis()-start);

	}

}
