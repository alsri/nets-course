import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class BigArrayMain {

	public static int THREAD_COUNT=100;
	public static int SLICE=100000;
	public static int     N=50000000;
	
	public static void main(String[] args) {
		ExecutorService ex= Executors.newFixedThreadPool(THREAD_COUNT);
		try {
			long startTime=System.currentTimeMillis();
			ArrayList<BigArray> tasks= new ArrayList<>();
			for (int i=0;i<N/SLICE;i++){
				tasks.add(new BigArray(SLICE));
			}
			if(N%SLICE>0){
				tasks.add(new BigArray(N%SLICE));
			}
			
			List<Future<ArrayList<Double>>> results = ex.invokeAll(tasks);
			System.out.format("%d numbers obtained in %d "
					+ "mseconds with %d threads%n",N,
					System.currentTimeMillis()-startTime, THREAD_COUNT);
			for (Future<ArrayList<Double>> res :results)
			{
				ArrayList<Double> array=res.get();
				//process result
			}
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
			System.out.println("Some thread failed");;
		} finally {
			ex.shutdown();
		}

	}
//	
//	public static void main(String[] args) {
//		ExecutorService ex= Executors.newFixedThreadPool(THREAD_COUNT);
//		ArrayList<Double> numbers= new ArrayList<>();
//		try {
//			long startTime=System.currentTimeMillis();
//			ArrayList<Future<ArrayList<Double>>> tasks= new ArrayList<>();
//			for (int i=0;i<N/SLICE;i++){
//				tasks.add(ex.submit(new BigArray(SLICE)));
//			}
//			if(N%SLICE>0){
//				tasks.add(ex.submit(new BigArray(N%SLICE)));
//			}
//			for (Future<ArrayList<Double>> res : tasks){
//				numbers.addAll(res.get());
//			}
//			System.out.format("An array of %d numbers obtained in %d "
//					+ "mseconds with %d threads%n",numbers.size(),
//					System.currentTimeMillis()-startTime, THREAD_COUNT);
//		} catch (InterruptedException e) {
//		} catch (ExecutionException e) {
//			System.out.println("A thread threw exception: "+e.getMessage());;
//		} finally {
//			ex.shutdown();
//		}
//
//	}

}
