import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadStudentMain {

	public static void main(String[] args) {
		try(ObjectInputStream in= new ObjectInputStream(
				new FileInputStream("students.ser"));){
//			ArrayList<Student> stds= (ArrayList<Student>) in.readObject();
//			for (Student s : stds){
//				System.out.println(s);
//			}
			Student s;
			while (true){
				try{
				s = (Student) in.readUnshared();
				System.out.println(s);
				} catch (IOException e){
					System.out.println("Stream ended or error");
					e.printStackTrace();
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
