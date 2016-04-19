import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ModifyFileIO {

	public static void main(String[] args) {
		long start= System.currentTimeMillis();
		ArrayList<String> names= new ArrayList<>();
		ArrayList<Double> prices= new ArrayList<>();
		String prodName= "chocolate bar";
		double newPrice=2.5;
		boolean canWrite=true;
		//first read all data
		try(DataInputStream in = new DataInputStream(new FileInputStream("Products.dat"))){
			while(true){
				int nameLength=in.readInt();
				StringBuilder name=new StringBuilder();
				for(int i=0;i<nameLength;i++)
					name.append(in.readChar());
				double price=in.readDouble();
				names.add(name.toString());
				if (name.toString().equals(prodName)){
					//found the product, can overwrite the price
					prices.add(newPrice);
				} else {
					prices.add(price);
				}
			}
		} catch (EOFException e){ //end of file, don't do anything
		} catch (IOException e) {
			System.out.println("Something went wrong: "+e.getMessage());
			canWrite=false;
		}
		//then write all data
		if(canWrite){
			try(DataOutputStream out = new DataOutputStream(new FileOutputStream("Products.dat"))){
				for (int i=0;i<names.size();i++){
					out.writeInt(names.get(i).length());
					out.writeChars(names.get(i));
					out.writeDouble(prices.get(i));
				}
				System.out.format("Change completed.");
			} catch (IOException e) {
				System.out.println("Something went wrong: "+e.getMessage());
				
			}
		}
		System.out.format("Ran %d milliseconds.%n",System.currentTimeMillis()-start);

	}

}
