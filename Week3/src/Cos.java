import java.util.concurrent.Callable;

public class Cos implements Callable<Double> {
	private double parameter;

	public Cos(double parameter){
		this.parameter=parameter;
	}
	@Override
	public Double call() throws Exception {
		System.out.format("%n%d Executing cos in thread %d",
				System.currentTimeMillis(),Thread.currentThread().getId());
		return Math.cos(parameter);
	}

}
