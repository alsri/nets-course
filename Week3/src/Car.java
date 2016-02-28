
public class Car implements Runnable{

	private int entrance;
	private int exit;
	Roundabout roundabout;
	
	public Car(int entrance, int exit, Roundabout roundabout){
		this.entrance=entrance;
		this.exit=exit;
		this.roundabout=roundabout;
	}
	public void print(String message){
		System.out.println(System.currentTimeMillis()+ " - Car "
				+Thread.currentThread().getId()+": "+message);
	}
	@Override
	public void run() {
		try{
			//entering
			int location =this.entrance;
			this.print("New car trying to enter from entrance "+this.entrance);
			this.roundabout.getLock(this.entrance).writeLock().lock();
			this.print("Entered from entrance "+this.entrance);
			this.roundabout.getLock(this.entrance).writeLock().unlock();
			this.roundabout.getLock((location+1)%Roundabout.SIZE).readLock().lock();
			location=(location+1)%Roundabout.SIZE;
			this.print("Crossing quarter "+location);
			Thread.sleep(1000);
			//crossing quarters
			while(location !=this.exit){
				this.roundabout.getLock(location).readLock().unlock();
				this.roundabout.getLock((location+1)%Roundabout.SIZE).readLock().lock();
				location=(location+1)%Roundabout.SIZE;
				this.print("Crossing quarter "+location);
				Thread.sleep(1000);
			}
			//exiting
			this.roundabout.getLock(location).readLock().unlock();
			this.print("Exited from exit "+location);
		} catch (InterruptedException e){}
	}
	
}
