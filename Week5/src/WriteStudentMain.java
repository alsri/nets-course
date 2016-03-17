import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriteStudentMain {

	public static void main(String[] args) {
		ArrayList<Student> students= new ArrayList<Student>();
		students.add(new Student("Robert","Brown","Dawson",12));
		students.add(new Student("Michael","Reds","Pearse",40));
		students.add(new Student("Joanna","Moore","Collins",62));
		students.add(new Student("Ann","Brown","Buffallo",132));
		
		try (ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream("students.ser"));) {
//			out.writeObject(students);
			for (Student s : students){
				out.writeObject(s);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
