import java.util.concurrent.Semaphore;

public class TrainTicketMain {

	public static void main(String[] args) {
		try{
			Semaphore ticketCounters= new Semaphore(5,true);
			for (int i=0;i<20;i++){
				(new Thread(new Traveler(ticketCounters))).start();
				Thread.sleep(10);
			}
		} catch (InterruptedException e){}

	}

}
