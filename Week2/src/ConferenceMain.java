
public class ConferenceMain {

	public static final int STUDENT_COUNT=5;
	
	public static void main(String[] args) {
		Microphone mic= new Microphone(STUDENT_COUNT-1);//listeners are 1-number of students
		for (int i=0;i<STUDENT_COUNT;i++){
			(new Thread(new Speaker(mic, "Student "+i))).start();
		}

	}

}
