import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Roundabout {
	public static int SIZE=4;
	ArrayList<ReentrantReadWriteLock> locks;
	
	public Roundabout(){
		this.locks=new ArrayList<>();
		for (int i=0;i<SIZE;i++){
			this.locks.add(new ReentrantReadWriteLock());
		}
	}
	
	public ReentrantReadWriteLock getLock(int entrance){
		return this.locks.get(entrance);
	}
	
}
