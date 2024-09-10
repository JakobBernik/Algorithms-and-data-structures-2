 /*
  *  a) iterativno : st. primerjav = O(n) ;
  *     rekurzivno : st. primerjav = O(n) ;                                     
  *  
  *  b) iterativno : st. primerjav  = 2*(n-1);
  *     rekurzivno : st. primerjav = (3*n-4)/2; ce n = dolzina tabele = potenca 2, potem je v vseh listih tocno 1 primerjava v notranjih vozliscih pa tocno 2. 
  *        
  *  c)dolžina_tabele: iter_min, iter_max, iter_comp, rek_min, rek_max, rek_comp, rek_izracun
  *
  *                16:        1,  7777777,        30,       1, 7777777,       22,          22
  *
  *                12:       12,  7777777,        22,       1, 7777777,       18,          16
  *
  *                37:        1,  7777777,        72,       1, 7777777,       56,        53.5
  *                 
  *                 5:        1,    97685,         8,       1,   97685,        6,         5.5
  *                
  *                 8:        1,  1231224,        14,       1, 1231224,       10,          10
  *                
  *               111:        1,  7777777,       220,       1, 7777777,      173,       164.5
  *               
  *                64:        1,  7777777,       126,       1, 7777777,       94,          94
  *
  *    rek_comp in rek_izracun se ujemata, ko je dolzina tabele potenca 2. manj primerjav naredi rekurzivni algoritem
  *  
  */
public class Izziv4 {
    
	public static int[] iterMinMax(int[] table){
	int[] result = new int[3]; // result[0] = min; result[1] = max; result[2] = st. primerjav;
	for(int i = 0;i< table.length;i++){
	   if(i == 0){
		 result[0] = table[i]; 
		 result[1] = table[i];
	   }else{
		   
		 if(table[i]<result[0]){
		 result[0] = table[i];
		 }
		 if(table[i]> result[1]){
		 result[1] = table[i];	
		 }
		 result[2] +=2;
	   }
	}
	return result;	
	}
	
	public static int[] DivideEtImpera(int[] table){
		if(table.length <= 2){
		 int[] result = new int[3]; // result[0] = min; result[1] = max; result[2] = st. primerjav;	
		  if(table.length < 2){ //case 0 primerjav
			result[0] = table[0];
			result[1] =	table[0];		
		  }else{	//case 1 primerjava
	     
		   if(table[0] < table[1]){ //primerjava v listu
			 result[0] = table[0];
		     result[1] = table[1];
		    
		   }else{
			 result[0] = table[1];
			 result[1] = table[0];
		   }	
		   result[2] = 1;
		  }
		  return result;
		}else{
		  int[] l = new int[table.length/2];
		  int[] r = new int[table.length-l.length];
		   System.arraycopy(table, 0, l, 0, l.length);
		   System.arraycopy(table, l.length, r, 0, r.length);	
		  int[] levi = DivideEtImpera(l);
		  int[] desni = DivideEtImpera(r);
		  int[] result = new int[3]; // result[0] = min; result[1] = max; result[2] = st. primerjav;
		  
		  if(levi[0] > desni[0]){ //1.primerjava
			 result[0] = desni[0]; 
		  }else{
			 result[0] = levi[0]; 
		  }
		  if( levi[1] > desni[1]){ // 2.primerjava
			result[1] = levi[1];
		  }else{
			result[1] = desni[1];  
		  }
		  result[2] = levi[2] + desni[2] + 2;
		  
		  return result; 
		}
	}
	
