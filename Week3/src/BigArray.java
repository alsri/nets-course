import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class BigArray implements Callable<ArrayList<Double>> {

	private int size;
	
	public BigArray( int size){
		this.size=size;
	}
	@Override
	public ArrayList<Double> call() throws Exception {
		ThreadLocalRandom rand= ThreadLocalRandom.current();
		ArrayList<Double> result= new ArrayList<>(this.size);
		
		for (int i=0;i<this.size;i++){
			result.add(rand.nextDouble()*2-1);
		}
		return result;
	}

}
