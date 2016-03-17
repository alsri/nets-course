import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ReaderTest {

	public static void main(String[] args) {
		BufferedReader r=null;
		try {
			r= new BufferedReader(new InputStreamReader(new FileInputStream("text.txt"),"Big5"));
			String line= r.readLine();
			while (line!=null){
				System.out.println("Read: "+line);
				line=r.readLine();
			}
//			for (String s : Charset.availableCharsets().keySet()){
//				System.out.println(s);
//			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {r.close();} catch (IOException e) {}
		}
	}

}
