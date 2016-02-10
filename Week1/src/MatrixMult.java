import java.util.ArrayList;

public class MatrixMult implements Runnable {

	private static ArrayList<ArrayList<Double>> m1, m2,result;
	private int row;
	
	public MatrixMult(ArrayList<ArrayList<Double>> m1,ArrayList<ArrayList<Double>> m2, int row){
		this.m1=m1;
		this.m2=m2;
		this.row=row;
		//init result
	}
	
	public MatrixMult(int row){
		this.row=row;
	}	

	public static ArrayList<ArrayList<Double>> getResult(){
		return MatrixMult.result;
	}
	
	public void run(){
		for (int i=0;i<MatrixMult.m1.get(0).size();i++){
			double element=0;
			for (int j=0;j<MatrixMult.m1.get(0).size();j++){
				element+=MatrixMult.m1.get(this.row).get(j)*MatrixMult.m2.get(j).get(row);
			}
			Matrix
		}
	}
	
	public static void main(String[] args) {
		//read two matrices from file
		//create threads
		//run threads
		//join threads
		//print result
		
	}

}
