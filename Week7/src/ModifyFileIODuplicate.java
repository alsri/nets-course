import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ModifyFileIODuplicate {

	public static void main(String[] args) {
		long start= System.currentTimeMillis();
		String prodName= "chocolate bar";
		double newPrice=2.2;
		boolean canRename=true;
		//first read all data
		try(DataInputStream in = new DataInputStream(new FileInputStream("Products.dat"));
				DataOutputStream out = new DataOutputStream(new FileOutputStream("ProductsCopy.dat"))){
			while(true){
				int nameLength=in.readInt();
				StringBuilder name=new StringBuilder();
				for(int i=0;i<nameLength;i++)
					name.append(in.readChar());
				out.writeInt(nameLength);
				out.writeChars(name.toString());
				double price=in.readDouble();
				if (name.toString().equals(prodName)){
					//found the product, can overwrite the price
					out.writeDouble(newPrice);
				} else {
					out.writeDouble(price);
				}
			}
		} catch (EOFException e){ //end of file, don't do anything
		} catch (IOException e) {
			System.out.println("Something went wrong: "+e.getMessage());
			canRename=false;
		}
		//then rename  duplicate file
		if(canRename){
			File original= new File("Products.dat");
			File modified= new File("ProductsCopy.dat");
			modified.renameTo(original);
		}
		System.out.format("Ran %d milliseconds.%n",System.currentTimeMillis()-start);
	}
}
