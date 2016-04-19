import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriteStudentMain {

	public static void main(String[] args) {
		ArrayList<Student1> students= new ArrayList<Student1>();
		students.add(new Student1("Robert","Brown","Dawson",12));
		students.add(new Student1("Michael","Reds","Pearse",40));
		students.add(new Student1("Joanna","Moore","Collins",62));
		students.add(new Student1("Ann","Brown","Buffallo",132));
		
		try (ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream("students.ser"));) {
//			out.writeObject(students);
			for (Student1 s : students){
				out.writeObject(s);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
