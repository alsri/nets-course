import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriterTest {

	public static void main(String[] args) {
		BufferedWriter w=null;
		try {
			w= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("text.txt"),"UTF-8"));
			w.write("Il corso di reti è molto interessante\r\n");
			w.write("Mi piace lavorare con le reti.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {w.close();} catch (IOException e) {}
		}
	}

}
