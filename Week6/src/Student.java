import java.io.Serializable;

public class Student implements Serializable{

	private static final long serialVersionUID = 2L;
	
	private String fname, lname;
	private String streetAddress;
	private int addressNo;
	
	public Student(String fname, String lname,String street, int no){
		this.fname=fname;
		this.lname=lname;
		this.streetAddress= street;
		this.addressNo=no;
	}
	
	public String toString(){
		StringBuilder sb= new StringBuilder();
		sb.append(this.fname);
		sb.append(" ");
		sb.append(this.lname);
		sb.append(" living at ");
		sb.append(this.addressNo);
		sb.append(" ");
		sb.append(this.streetAddress);
		sb.append(" street.");
		return sb.toString();
	}
	public String getLname() {
		return this.lname;
	}

}
