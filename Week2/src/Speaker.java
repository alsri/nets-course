
public class Speaker implements Runnable {
	private Microphone mic;
	private String name;
	private int round;
	
	public Speaker(Microphone mic, String name){
		this.mic=mic;
		this.name=name;
		this.round=0;
	}
	
	public void print(String message){
		System.out.println(System.currentTimeMillis()+ " "
				+this.name+": "+message);
	}

	@Override
	public void run() {
		try{
			while (true){//trying forever to take the floor
				this.print("Trying to give my speech");
				boolean spoke= mic.trySpeak(this.name);
				if (spoke){
					this.print("Gave my speech, now I go home");
					return;
				} else{
					this.print("Could not speak, I have to listen");
					String speech= mic.read(); 
					this.print("I listened to "+speech);
					this.round++;
					synchronized(mic){
						while (this.round>mic.getRound()){
							this.print("Waiting for round to finish");
							mic.wait();
						}
					}
				}
			}
		}catch(InterruptedException e){}
	}
}
