import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PowerMain {

	public static void main(String[] args) {
		double parameter= Double.parseDouble(args[0]);
		double result= 0;
		
		ArrayList<Future<Double>> runningTasks= new ArrayList<Future<Double>>();
		
		for (int i=2;i<5;i++){
			ExecutorService ex= Executors.newSingleThreadExecutor();
			runningTasks.add(ex.submit(new Power(parameter,i)));
			ex.shutdown();
		}
		try{
			for (Future<Double> t: runningTasks ){
				result+=t.get();
			} 
			System.out.println("result is "+ result);
		}catch (ExecutionException e){
			System.out.println("some thread has failed");
		} catch (InterruptedException e) {
				System.out.println("interrupted while waiting");
		}
		
	}
}
