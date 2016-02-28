import java.util.concurrent.Callable;

public class ExceptionTask implements Callable<Double>{

	@Override
	public Double call() throws Exception {
		Thread.sleep(100);
		System.out.format("%d throwing an exception in thread %d%n",
				System.nanoTime(),Thread.currentThread().getId());
		throw new Exception("I only do exceptions");
	}

}
