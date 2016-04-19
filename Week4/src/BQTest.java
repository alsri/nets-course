import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BQTest {

	public static O o = new O();
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<O> bq= new ArrayBlockingQueue<O>(10);
		bq.put(o);
		System.out.println(bq.size());
		bq.put(o);
		System.out.println(bq.size());
		bq.take();
		System.out.println(bq.size());

	}

}
