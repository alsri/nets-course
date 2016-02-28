import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BigSumMainEcs {

	public static int THREAD_COUNT=1;
	public static int SLICE=100000;
	public static int     N=50000000;

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
		ExecutorCompletionService<Double> ecs= new ExecutorCompletionService<Double>(ex);
		ArrayList<Double> numbers= createLargeArray(N);
		try {
			long startTime=System.currentTimeMillis();
			int start=0;
			while(start<numbers.size()){
				ecs.submit(new BigSum(numbers, start, Math.min(start+SLICE, numbers.size())));
				start+=SLICE;
			}
			double result=0;
			for (int i=0;i<start/SLICE;i++){
				result+=ecs.take().get();
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
