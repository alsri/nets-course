
public class Word {
	private String word;
	public String getWord() {
		return word;
	}

	private long time;
	
	Word(String word, long time){
		this.word=word;
		this.time= time;
	}

	public long getTime() {
		return time;
	}

}
