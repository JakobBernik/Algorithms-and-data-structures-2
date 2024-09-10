
import java.util.*;

public class Izziv1 {
	
	public static int[] generateTable(int n) {
		
		int[] Tab = new int[n];
		for(int i =0;i<n;i++){
		 Tab[i] = i+1;	
		}
		
		return Tab;
	}
	
	public static int findLinear(int[] a, int v) {
		int i = 0;
		while(a[i]!=v){
		i++;	
		}
		return a[i];
	}
	
	public static int findBinary(int[] a, int l, int r, int v) {
	   int i = (l+r)/2; // zaokrozi navzdol
		if(a[i]==v){ // ce iskani element enak trenutnemu elementu
		  return a[i];
		}
		if(l+1 == r){ // ce meji omejujeta izbiro na zgolj dva elementa in na nizjem ni iskani element(zaradi floor) je pravilni drugi, torej ga vrnemo
		 return a[i+1];	
		}
		else if(a[i] > v){ // iskana vrednost se nahaja v polovici z nizjimi vrednostmi. zgornjo mejo zmanjsamo
		 return findBinary(a,l,i,v);	
		}
		else{ // iskana vrednost se nahaja v polovici z vecjimi vrednostmi. spodnjo mejo povecamo
			return findBinary(a,i,r,v);
		}
		
		
    }
	public static long timeLinear(int n) {
		Random rand = new Random();
		int[] tab = generateTable(n);
		long startTime = System.nanoTime();
		for(int i = 0;i< 1000;i++){
	 	int value = rand.nextInt(n) + 1;
	 	findLinear(tab,value);		
		}
		long executionTime = System.nanoTime() - startTime;
		return executionTime/1000;
    }
	
	public static long timeBinary(int n) {
		Random rand = new Random();
		int[] tab = generateTable(n);
		long startTime = System.nanoTime();
		for(int i = 0;i< 1000;i++){
	 	int value = rand.nextInt(n) + 1;
	 	findBinary(tab,0,n-1,value);		
		}
		long executionTime = System.nanoTime() - startTime;
		return executionTime/1000;
    }
	

	public static void main(String[] args) {
		
		System.out.format("%-10s%-10s%-10s\n", "n","Linearno","Binarno");
		System.out.format("%-30s\n","---------|---------|----------");
      for(int i = 1000 ;i <= 100000;i += 1000){
    	long linear = timeLinear(i);
    	long binary = timeBinary(i);
    	System.out.format("%-10d%-10d%-10d\n",i,linear,binary); 
      } 
	}
}
