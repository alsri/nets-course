	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.OutputStreamWriter;
	import java.net.HttpURLConnection;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.net.URLConnection;
	import java.net.URLDecoder;
	import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
	
public class SearchJson {


		public static void main(String[] args) {
			try {
				JSONParser parser= new JSONParser();
				String google_service= "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
				String term="pisa university";
				URL google= new URL(google_service+URLEncoder.encode(term,"UTF-8")); 
				HttpURLConnection connection = (HttpURLConnection) google.openConnection();
				connection.connect();
				System.out.println(connection.getResponseCode());
				System.out.println(connection.getResponseMessage());
				String[] contentType=connection.getContentType().split(";");
				if (contentType[0].startsWith("text")){
						String encoding="UTF-8";
						if (contentType.length>1)
							encoding=contentType[1].substring(9);
						try(BufferedReader in = new BufferedReader(
								new InputStreamReader(connection.getInputStream(),encoding));){
					        JSONObject response= (JSONObject) parser.parse(in);
					        JSONArray results=(JSONArray)((JSONObject)(response.get("responseData"))).get("results");
					        for (Object result: results){
					        		System.out.println(((JSONObject)result).get("url"));
					        }
						} 
				} else{
						System.out.println("Server sent binary response.");
				}
			} catch (MalformedURLException e) {
				System.err.println("Wrong URL format:"+e.getMessage());
			} catch (IOException e) {
				System.err.println("Error with connection:"+e.getMessage());
			} catch (ParseException e) {
				System.err.println("Error parsing:"+e.getMessage());
			}
			

		}

	}

