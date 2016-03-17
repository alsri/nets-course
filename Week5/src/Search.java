import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Search {

	public static void main(String[] args) {
		try {
			String google_service= "http://www.google.com/search?q=";
			String[] terms={"pisa university"};
			for (String term: terms){
				URL google= new URL(google_service+URLEncoder.encode(term,"UTF-8"));
				URLConnection connection = google.openConnection();
				connection.addRequestProperty("User-Agent","Chrome" );//pretend to be a browser
				connection.connect();
				try(BufferedReader in = new BufferedReader(
						new InputStreamReader(connection.getInputStream(),"UTF-8"));
						BufferedWriter out= new BufferedWriter(
								new FileWriter(term+"Result.html"));){
			        String resultLine;
			        while ((resultLine = in.readLine()) != null) {
			            out.write(resultLine+"\n");
			        }
				}

			}
		} catch (MalformedURLException e) {
			System.err.println("Wrong URL format:"+e.getMessage());
		} catch (IOException e) {
			System.err.println("Error with connection:"+e.getMessage());
		}
		

	}

}
