import java.util.ArrayList;
import java.util.concurrent.Callable;

public class BigSum implements Callable<Double> {

	private ArrayList<Double> numbers;
	private int start,end;
	
	public BigSum(ArrayList<Double> numbers, int start, int end){
		this.numbers=numbers;
		this.start=start;
		this.end=end;
	}
	@Override
	public Double call() throws Exception {
		double result= 0;
		for (int i=start;i<end;i++){
			result+=numbers.get(i);
		}
		return result;
	}

}
