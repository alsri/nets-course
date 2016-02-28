import java.util.concurrent.Callable;

public class Power implements Callable<Double> {
	private double parameter;
	private int power;


	public Power(double parameter,int power){
		this.parameter=parameter;
		this.power=power;
	}
	@Override
	public Double call() throws Exception {
		System.out.format("%d Executing power %d of %f in thread %d%n",
				System.nanoTime(),this.power,this.parameter,Thread.currentThread().getId());
		return Math.pow(this.parameter,this.power);
	}

}
