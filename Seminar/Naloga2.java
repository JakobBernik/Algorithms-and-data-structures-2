import java.util.Scanner;

public class Naloga2 {
	public static int stop;
	public static void main(String[] args) {
		if(args[0].equals("num")){
			num(args[1]);
		}else{
			if(args.length==3){
				stop = Integer.valueOf(args[2]);
			}else{
				stop = 1;	
			}
			mat(args[1]);
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////// num
	public static void num(String algoritem){
		Scanner sc = new Scanner(System.in);
		String num1 = sc.next();
		sc.nextLine();
	    String num2 = sc.next();
		sc.close();
		String reg ="^0*";
		num1 = num1.replaceFirst(reg, "");
		num2 = num2.replaceFirst(reg, "");
		if(num1.length()==0){
		num1 = "0";	
		}
		if(num2.length()==0){
		num2 = "0";	
		}
		char[] number1 = num1.toCharArray();
		char[] number2 = num2.toCharArray();		
		switch(algoritem){
			case "dv":
				dv(number1,number2);
			break;
			case "ka":
				ka(number1,number2);
			break;	
		    default:
		    	os(number1,number2);
		    break;	
		}
	}
//*************************************************************************************************** osnovnosolsko mnozenje(16)	
	public static void os(char[] num1,char[] num2){
		char[] p = arr(num1.length+num2.length);
		int position = num2.length-1;
		for(int i = 0; i < num2.length; i++){
			char[] produkt = zmnozi(num1,num2[i]);
			System.out.println(String.valueOf(produkt));
			p = sestej(p,produkt,position-i);
		}
		if(p[0] == '0'){
			p = String.valueOf(p).replaceFirst("^0*", "").toCharArray();	
		    if(p.length == 0){
		     p = new char[1];
		     p[0] = '0';
		    }
		}
		for(int i = 0; i < p.length; i++){
			System.out.print("-");	
		}
		System.out.println();
		System.out.println(String.valueOf(p));
	}
//*************************************************************************************************** deli in vladaj(61)
	public static char[] dv(char[] num1,char[] num2){
		String reg ="^0*";
		String n1 = String.valueOf(num1).replaceFirst(reg, "");
		String n2 = String.valueOf(num2).replaceFirst(reg, "");
		if(n1.length()==0){
		n1 = "0";	
		}
		if(n2.length()==0){
		n2 = "0";	
		}
		num1 = n1.toCharArray();
		num2 = n2.toCharArray();
		int n = Math.max(num1.length, num2.length);
		if(n%2 != 0){
			n++;	
		}
		System.out.println(String.valueOf(num1)+" "+String.valueOf(num2));
		if(Character.getNumericValue(num1[0]) == 0 || Character.getNumericValue(num2[0]) == 0){ //1.pogoj
			char[] produkt = new char[1];
			produkt[0] = '0';
			System.out.println('0');
		return produkt;	
		}
		if(num1.length == 1){ //2.1pogoj
			char[] produkt = zmnozi(num2,num1[0]);
			System.out.println(String.valueOf(produkt));
		return produkt; 	
		}else if(num2.length == 1){ //2.2pogoj
			char[] produkt =zmnozi(num1,num2[0]);
			System.out.println(String.valueOf(produkt));
		return produkt; 
		}else{
			int ix1 = num1.length-n/2;
			int ix2 = num2.length-n/2; 
			String a = String.valueOf(num1);
			String b = String.valueOf(num2);
			char[] a0;
			if(ix1 > 0){
				a0 = a.substring(ix1, num1.length).toCharArray();
			}else{
				a0 = num1;
			}
			char[] a1;
			if(ix1 <= 0){
				a1 = new char[1];
				a1[0] ='0';
			}else{
				a1 = a.substring(0, ix1).toCharArray();
			}
			char[] b0;
			if(ix2 > 0){
				b0 = b.substring(ix2, num2.length).toCharArray();
			}else{
				b0 = num2;
			}
			char[] b1;
			if(ix2 <= 0){
				b1 = new char[1];
				b1[0] ='0';
			}else{
				b1 = b.substring(0, ix2).toCharArray();
			}
			char[] a0b0 = dv(a0,b0);
			char[] a0b1 = dv(a0,b1);
			char[] a1b0 = dv(a1,b0);
			char[] a1b1 = dv(a1,b1);
			String shiftFull = zeros(n);
			String shiftHalf = zeros(n/2);
			char[] Big = (String.valueOf(a1b1)+shiftFull).toCharArray();
			char[] Medium = (String.valueOf(sestej(a0b1,a1b0,0))+shiftHalf).toCharArray();				
			char[] produkt = sestej(Big,sestej(Medium,a0b0,0),0);
			produkt = String.valueOf(produkt).replaceFirst("^0*", "").toCharArray();
			System.out.println(String.valueOf(produkt));
		return produkt;		
		}
	}
//*************************************************************************************************** karacubov algoritem(65)	
	public static char[] ka(char[] num1,char[] num2){
		String reg ="^0*";
		String n1 = String.valueOf(num1).replaceFirst(reg, "");
		String n2 = String.valueOf(num2).replaceFirst(reg, "");
		if(n1.length()==0){
		n1 = "0";	
		}
		if(n2.length()==0){
		n2 = "0";	
		}
		num1 = n1.toCharArray();
		num2 = n2.toCharArray();
		int n = Math.max(num1.length, num2.length);
		if(n%2 != 0){
			n++;	
		}
		System.out.println(String.valueOf(num1)+" "+String.valueOf(num2));
		if(Character.getNumericValue(num1[0]) == 0 || Character.getNumericValue(num2[0]) == 0){
			char[] produkt = new char[1];
			produkt[0] = '0';
			System.out.println("0");
		return produkt;	
		}
		if(num1.length == 1){
			char[] produkt = zmnozi(num2,num1[0]);
			System.out.println(String.valueOf(produkt));
		return produkt; 	
		}else if(num2.length == 1){
			char[] produkt =zmnozi(num1,num2[0]);
			System.out.println(String.valueOf(produkt));
		return produkt; 
		}else{
			int ix1 = num1.length-n/2;
			int ix2 = num2.length-n/2; 
			String a = String.valueOf(num1);
			String b = String.valueOf(num2);
			
			char[] a0 = a.substring(ix1, num1.length).toCharArray();
			char[] a1;
			if(ix1 == 0){
				a1 = new char[1];
				a1[0] ='0';
			}else{
				a1 = a.substring(0, ix1).toCharArray();
			}
			char[] b0 = b.substring(ix2, num2.length).toCharArray();
			char[] b1;
			if(ix2 == 0){
				b1 = new char[1];
				b1[0] ='0';
			}else{
				b1 = b.substring(0, ix2).toCharArray();
			}
			char[] a0b0 = ka(a0,b0);
			char[] a1b1 = ka(a1,b1);
			char[] a0a1b0b1 = ka(sestej(a0,a1,0),sestej(b0,b1,0));
			String shiftFull = zeros(n);
			String shiftHalf = zeros(n/2);
			char[] Big = (String.valueOf(a1b1)+shiftFull).toCharArray();
			char[] Medium = (String.valueOf(odstej(a0a1b0b1,String.valueOf(sestej(a0b0,a1b1,0)).replaceFirst("^0*", "").toCharArray(),0))+shiftHalf).toCharArray(); 
			char[] produkt = sestej(Big,sestej(Medium,a0b0,0),0);
			produkt = String.valueOf(produkt).replaceFirst("^0*", "").toCharArray();
			System.out.println(String.valueOf(produkt));
		return produkt;		
		}	
	}
//*************************************************************************************************** zmnozi(29) - zmnozi veliko stevilo num1 z stevko st	
	public static char[] zmnozi(char[] num1,char st){
		char[] produkt;
		int prenos = 0;
		int s = Character.getNumericValue(st);
		if(s == 0){
		produkt = new char[1];
		produkt[0] = '0';	
		}else if(s == 1){
			produkt = num1;	
		}else{
			produkt = new char[num1.length+1];
			for(int i = num1.length-1; i >= 0; i--){
				int p = Character.getNumericValue(num1[i])*s + prenos;
				if(p >= 10){
					prenos = p/10;
					produkt[i+1] = Character.forDigit(p%10, 10);
				}else{
					prenos = 0;
					produkt[i+1] = Character.forDigit(p, 10);
				}
			}
			if(prenos > 0){
				produkt[0] = Character.forDigit(prenos, 10);	
			}else{
				produkt[0] = '0';
				produkt = String.valueOf(produkt).substring(1, produkt.length).toCharArray();
			}
		}
	return produkt;	
	}
//*************************************************************************************************** sestej(18) - pristeje vecjemu stevilu manjse stevilo, pri cemer position prestavlja zacetni odmik manjsega od zacetka vecjega	
	public static char[] sestej(char[] pa,char[] pb,int position){
		char[] p;
		char[] pr;
		if(pa.length >= pb.length){
			p = ("0"+String.valueOf(pa)).toCharArray();
			pr = pb;
		}else{
			p = ("0"+String.valueOf(pb)).toCharArray();
			pr = pa;
		}
		int prenos = 0;
		int j = p.length-1-position;
		for(int i = pr.length-1 ; i >= 0 ; i--){
			int sum = Character.getNumericValue(p[j]) + Character.getNumericValue(pr[i]) + prenos;
			if(sum >= 10){
				prenos = 1;
				p[j] = Character.forDigit(sum%10, 10);
			}else{
				prenos = 0;	
				p[j] = Character.forDigit(sum,10); 
			}
			j--;
		}
			while(prenos != 0){
				int sum = prenos + Character.getNumericValue(p[j]);
				if(sum>=10){
					prenos = sum/10;
					p[j] = Character.forDigit(sum%10, 10);
				}else{
					prenos = 0;
					p[j] = Character.forDigit(sum, 10);
				}
				j--;
			}	
		return p;
	}
//*************************************************************************************************** odstej(18) - odsteje stevilo pr od stevila p	
		public static char[] odstej(char[] p,char[] pr,int position){
			int prenos = 0;
			int j = p.length-1-position;
			for(int i = pr.length-1 ; i >= 0 ; i--){
				int diff = Character.getNumericValue(p[j]) - (Character.getNumericValue(pr[i]) + prenos);
				if(diff < 0){
					prenos = 1;
					p[j] = Character.forDigit(10+diff, 10);
				}else{
					prenos = 0;	
					p[j] = Character.forDigit(diff,10); 
				}
				j--;
			}
			while(prenos != 0){
				int diff = Character.getNumericValue(p[j])-prenos;
					if(diff < 0){
					    prenos = 1;
					    p[j] = Character.forDigit(10+diff, 10);
					}else{
						prenos = 0;
						p[j] = Character.forDigit(diff,10);
					}
				j--;   
			}
		return p;	
		}	
//************************************************************************************************** arr(6) - napolni mesta zacetnega stevila pri os z 0	
	public static char[] arr(int l){
		char[] arr = new char[l];
		for(int i = 0;i < l;i++){
			arr[i]='0';	
		}
	return arr;
	}
//************************************************************************************************** zeros() - String n nicel
	public static String zeros(int n){
		String zeros = "";
		for(int i = 0; i < n; i++){
			zeros+="0";
		}
		return zeros;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////// mat	
	public static void mat(String algoritem){
		Scanner sc = new Scanner(System.in);
		int[][] mat1 = in(sc);
		int[][] mat2 = in(sc);
		sc.close();
		switch(algoritem){
			case "dv":
				Object[] oDV = popraviMat(mat1,mat2);
				rezultat(dv((int[][])oDV[0],(int[][])oDV[1]));
				break;
			case "st":
				Object[] oST = popraviMat(mat1,mat2);
				rezultat(st((int[][])oST[0],(int[][])oST[1]));
			break;	
			default:
				os(mat1,mat2);
			break;	
		}
	}
//************************************************************************************************** enostavno mnozenje(9)
	public static void os(int[][] mat1,int[][] mat2){
		int[][] mat = zmnozi(mat1,mat2);
		System.out.println("DIMS: "+mat1.length+"x"+mat2[0].length);
		for(int i = 0; i < mat.length; i++){
			for(int j = 0; j < mat[i].length; j++){
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
	}
//************************************************************************************************** deli in vladaj(24)
	public static int[][] dv(int[][] mat1,int[][] mat2){
		if(mat1.length <= stop){
		return zmnozi(mat1,mat2);	
		}else{
			Object[] o1 = razbijMat(mat1); // o1 = {a11,a12,a21,a22}
			Object[] o2 = razbijMat(mat2); // o2 = {b11,b12,b21,b22}
			int[][] a11b11 = dv((int[][])o1[0],(int[][])o2[0]);
			izpisi(a11b11);
			int[][] a12b21 = dv((int[][])o1[1],(int[][])o2[2]);
			izpisi(a12b21);
			int[][] a11b12 = dv((int[][])o1[0],(int[][])o2[1]);
			izpisi(a11b12);
			int[][] a12b22 = dv((int[][])o1[1],(int[][])o2[3]);
			izpisi(a12b22);
			int[][] a21b11 = dv((int[][])o1[2],(int[][])o2[0]);
			izpisi(a21b11);
			int[][] a22b21 = dv((int[][])o1[3],(int[][])o2[2]);
			izpisi(a22b21);
			int[][] a21b12 = dv((int[][])o1[2],(int[][])o2[1]);
			izpisi(a21b12);
			int[][] a22b22 = dv((int[][])o1[3],(int[][])o2[3]);
			izpisi(a22b22);
		return sestaviMat(sestej(a11b11,a12b21),sestej(a11b12,a12b22),sestej(a21b11,a22b21),sestej(a21b12,a22b22));
		}
	}
//************************************************************************************************** strassenov algoritem(22)
	public static int[][] st(int[][] mat1,int[][] mat2){
		if(mat1.length <= stop){
		return zmnozi(mat1,mat2);	
		}else{
			Object[] o1 = razbijMat(mat1); // o1 = {a11,a12,a21,a22}
			Object[] o2 = razbijMat(mat2); // o2 = {b11,b12,b21,b22}
			int[][] P1 = st((int[][])o1[0],odstej((int[][])o2[1],(int[][])o2[3]));
			izpisi(P1);
			int[][] P2 = st(sestej((int[][])o1[0],(int[][])o1[1]),(int[][])o2[3]);
			izpisi(P2);
			int[][] P3 = st(sestej((int[][])o1[2],(int[][])o1[3]),(int[][])o2[0]);
			izpisi(P3);
			int[][] P4 = st((int[][])o1[3],odstej((int[][])o2[2],(int[][])o2[0]));
			izpisi(P4);
			int[][] P5 = st(sestej((int[][])o1[0],(int[][])o1[3]),sestej((int[][])o2[0],(int[][])o2[3]));
			izpisi(P5);
			int[][] P6 = st(odstej((int[][])o1[1],(int[][])o1[3]),sestej((int[][])o2[2],(int[][])o2[3]));
			izpisi(P6);
			int[][] P7 = st(odstej((int[][])o1[0],(int[][])o1[2]),sestej((int[][])o2[0],(int[][])o2[1]));
			izpisi(P7);
		return sestaviMat(sestej(sestej(P4,P5),odstej(P6,P2)),sestej(P1,P2),sestej(P3,P4),odstej(sestej(P1,P5),sestej(P3,P7)));	
		}	
	}
//************************************************************************************************** in(10) - z vhoda prebere matriko	
	public static int[][] in(Scanner sc){
	  int rows = sc.nextInt();
	  int columns = sc.nextInt();
	  int[][] mat = new int[rows][columns];
	  for(int i = 0; i < rows; i++){
		  for(int j = 0;j < columns; j++){
			  mat[i][j] = sc.nextInt();
		  }
	  }
	  return mat;
	}
//************************************************************************************************** sestej(8) - sesteje mat1 in mat2
	public static int[][] sestej(int[][] mat1,int[][] mat2){
		int[][] mat = new int[mat1.length][mat1.length];
		for(int i = 0; i < mat.length; i++){
			for(int j = 0; j < mat[i].length; j++){
				mat[i][j] = mat1[i][j] + mat2[i][j];
			}
		}
	return mat;	
	}
//************************************************************************************************** odstej(8) - odsteje mat2 od mat1
	public static int[][] odstej(int[][] mat1,int[][] mat2){
		int[][] mat = new int[mat1.length][mat1.length];
		for(int i = 0; i < mat.length; i++){
			for(int j = 0; j < mat[i].length; j++){
				mat[i][j] = mat1[i][j] - mat2[i][j];
			}
		}
	return mat;	
	}	
//************************************************************************************************** zmnozi(8) - zmnozi mat1 in mat2
	public static int[][] zmnozi(int[][] mat1,int[][] mat2){
		int[][] mat = new int[mat1.length][mat2[0].length];
		for(int i = 0; i < mat.length;i++){
			for(int j = 0; j < mat[i].length;j++){
				mat[i][j] = izracunaj(mat1,mat2,i,j);
			}
		}
	return mat;	
	}
//************************************************************************************************** izracunaj(9) - izacuna vsoto produktov vrstice i mat1 z stolpcem j mat2
	public static int izracunaj(int[][] mat1,int[][] mat2,int i,int j){
		int dim=mat1[0].length;
		int l = 0;
		int sum=0;
		while(l < dim){
			sum+= mat1[i][l]*mat2[l][j];
			l++;		
		}
	return sum;
	}
//************************************************************************************************** popraviMat(27) - popravi vhodni matriki na najvecjo dimenzijo
	public static Object[] popraviMat(int[][] mat1,int[][] mat2){
		double dims = Math.max(Math.max(Math.log(mat1.length)/Math.log(2),Math.log(mat2.length)/Math.log(2)),Math.max(Math.log(mat1[0].length)/Math.log(2),Math.log(mat2[0].length)/Math.log(2)));
		Object[] o = new Object[2];	
			int dim =(int) Math.pow(2,Math.ceil(dims));
			if(dim == 1){
				dim = 2;	
			}
			if(mat1.length != dim || mat1[0].length != dim){
				int[][] newMat1 = new int[dim][dim];
				for(int i = 0; i < mat1.length; i++){
					for(int j = 0; j < mat1[i].length; j++){
						newMat1[i][j] = mat1[i][j];
					}
				}
				o[0] = newMat1;	
			}else{
				o[0] = mat1;
			}
			if(mat2.length != dim || mat2[0].length != dim){
				int[][] newMat2 = new int[dim][dim];
				for(int i = 0; i < mat2.length; i++){
					for(int j = 0; j < mat2[i].length; j++){
						newMat2[i][j] = mat2[i][j];
					}
				}
				o[1] = newMat2;	
			}else{
				o[1] = mat2;
			}	
	return o;
	}
//************************************************************************************************** razbijMat(25) -razbije mat na 4 enake dele
	public static Object[] razbijMat(int[][] mat){
		Object[] o = new Object[4];
		int size = mat.length/2;
		int[][] m1 = new int[size][size];
		int[][] m2 = new int[size][size];
		int[][] m3 = new int[size][size];
		int[][] m4 = new int[size][size];
		for(int i = 0 ; i < mat.length; i++){
			for(int j = 0; j < mat[i].length; j++){
				if(i< size && j < size){
					m1[i][j] = mat[i][j];
				}else if(i < size && j >= size){
					m2[i%size][j%size] = mat[i][j];
				}else if(i >= size && j < size){
					m3[i%size][j%size] = mat[i][j];
				}else{
					m4[i%size][j%size] = mat[i][j];
				}
			}
		}
		o[0] = m1;
		o[1] = m2;
		o[2] = m3;
		o[3] = m4;
	return o;	
	}
//************************************************************************************************** sestaviMat(17) sestavi mat iz 4 enakih delov	
	public static int[][] sestaviMat(int[][] m1,int[][] m2,int[][] m3,int[][] m4){
		int size = m1.length;
		int[][] mat = new int[size*2][size*2];
		for(int i = 0; i < mat.length; i++){
			for(int j = 0; j < mat[i].length; j++){
				if(i< size && j < size){
					mat[i][j] = m1[i][j];
				}else if(i < size && j >= size){
					mat[i][j] = m2[i%size][j%size];
				}else if(i >= size && j < size){
					mat[i][j] = m3[i%size][j%size];
				}else{
					mat[i][j] = m4[i%size][j%size];
				}
			}
		}
		return mat;
	}
//************************************************************************************************** izpisi(8) - izpise vsoto elementov matrike
	public static void izpisi(int[][] mat){
		int sum=0;
		for(int i = 0; i < mat.length; i++){
			for(int j = 0; j < mat[i].length; j++){
				sum+= mat[i][j];
			}
		}
		System.out.println(sum);
	}
//************************************************************************************************** rezultat(8)
	public static void rezultat(int[][] mat){
		System.out.println("DIMS: "+mat.length+"x"+mat.length);
		for(int i = 0; i < mat.length; i++){
			for(int j = 0; j < mat[i].length; j++){
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
	}	
}