	public static void main(String[] args) {
	 /* int[] tab1 = {123,23,435,1231224,12,1,345,2342,12345,12312,8796,6554,546,7777777,111322,97685};
		int[] tab2 = {123,23,435,1231224,12,345,2342,12312,8796,546,7777777,97685};
		int[] tab3 = {123,23,435,1231224,12,1,345,2342,12345,12312,8796,6554,546,7777777,111322,97685,98,76,44,32,23,345,665,789,6533,234,789,9998,67544,66678,90000,8777,32344,8790,329,2348,4578};
		int[] tab4 = {123,12,1,12312,97685};
		int[] tab5 = {123,23,435,1231224,12,1,345,2342};
		int[] tab6 = {123,23,435,1231224,12,1,345,2342,12345,12312,8796,6554,546,7777777,111322,97685,98,76,44,32,23,345,665,789,6533,234,789,9998,67544,66678,90000,8777,32344,8790,329,2348,4578,123,23,435,1231224,12,1,345,2342,12345,12312,8796,6554,546,7777777,111322,97685,98,76,44,32,23,345,665,789,6533,234,789,9998,67544,66678,90000,8777,32344,8790,329,2348,4578,123,23,435,1231224,12,1,345,2342,12345,12312,8796,6554,546,7777777,111322,97685,98,76,44,32,23,345,665,789,6533,234,789,9998,67544,66678,90000,8777,32344,8790,329,2348,4578};
		int[] tab7 = {123,23,435,1231224,12,1,345,2342,12345,12312,8796,6554,546,7777777,111322,97685,98,76,44,32,23,345,665,789,6533,234,789,9998,67544,66678,90000,8777,32344,8790,329,2348,4578,123,23,435,1231224,12,1,345,2342,12345,12312,8796,6554,546,7777777,111322,97685,98,76,44,32,23,345,665,789,6533,234,789};
		int[] res11 = iterMinMax(tab1);
		int[] res12 = DivideEtImpera(tab1);
		System.out.println("Min: "+ res11[0] +" Max: "+ res11[1] + " st. primerjav: "+ res11[2]);
		System.out.println("Min: "+ res12[0] +" Max: "+ res12[1] + " st. primerjav: "+ res12[2]);
		int[] res21 = iterMinMax(tab2);
		int[] res22 = DivideEtImpera(tab2);
		System.out.println("Min: "+ res21[0] +" Max: "+ res21[1] + " st. primerjav: "+ res21[2]);
		System.out.println("Min: "+ res22[0] +" Max: "+ res22[1] + " st. primerjav: "+ res22[2]);
		int[] res31 = iterMinMax(tab3);
		int[] res32 = DivideEtImpera(tab3);
		System.out.println("Min: "+ res31[0] +" Max: "+ res31[1] + " st. primerjav: "+ res31[2]);
		System.out.println("Min: "+ res32[0] +" Max: "+ res32[1] + " st. primerjav: "+ res32[2]);
		int[] res41 = iterMinMax(tab4);
		int[] res42 = DivideEtImpera(tab4);
		System.out.println("Min: "+ res41[0] +" Max: "+ res41[1] + " st. primerjav: "+ res41[2]);
		System.out.println("Min: "+ res42[0] +" Max: "+ res42[1] + " st. primerjav: "+ res42[2]);
		int[] res51 = iterMinMax(tab5);
		int[] res52 = DivideEtImpera(tab5);
		System.out.println("Min: "+ res51[0] +" Max: "+ res51[1] + " st. primerjav: "+ res51[2]);
		System.out.println("Min: "+ res52[0] +" Max: "+ res52[1] + " st. primerjav: "+ res52[2]);
		int[] res61 = iterMinMax(tab6);
		int[] res62 = DivideEtImpera(tab6);
		System.out.println("Min: "+ res61[0] +" Max: "+ res61[1] + " st. primerjav: "+ res61[2]);
		System.out.println("Min: "+ res62[0] +" Max: "+ res62[1] + " st. primerjav: "+ res62[2]);
		int[] res71 = iterMinMax(tab7);
		int[] res72 = DivideEtImpera(tab7);
		System.out.println("Min: "+ res71[0] +" Max: "+ res71[1] + " st. primerjav: "+ res71[2]);
		System.out.println("Min: "+ res72[0] +" Max: "+ res72[1] + " st. primerjav: "+ res72[2]);
        */
	}

}
