import java.util.concurrent.Callable;

public class Tan implements Callable<Double> {
	private double parameter;

	public Tan(double parameter){
		this.parameter=parameter;
	}
	@Override
	public Double call() throws Exception {
		System.out.format("%n%d Executing tan in thread %d",
				System.currentTimeMillis(),Thread.currentThread().getId());
		return Math.tan(parameter);
	}

}
