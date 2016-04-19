import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFileIO {

	public static void main(String[] args) {
		long start= System.currentTimeMillis();
		int fileSize=2000000;
		int byteArraySize=1000;
		try(BufferedInputStream in= new BufferedInputStream(new FileInputStream("File"+fileSize+"k.dat"));
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("File"+fileSize+"kCopy.dat"))){
			byte[] bytes = new byte[byteArraySize*1024];
			while(in.read(bytes)!=-1){
				out.write(bytes);
			}
			System.out.format("Copy completed.");
		} catch (IOException e) {
			System.out.println("Something went wrong: "+e.getMessage());
		}
		System.out.format("Ran %d milliseconds.%n",System.currentTimeMillis()-start);
		

	}

}
