import java.util.concurrent.Callable;

public class Sin implements Callable<Double> {
	private double parameter;

	public Sin(double parameter){
		this.parameter=parameter;
	}
	@Override
	public Double call() throws Exception {
		System.out.format("%n%d Executing sin in thread %d",
				System.currentTimeMillis(),Thread.currentThread().getId());
		return Math.sin(parameter);
	}

}
