import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Streams {

	public static int byteArray2Int(byte[] bytes){
		int result=0;
		for (byte b : bytes){
			result=(result<<8)+(b>=0?b:(b+256));
		}
		return result;
	}
	
	public static double byteArray2Double(byte[] bytes){
		long result=0;
		for (byte b : bytes){
			result=(result<<8)+(b>=0?b:(b+256));
		}
		return Double.longBitsToDouble(result);
	}
	
	public static byte[] int2ByteArray(int x)
	{
		byte[] result= new byte[Integer.BYTES];
		for (int i=0;i<Integer.BYTES;i++)
		{
			result[Integer.BYTES-i-1]=(byte) (x>>(i*8));
		}
		return result;
	}
	
	public static byte[] double2ByteArray(double x)
	{
		byte[] result= new byte[Long.BYTES];
		long bits=Double.doubleToLongBits(x);
		for (int i=0;i<Long.BYTES;i++)
		{
			result[Long.BYTES-i-1]=(byte) (bits>>(i*8));
		}
		return result;
	}
	
	public static void main(String[] args) {
		int integer=2435;
		double real=2456754.6;
		char character='A';
		String name="Alina";
		int nameByteLength=name.getBytes().length;
		FileOutputStream out=null;
		FileInputStream in = null;
		try {
			out= new FileOutputStream("data.dat");
			out.write(int2ByteArray(integer));
			out.write(double2ByteArray(real));
			out.write(int2ByteArray(character));
			out.write(name.getBytes());
			System.out.println("I wrote "+integer+" "+real+" "+character+" "+name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {}
		}
		
		
		try {
			in= new FileInputStream("data.dat");
			
			byte[] bytes=new byte[Integer.BYTES]; 
			in.read(bytes);
			int readInteger=byteArray2Int(bytes);
			
			bytes=new byte[Long.BYTES];
			in.read(bytes);
			double readReal=byteArray2Double(bytes);
			
			bytes=new byte[Integer.BYTES];
			in.read(bytes);
			char readCharacter=(char)(byteArray2Int(bytes));
			
			bytes=new byte[nameByteLength];
			in.read(bytes);
			String readName=new String(bytes);
			
			System.out.println("I read "+readInteger+" "+readReal+" "+readCharacter+" "+readName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {}
		}
		
	}

}
