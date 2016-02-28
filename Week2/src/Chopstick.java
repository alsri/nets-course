import java.util.concurrent.locks.ReentrantLock;

public class Chopstick extends ReentrantLock{
	int priority;
	
	public Chopstick(int priority){
		super();
		this.priority=priority;
	}

	public int getPriority() {
		return priority;
	}
}
