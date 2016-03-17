
public class Address {
	int no;
	public int getNo() {
		return no;
	}

	public String getStreet() {
		return street;
	}

	String street;
	
	public Address(int n, String street){
		this.no=n;
		this.street=street;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.no);
		sb.append(" ");
		sb.append(this.street);
		sb.append(" street.");
		return sb.toString();
	}
}
