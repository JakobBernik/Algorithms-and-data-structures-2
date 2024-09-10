import java.util.Scanner;

public class Izziv5 {

	public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       Complex Operand1=null,Operand2=null;
       int n=0;
       while(true){
        System.out.println("Vtipkaj ukaz (+, -, *, /, w):");
        String Ukaz = sc.nextLine();
         if(Ukaz.equals("")){
    	  sc.close();
       	  System.out.println("izhod");
       	  break; 
         }else if(Ukaz.equals("w")){
    	   n = sc.nextInt();
    	   sc.nextLine();
         }else if(Ukaz.equals("+") || Ukaz.equals("-") || Ukaz.equals("*") || Ukaz.equals("/")){
           String Op1 = sc.nextLine(); // preberemo prvo stevilo kot string
           String Op2 = sc.nextLine(); // preberemo drugo stevilo kot string
           Operand1 = new Complex(Op1); // na osnovi prebranih podatkov sedaj zgradimo kompleksni stevili Operand1 in Operand2
           Operand2 = new Complex(Op2);
         }else{
    	   System.out.println("Napacen ukaz!");
    	   continue;   
         }
        switch(Ukaz){ // z ukazom switch izvedemo zeleno operacijo
        
        case "+":
         Complex vsota = Operand1.add(Operand2);
         System.out.print("rezultat: ");
         vsota.print();
         System.out.println();
         break;
         
        case "-":
         Complex razlika = Operand1.subtract(Operand2);
         System.out.print("rezultat: ");
         razlika.print();
         System.out.println();
         break;
         
        case "*":
         Complex produkt = null;	
          if(Operand2.imaginary==0.0){ // v primeru da ima Operand2 imaginarno komponento enako 0, se ga obravnava kot realno stevilo in se ga zmnozi z Operand1 na ustrezen nacin 
           produkt = Operand1.multiply(Operand2.real);	
          }else{
           produkt = Operand1.multiply(Operand2);	
          }
         System.out.print("rezultat: ");
         produkt.print();
         System.out.println();
         break;
         
        case "/":
         Complex kolicnik = Operand1.divide(Operand2);
         System.out.print("rezultat: ");
         kolicnik.print();
         System.out.println();
         break;
         
        case "w":
         Complex Nkoren = new Complex();
         Nkoren = Nkoren.n_root(n);
         System.out.print("rezultat: ");
         Nkoren.print();
         Complex koreni;
          for(int i = n-2;i >= 0;i--){
            double kot = (360*(n-i)/n)*Math.PI/180;
            koreni = new Complex(Math.cos(kot),Math.sin(kot));
            System.out.print(" ");
            koreni.print();
          }
         System.out.println();
         break;
        }
       }   
	}
}

class Complex {
	double real;
	double imaginary; 
	
	public Complex(String Stevilo){ // razdeli vneseni string na imaginarni in realni del ter tako ustvari kompleksno stevilo
		boolean minus = false;
		if(Stevilo.charAt(0)=='-'){ // preveri ce se string zacne z znakom minus. tako vemo da je realni del negativen. minusa se lahko znebimo.
	     minus = true;
		 Stevilo = Stevilo.substring(1, Stevilo.length()-1);
		}else{	// ce je realno stevilo pozitivno, se znebimo zgolj i-ja na koncu 
	     Stevilo = Stevilo.substring(0, Stevilo.length()-1);	
		}
	     String[] pieces = Stevilo.split("\\+"); // vneseno stevilo sedaj poskusimo splitati po plusu. ce nam uspe je imaginarni del pozitiven
	     if(pieces.length==2){ // med operandoma je bil plus
	       if(minus){
	    	real = -Double.parseDouble(pieces[0]);  // pridobimo double vrednost stringa, pieces[0] predstavlja realni del 
	       }else{
	    	real = Double.parseDouble(pieces[0]);     
	       }
	        imaginary = Double.parseDouble(pieces[1]); // pridobimo double vrednost stringa, pieces[1] predstavlja realni del 
	     }else{ // med operandoma je bil minus, zator moramo stevilo se enkrat splitati po minusu
	      pieces = Stevilo.split("\\-");
	        if(minus){
		     real = -Double.parseDouble(pieces[0]);   
		    }else{
		     real = Double.parseDouble(pieces[0]);     
		    }
		     imaginary = - Double.parseDouble(pieces[1]);
	      }
    }
	
	public Complex(double realno,double imaginarno){
		real = (double)Math.round(realno*100000)/100000;
		imaginary = (double)Math.round(imaginarno*100000)/100000;
	}
	
	public Complex(){
		real = 0.0;
		imaginary = 0.0;		
	}
	
	public Complex add(Complex a){
		return new Complex((this.real+a.real), this.imaginary+a.imaginary);	
	}
	
	public  Complex subtract(Complex a){
    return new Complex((this.real-a.real), (this.imaginary-a.imaginary));	
	}
	
	public Complex multiply(Complex a){
    return new Complex((this.real*a.real)-(this.imaginary*a.imaginary),(this.real*a.imaginary)+(this.imaginary*a.real)); 
	}
	
	public Complex multiply(double a){
    return new Complex(this.real*a,this.imaginary*a);
	}
	
	public Complex divide(Complex a){
		Complex kolicnik = null;
		if(a.imaginary != 0.0){ // ce je imaginarni del prisoten
		Complex sideComplex = new Complex(a.real,-a.imaginary); // nasprotno stevilo delitelja
		Complex Converted1 = this.multiply(sideComplex);	
		Complex Converted2 = a.multiply(sideComplex); // tu ostane zgolj realni del
		kolicnik = new Complex(Converted1.real/Converted2.real,Converted1.imaginary/Converted2.real);
		}else{ // ce imaginarnega dela ni
		kolicnik = new Complex(this.real/a.real,this.imaginary/a.real);	
		}
	return kolicnik;	
	}
	
	public Complex n_root(int n){
	double kot = (360/n)*Math.PI/180;
	return new Complex(Math.cos(kot),Math.sin(kot));	
	}
	
	public void print(){
		if(this.real != 0.0){
		System.out.print(this.real);	
		}
		if(this.imaginary > 0.0 && this.real != 0.0){
		 System.out.print("+");	
		}
		if(this.imaginary != 0.0){
		 System.out.print(this.imaginary+"i");	
		}
	}
}