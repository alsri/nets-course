import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BigSumMain {
	
	public static int THREAD_COUNT=1;
	public static int SLICE=100000;
	public static int N=20000000;

	public static ArrayList<Double> createLargeArray(int size){
		Random rand = new Random();
		ArrayList<Double> result = new ArrayList<>(size);
		for (int i=0;i<size;i++){
			//double between -1 and 1
			result.add(rand.nextDouble()*2-1);
		}
		return result;
	}
	
	public static void main(String[] args) {
		ExecutorService ex= Executors.newFixedThreadPool(THREAD_COUNT);
		ArrayList<Double> numbers= createLargeArray(N);
		try {
			long startTime=System.currentTimeMillis();
			ArrayList<BigSum> tasks= new ArrayList<>();
			int start=0;
			while(start<numbers.size()){
				tasks.add(new BigSum(numbers, start, Math.min(start+SLICE, numbers.size())));
				start+=SLICE;
			}
			
			List<Future<Double>> results = ex.invokeAll(tasks);
			double result=0;
			for (Future<Double> res : results){
				result+=res.get();
			}
			System.out.format("Sum of %d numbers is %f obtained in %d "
					+ "mseconds with %d threads%n",N,result,
					System.currentTimeMillis()-startTime, THREAD_COUNT);
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
			System.out.println("A thread threw exception: "+e.getMessage());;
		} finally {
			ex.shutdown();
		}
		
		
	}

}
