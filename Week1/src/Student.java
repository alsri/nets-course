
public class Student {
	private String name;
	private int math_grade, prog_grade;
	private Integer math_lock, prog_lock;
	
	public Student(String name){
		this.name=name;
		this.math_grade=0;
		this.prog_grade=0;
		this.math_lock=new Integer(0);
		this.prog_lock=new Integer(0);
	}
	public String getName() {
		synchronized(this.name){
			return name;
		}
	}
	public  void setName(String name) {
		synchronized(this.name){
			this.name = name;
		}
	}
	public  int getMath_grade() {
		synchronized(this.math_lock){
			return math_grade;
		}
	}
	public  void setMath_grade(int math_grade) {
		synchronized(this.math_lock){
			this.math_grade = math_grade;
		}
	}
	public  int getProg_grade() {
		synchronized(this.prog_lock){
			return prog_grade;
		}
	}
	public  void setProg_grade(int programming_grade) {
		synchronized(this.prog_lock){
			this.prog_grade = programming_grade;
		}
	}
	public  double getAverage(){
		synchronized(this.prog_lock){
			synchronized(this.math_lock){
				return (this.math_grade+this.prog_grade)/2;
			}
		}
		
	}
}
