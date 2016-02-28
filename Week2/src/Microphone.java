
public class Microphone {
	private String speech;
	private int totalReaders;
	private int readerCount;
	private int round;
	
	public Microphone(int totalReaders){
		this.totalReaders=totalReaders;
		this.speech="";
		this.readerCount=0;
		this.round=0;
	} 
	
	public synchronized void reset(){
		this.speech="";
		this.readerCount=0;
		this.totalReaders--;
		this.round++;
		this.notifyAll();
	}
	
	public synchronized boolean trySpeak(String speech){
		if (this.speech.equals("")){
			this.speech=speech;
			return true;
		}else{
			return false;
		}
	}
	
	public synchronized String read(){
		String result=this.speech;
		this.readerCount++;
		if (this.readerCount==this.totalReaders){
			//round finished
			this.reset();
		}
		return result;
	}
	
	public synchronized int getRound(){
		return this.round;
	}
	
}
