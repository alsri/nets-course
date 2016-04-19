import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

public class ByteArrayExample {

	public static void main(String[] args) {
		
		Student[] students={new Student("Robert","Brown","Dawson",12),
				new Student("Michael","Reds","Pearse",40),
				new Student("Joanna","Moore","Collins",62),
				new Student("Ann","Brown","Buffallo",132)};
		
		ArrayList<Student> student_list= new ArrayList<Student>();
		Vector<Student> student_vector= new Vector<Student>();
		LinkedList<Student> student_linked_list= new LinkedList<Student>();
		
		for (Student s : students){
			student_list.add(s);
			student_vector.add(s);
			student_linked_list.add(s);
		}
		
		ArrayList<Object> all_lists=new ArrayList<>();
		all_lists.add(student_list);
		all_lists.add(student_vector);
		all_lists.add(student_linked_list);
		
		try {
			for (Object list : all_lists){
				ByteArrayOutputStream byte_stream= new ByteArrayOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(byte_stream);
				out.writeObject(list);
				byte[] bytes= byte_stream.toByteArray();
				System.out.format("the %s uses %d bytes to store %d students %n",
						list.getClass(),bytes.length,students.length);
				
				ByteArrayInputStream in_byte_stream= new ByteArrayInputStream(bytes);
				ObjectInputStream in = new ObjectInputStream(in_byte_stream);
				Collection<Student> read_list=(Collection<Student>) in.readObject();
				System.out.format("read back from byte[] %d students: %n",
						read_list.size());
				for (Student s : read_list){
					System.out.println(s);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
