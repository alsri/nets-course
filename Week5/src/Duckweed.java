import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Duckweed {

	public static void main(String[] args) {
		String date = ""; 
		//THIS HOST GENERATE ALWAYS IOException 
		String host = "http://duckweed.isti.cnr.it:5000/top/"; 
		//Using String host = "http://duckweed.isti.cnr.it"; without date I haven't got any trouble. 
		if(args.length == 0){
			System.out.println("No arguments found. Use the format YYYYMMDD.");
			return; 
		}
		try {
			date = "20160111"; //args[0]; 
			host += date; 
			URL duckweed = new URL(host);
			System.out.println("Url is " + duckweed.toString());
			HttpURLConnection connection = (HttpURLConnection) duckweed.openConnection();
			System.out.println(connection.getResponseCode());
			System.out.println(connection.getResponseMessage());
		} catch (MalformedURLException e) {e.printStackTrace();	} 
		  catch (IOException e) {e.printStackTrace();}
	}

}
