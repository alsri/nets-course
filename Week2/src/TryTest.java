import java.util.concurrent.locks.ReentrantLock;

public class TryTest implements Runnable {
	ReentrantLock l1,l2;
	public TryTest(ReentrantLock l1,ReentrantLock l2){
		this.l1=l1;
		this.l2=l2;
	}
	@Override
	public void run() {
		try{
		this.l1.lock();
		try{
			System.out.println("locked l1");
			this.l2.lock();
			
			try{
				System.out.println("locked l2");
				throw new Exception("inside");
			}finally{
				this.l2.unlock();
				System.out.println("unlock l2");
			}
			
			
		}finally{
			this.l1.unlock();
			System.out.println("unlock l1");
		}
		} catch (Exception e)
		{System.out.println(e.getMessage());}
		
	}
	
	public static void main(String[] args) {
		ReentrantLock l1= new ReentrantLock();
		ReentrantLock l2= new ReentrantLock();
		for (int i=0; i<5;i++){
			(new Thread(new TryTest(l1,l2))).start();
		}
	}
	
	
}
