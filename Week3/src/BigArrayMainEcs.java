import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class BigArrayMainEcs {

	public static int THREAD_COUNT=100;
	public static int SLICE=100000;
	public static int     N=50000000;
	
	public static void main(String[] args) {
		ExecutorService ex= Executors.newFixedThreadPool(THREAD_COUNT);
		ExecutorCompletionService<ArrayList<Double>> ecs= new ExecutorCompletionService<>(ex);
		try {
			for (int i=0;i<N/SLICE;i++){
				ecs.submit(new BigArray(SLICE));
			}
			if(N%SLICE>0){
				ecs.submit(new BigArray(N%SLICE));
			}
			
			for (int i=0;i<(N/SLICE+Math.signum(N%SLICE));i++)
			{
				ArrayList<Double> array=ecs.take().get();
				//process result
			}
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
			System.out.println("Some thread failed");;
		} finally {
			ex.shutdown();
		}

	}
}
