import java.nio.Buffer;
import java.nio.IntBuffer;

public class BufferTest {
	static void printBufferInfo(Buffer b){
		System.out.format("Buffer with capacity %d, "
				+ "current position %d, "
				+ "maximum limit %d, "
				+ "remaining %d.%n", b.capacity(),
				b.position(),b.limit(),b.remaining());
	}
	static void printBufferArray(IntBuffer b){
		for (int e : b.array()){
			System.out.print(e+" ");
		}
		System.out.println();
	}
	static void drain(IntBuffer b){
		System.out.print("Drain: ");
		while ( b.hasRemaining()){
			System.out.print(b.get()+" ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		IntBuffer intBuf= IntBuffer.allocate(20);
		System.out.println("New buffer:");
		printBufferInfo(intBuf);
		for (int i=0;i<10;i++){
			intBuf.put(i);
		}
		System.out.println("After fill");
		printBufferInfo(intBuf);
		printBufferArray(intBuf);
		drain(intBuf);
		System.out.println("After drain");
		printBufferInfo(intBuf);
		intBuf.clear();
		System.out.println("After clear");
		printBufferInfo(intBuf);
		printBufferArray(intBuf);
		drain(intBuf);
		System.out.println("After drain");
		printBufferInfo(intBuf);
		intBuf.clear();
		System.out.println("After clear");
		printBufferInfo(intBuf);
		for (int i=0;i<10;i++){
			intBuf.put(i+10);
		}
		System.out.println("After fill");
		printBufferInfo(intBuf);
		intBuf.flip();
		System.out.println("After flip");
		printBufferInfo(intBuf);
		printBufferArray(intBuf);
		drain(intBuf);
		System.out.println("After drain");
		printBufferInfo(intBuf);
	}

}
