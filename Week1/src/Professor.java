import java.util.ArrayList;

public class Professor implements Runnable{
	
	private String subject;
	private ArrayList<Student> students;
	
	public Professor(String subject,ArrayList<Student> students){
		this.subject=subject;
		this.students=students;
	}
	
	private void grade(Student s){
		switch(subject)
		{
		case "math":
			s.setMath_grade(25);
			break;
		case "prog":
			s.setProg_grade(29);
			break;
		default: break;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ArrayList<Student> students= new ArrayList<Student>();
		students.add(new Student("Alina Sirbu"));
		students.add(new Student("Alessandro Lulli"));
		
		Professor prof1= new Professor("math",students);
		Professor prof2= new Professor("prog",students);
		
		Thread t1= new Thread(prof1);
		Thread t2= new Thread(prof2);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		for (Student s: students){
			System.out.println(s.getName()+ " : " + s.getAverage());
		}
		
	}

	@Override
	public void run() {
		try{
			for (Student s: this.students){
				long sleeptime=System.nanoTime()%10;
				Thread.sleep(sleeptime*1000);
				this.grade(s);
				System.out.println(Thread.currentThread()+" graded "
				+s.getName()+ " average is " + s.getAverage());
			}
		}catch (InterruptedException e){}
	}

}
