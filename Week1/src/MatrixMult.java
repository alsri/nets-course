import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class MatrixMult implements Runnable {

	private static ArrayList<ArrayList<Double>> m1, m2,result;
	private int row;
	
	public MatrixMult(ArrayList<ArrayList<Double>> m1,ArrayList<ArrayList<Double>> m2, int row){
		MatrixMult.m1=m1;
		MatrixMult.m2=m2;
		this.row=row;
		//init result
		MatrixMult.result=new ArrayList<ArrayList<Double>>();
		for (int i=0;i<MatrixMult.m1.size();i++){
			ArrayList<Double> resultRow = new ArrayList<Double>();
			for (int j=0;j<MatrixMult.m2.get(0).size();j++){
				resultRow.add(0.0);
			}
			MatrixMult.result.add(resultRow);
		}
	}
	
	public MatrixMult(int row){
		this.row=row;
	}	

	public static ArrayList<ArrayList<Double>> getResult(){
		return MatrixMult.result;
	}
	
	public void run(){
		ArrayList<Double> myRow= new ArrayList<Double>();
		for (int i=0;i<MatrixMult.m2.get(0).size();i++){
			double element=0.0;
			for (int j=0;j<MatrixMult.m1.get(0).size();j++){
				element+=MatrixMult.m1.get(this.row).get(j)*MatrixMult.m2.get(j).get(i);
			}
			myRow.add(element);
		}

		MatrixMult.result.set(this.row,myRow);
	}
	public static ArrayList<ArrayList<Double>> readMatrix(String fileName) throws IOException{
		ArrayList<ArrayList<Double>> result=new ArrayList<ArrayList<Double>>();
		BufferedReader reader= new BufferedReader(new FileReader(fileName));
		String line;
		while((line=reader.readLine()) != null){
			ArrayList<Double> resultRow = new ArrayList<Double>();
			String[] tokens=line.split("\t");
			for (String t : tokens){
				resultRow.add(new Double(t));
			}
			result.add(resultRow);
		}
		reader.close();
		return result;
	}
	
	public static ArrayList<ArrayList<Double>> generateLargeMatrix(int rows, int columns) {
		ArrayList<ArrayList<Double>> result=new ArrayList<ArrayList<Double>>();
		Random rand= new Random(System.currentTimeMillis()*10000);
		for (int i=0; i<rows;i++){
			ArrayList<Double> resultRow = new ArrayList<Double>();
			for (int j=0; j<columns;j++){
				resultRow.add(rand.nextDouble());
			}
			result.add(resultRow);
		}
		return result;
	}
	
	public static void multWithThreads(ArrayList<ArrayList<Double>> m1, ArrayList<ArrayList<Double>> m2) throws InterruptedException{
		//create threads
		int nthreads=m1.size();
		System.out.println("Creating "+nthreads+" threads");
		ArrayList<Thread> threads= new ArrayList<Thread>();
		MatrixMult runnable= new MatrixMult(m1,m2,0);
		Thread t= new Thread(runnable);
		threads.add(t);
		for (int i=1;i<nthreads;i++){
			runnable= new MatrixMult(i);
			t= new Thread(runnable);
			threads.add(t);
		}
		
		//run threads
		for (Thread ti : threads){
			ti.start();
		}
		//join threads
		for (Thread ti : threads){
			ti.join();
		}
		//now all threads are done, print result
		System.out.println("All threads finished, result is:");
		for (ArrayList<Double> row: result){
			for (Double d :row){
				System.out.print(d+" ");
			}
			System.out.println("");
		}
		return;
	}
	
	public static void multWithoutThreads(ArrayList<ArrayList<Double>> m1, ArrayList<ArrayList<Double>> m2){
		ArrayList<ArrayList<Double>> result=new ArrayList<ArrayList<Double>>();
		for (int i=0;i<m1.size();i++){
			ArrayList<Double> resultRow = new ArrayList<Double>();
			for (int j=0;j<m2.get(0).size();j++){
				double element=0.0;
				for (int k=0;k<m1.get(0).size();k++){
					element+=m1.get(i).get(k)*m2.get(k).get(j);
				}
				resultRow.add(element);
			}
			result.add(resultRow);
		}
		
		for (ArrayList<Double> row: result){
			for (Double d :row){
				System.out.print(d+" ");
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		//read two matrices from file
		try {
			ArrayList<ArrayList<Double>> m1, m2;
			//m1=readMatrix("matrix1.txt");
			//m2=readMatrix("matrix2.txt");
			m1=generateLargeMatrix(1000, 1000);
			m2=generateLargeMatrix(1000,1000);
			long time= System.currentTimeMillis();
			multWithThreads(m1, m2);
			System.out.println("With threads, finished in seconds: "+ ((System.currentTimeMillis()-time)/1000.0));
			time= System.currentTimeMillis();
			multWithoutThreads(m1, m2);
			System.out.println("Without threads, finished in seconds: "+ ((System.currentTimeMillis()-time)/1000.0));
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
