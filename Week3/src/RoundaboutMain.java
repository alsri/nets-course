import java.util.Random;

public class RoundaboutMain {

	public static void main(String[] args) {
		try{
			Random rand= new Random(System.nanoTime());
			Roundabout roundabout = new Roundabout();
			for (int i=0;i<10;i++){
				int entrance=rand.nextInt(Roundabout.SIZE);
				int exit=rand.nextInt(Roundabout.SIZE);
				while(exit==entrance){
					exit=rand.nextInt(Roundabout.SIZE);
				}
				(new Thread(new Car(entrance,exit,roundabout))).start();
				Thread.sleep(rand.nextInt(2000)); //sleep max 2 secs between cars
			}
		}catch(InterruptedException e){}
	}

}
