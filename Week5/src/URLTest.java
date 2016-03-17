import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {

	public static void main(String[] args) {
		try {
			URL url= new URL("https://docs.oracle.com/"
					+ "javase/tutorial/networking/urls/creatingUrls.html");
			try(BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));){
				String line;
				while ((line=in.readLine())!=null){
					System.out.println(line);
				}
			}
		} catch (MalformedURLException e) {
			System.err.println("Wrong URL format");
		} catch (IOException e) {
			System.err.println("Error reading resource");
		}
	}

}
