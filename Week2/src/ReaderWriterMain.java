import java.util.Random;

public class ReaderWriterMain {

	public static void main(String[] args) {
		Counter c= new Counter();
		Random rand = new Random(System.currentTimeMillis());
		double writerProb=0.2;
		
		for (int i=0;i<20;i++){
			if (rand.nextDouble()<writerProb){
				(new Thread(new Writer(c))).start();
			}
			else{
				(new Thread(new Reader(c))).start();
			}
		}

	}

}
