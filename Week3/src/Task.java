import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class Task implements Callable<Double>{

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService ex= Executors.newFixedThreadPool(10);
		Task task= new Task();
		ArrayList<Future<Double>> res= new ArrayList<Future<Double>>();
		for (int i=0;i<100;i++){
			res.add(ex.submit(task));
		}
		for (Future<Double> f : res){
			System.out.println(f.get());
		}
		ex.shutdown();
	}

	@Override
	public Double call() throws Exception {
		Thread.sleep(System.currentTimeMillis()%1000);
		return ThreadLocalRandom.current().nextDouble();
	}

}
