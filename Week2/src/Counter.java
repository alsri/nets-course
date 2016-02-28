import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Counter {
	private int count;
	private ReentrantReadWriteLock lock;
	
	public Counter(){
		this.count=0;
		this.lock=new ReentrantReadWriteLock();
	}
	
	public void increment(){
		this.lock.writeLock().lock();
		this.count++;
		try {
			Thread.sleep(100);// simulate longer write
		} catch (InterruptedException e) {} 
		this.lock.writeLock().unlock();
	}

	public int getCount() {
		this.lock.readLock().lock();
		int count=this.count;
		try {
			Thread.sleep(200);// simulate longer read
		} catch (InterruptedException e) {} 
		this.lock.readLock().unlock();
		return count;
	}


}
