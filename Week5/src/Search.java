import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolFamily;
import java.net.StandardProtocolFamily;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Search {

	public static void main(String[] args) {
		try {
			String google_service= "http://www.google.com/search?q=";
			String[] terms={"pisa university"};
			for (String term: terms){
				URL google= new URL(google_service+URLEncoder.encode(term,"UTF-8")); 
				HttpURLConnection connection = (HttpURLConnection) google.openConnection();
				connection.addRequestProperty("User-Agent","Chrome" );//pretend to be a browser
				connection.connect();
				System.out.println(connection.getResponseCode());
				System.out.println(connection.getResponseMessage());
				String[] contentType=connection.getContentType().split(";");
				if (contentType[0].startsWith("text")){
					String encoding="UTF-8";
					if (contentType.length>1)
						encoding=contentType[1].substring(9);
					System.out.println(encoding);
					try(BufferedReader in = new BufferedReader(
							new InputStreamReader(connection.getInputStream(),encoding));
							BufferedWriter out= new BufferedWriter(
									new OutputStreamWriter(
									new FileOutputStream(term+"Result.html"),"UTF-8"));){
				        String resultLine;
				        while ((resultLine = in.readLine()) != null) {
				            out.write(resultLine+"\n");
				        }
					}
				} else
				{
					System.out.println("Server sent binary response.");
				}

			}
		} catch (MalformedURLException e) {
			System.err.println("Wrong URL format:"+e.getMessage());
		} catch (IOException e) {
			System.err.println("Error with connection:"+e.getMessage());
		}

	}

}
