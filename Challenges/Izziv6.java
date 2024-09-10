import java.util.Scanner;

public class Izziv6 {

	public static void main(String[] args) {
		
		int stopnja = Integer.valueOf(args[0]);
		Scanner sc = new Scanner(System.in);
		Compleks[] koeficienti = new Compleks[stopnja];
		for(int i = 0; i < stopnja; i++){
			koeficienti[i] = new Compleks(sc.nextDouble(),0);
		}
		sc.close();
		Compleks[] rezultat = FFT(koeficienti);
	}
	
	public static Compleks[] FFT(Compleks[] koeficienti){ //rekurzivni del
	 
	  int n = koeficienti.length;
	    if(n == 1){
		 return koeficienti;  
	    }
	    Compleks[] sodiDel = dobi(koeficienti,0);
	    Compleks[] lihiDel = dobi(koeficienti,1);		
	    Compleks[] sod = FFT(sodiDel);
	    Compleks[] lih = FFT(lihiDel);
	    
	    double kot = (360/n)*Math.PI/180;
	    Compleks w = new Compleks(Math.cos(kot),Math.sin(kot));		
		Compleks wk = new Compleks(1,0);
	    int k = 2;
		Compleks[] y = new Compleks[n];
		for(int i = 0; i < n/2; i++){
			y[i] = sod[i].add(lih[i].multiply(wk)); 
			y[i+n/2] = sod[i].subtract(lih[i].multiply(wk));
			wk = wk.multiply(w);
		}
		for(int i = 0; i < n; i++){
		y[i].printaj();
		System.out.print(" ");
		}
		System.out.println();
	return y;
	}
	
	public static Compleks[] dobi(Compleks[] koef,int stik){ // delitev 
		int n = koef.length/2;
		Compleks[] rez = new Compleks[n];
		int j = 0;
		int i = 0;
		while(i < koef.length && j<rez.length){
		 rez[j] = new Compleks(koef[i+stik]);
		 j++;
		 i+=2;
		}
		return rez;
		}
}

class Compleks{
	double real;
	double imaginary;
	public Compleks(double r,double i){
		real = r;
		imaginary = i;		
	}
	public Compleks(Compleks o){
		real = o.real;
		imaginary = o.imaginary;
	}
	public Compleks add(Compleks a){
		return new Compleks((this.real+a.real), this.imaginary+a.imaginary);	
	}
	public  Compleks subtract(Compleks a){
	    return new Compleks((this.real-a.real), (this.imaginary-a.imaginary));	
		}
	public Compleks multiply(Compleks a){
	    return new Compleks((this.real*a.real)-(this.imaginary*a.imaginary),(this.real*a.imaginary)+(this.imaginary*a.real)); 
		}
	public void printaj(){
			System.out.format("%.1f", this.real);
			if(this.imaginary > 0.0){
				System.out.format("+%.1fi ", this.imaginary);	
		    }else if(this.imaginary != 0.0){
				System.out.format("%.1fi ", this.imaginary);	
			}
	}
}