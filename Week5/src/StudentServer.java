import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentServer {

	public static void main(String[] args) {
		ArrayList<Student> students= new ArrayList<Student>();
		students.add(new Student("Robert","Brown","Dawson",12));
		students.add(new Student("Michael","Reds","Pearse",40));
		students.add(new Student("Joanna","Moore","Collins",62));
		students.add(new Student("Ann","Brown","Buffallo",132));
		
		ExecutorService es= Executors.newFixedThreadPool(20);
		
		try(ServerSocket server= new ServerSocket(1500)){
			while(true){
				System.out.println("Waiting for clients...");
				Socket client=server.accept();
				System.out.println("Client arrived.");
				StudentClientHandler handler= new StudentClientHandler(client,students);
				es.submit(handler);
			}
		} catch (IOException e) {
			System.err.println("Error: "+ e.getMessage());
		} finally {
			es.shutdown();
		}

	}

}
