import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InvokeAnyMain {

	public static void main(String[] args) {
		ExecutorService ex=Executors.newFixedThreadPool(3);
		ArrayList<Callable<Double>> tasks= new ArrayList<Callable<Double>>();
		for (int i=0;i<5;i++){
			tasks.add(new ExceptionTask());
		}
		//tasks.add(new Power(3,2));
		//tasks.add(new Power(4,2));
		for (int i=0;i<5;i++){
			tasks.add(new ExceptionTask());
		}
		
		Double result;
		try {
			result = ex.invokeAny(tasks);
			System.out.println("Result is "+result);
		} catch (InterruptedException e) {
		}catch ( ExecutionException e) {
			System.out.println("No threda finished correctly "+e.getCause());
		}finally{
			ex.shutdown();
		}
		
		
		
		
		

	}

}
