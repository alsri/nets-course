import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class StudentClientHandler implements Runnable {
	Socket client;
	ArrayList<Student> students;

	public StudentClientHandler(Socket client, ArrayList<Student> students) {
		this.students=students;
		this.client=client;
	}

	@Override
	public void run() {
		System.out.println("Handling client");
		try(ObjectInputStream in= new ObjectInputStream(client.getInputStream());
				ObjectOutputStream out= new ObjectOutputStream(client.getOutputStream());){
			while(true){
				System.out.println("Ready for a new name...");
				String lname=(String) in.readObject();
				System.out.println("Received request for "+lname);
				ArrayList<Student> response= new ArrayList<>();
				for (Student student : this.students){
					if (student.getLname().trim().toLowerCase().equals(
							lname.trim().toLowerCase())){
						response.add(student);
					}
				}
				out.writeObject(response);
				out.flush();
			}
		} catch (IOException e) {
				System.err.println("Error: "+ e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found: "+ e.getMessage());
		} finally{
			try {client.close();
			} catch (IOException e) {}
		}
		
	}

}
