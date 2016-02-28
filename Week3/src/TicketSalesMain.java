import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TicketSalesMain {

	public static void main(String[] args) {
		ExecutorService ticketCounters= Executors.newFixedThreadPool(5);
		for (int i =0;i<20;i++){
			ticketCounters.execute(new Traveler(i));
		}
		ticketCounters.shutdown();
		
	}
}
