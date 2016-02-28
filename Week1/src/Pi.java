
public class Pi implements Runnable{

	double precision;
	
	public Pi(double precision){
		this.precision=precision;
	}
	
	public void run(){
		double pi=0;
		double divisor=1.0;
		
		while(true){
			if(Thread.currentThread().interrupted()){
				System.out.println("Thread was interrupted, value of pi is "+pi);
				return;
			}
			if(Math.abs(pi-Math.PI) <this.precision)
			{
				System.out.println("Desired precision was reached, value of pi is "+pi);
				return;
			}
			pi+=4.0/divisor;
			divisor=-1*Math.signum(divisor)*(Math.abs(divisor)+2);
		}
	}
	
	public static void main(String[] args) {
		Pi task= new Pi(Double.parseDouble(args[0]));
		int waitTime=Integer.parseInt(args[1]);
		
		Thread t= new Thread(task);
		t.start();
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
		}
		t.interrupt();

	}

}
