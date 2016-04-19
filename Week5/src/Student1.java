import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Student1 implements Serializable{

	private static final long serialVersionUID = 2L;
	
	private String fname, lname;
	private transient Address address;
	
	public Student1(String fname, String lname,String street, int no){
		this.fname=fname;
		this.lname=lname;
		this.address= new Address(no, street);
	}
	
	public String toString(){
		StringBuilder sb= new StringBuilder();
		sb.append(this.fname);
		sb.append(" ");
		sb.append(this.lname);
		sb.append(" living at ");
		sb.append(this.address);
		return sb.toString();
	}
	
	private void writeObject(ObjectOutputStream out){
		try {
			out.defaultWriteObject();
			out.writeInt(this.address.getNo());
			out.writeUTF(this.address.getStreet());
		} catch (IOException e) {
			System.out.println("Could not write object"+e.getMessage());
		}
	}
	
	private void readObject(ObjectInputStream in){
		try {
			in.defaultReadObject();
			int no =in.readInt();
			String street = in.readUTF();
			this.address= new Address(no, street);
		} catch (IOException e) {
			System.out.println("Could not write object");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found or wrong version");
		}
	}

	public String getLname() {
		return this.lname;
	}

}
