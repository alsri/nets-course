import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class StudentClient {

	public static void main(String[] args) {
		System.out.println("Connecting to server...");
		try(Socket socket= new Socket(InetAddress.getLocalHost(),1500);
				ObjectOutputStream out= new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in= new ObjectInputStream(socket.getInputStream());
				BufferedReader localIn= new BufferedReader(new InputStreamReader(System.in));)
		{
			System.out.println("Connected.");
			String option=null;
			ArrayList<Student> result=null;
			System.out.println("Insert last name to query server, 'exit' to quit.");
			while( !(option=localIn.readLine()).equals("exit")){
				System.out.println("Asking for students with last name "+option);
				out.writeObject(option);
				out.flush();
				result=(ArrayList<Student>) in.readObject();
				System.out.println("Received "+result.size()+" students with last name "+ option);
				for (Student student: result){
					System.out.println(student);
				}
				System.out.println("Insert last name to query server, 'exit' to quit.");
			}
		} catch (UnknownHostException e) {
			System.err.println("Unknown host");
		} catch (IOException e) {
			System.err.println("Error: "+ e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found: "+ e.getMessage());
		}

	}

}
