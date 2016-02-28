import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileExample {

	public static void main(String[] args) {
		
		try{
			FileReader reader= new FileReader("matrix1.txt");
			BufferedReader br= new BufferedReader(reader);
			String line=br.readLine();
		} catch(IOException e){e.printStackTrace();}
	}

}
