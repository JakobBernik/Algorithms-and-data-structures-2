import java.util.Scanner;

public class Naloga1 {
    
//////////////////////////////////////////////////////////////////BubbleSort (57)
	public static int[] BubbleS(int[] tab,String Mode,String Way){
		int velikost = tab.length;
		int Index = velikost-1;
		int i = Index-1;
		int StUrejenih = 0;
		int StPrimerjav = 0;
		int StPrirejanj = 0; 
		 if(Mode.equals("trace")){  
		  for(int j = 0;j<velikost;j++){
		     if(j==StUrejenih){
		     System.out.print("| ");	 
		     }
		     if(j==velikost-1){
			  System.out.print(tab[j]); 
			 }else{
			  System.out.print(tab[j]+" ");  
			 }
		  }
		  System.out.println();
		 }
		 if(Way.equals("up")){
			while(StUrejenih != velikost-1){
			 
			if(tab[i] > tab[Index]){
			int tmp = tab[i];
			tab[i] = tab[Index];
			tab[Index] = tmp;
			   if(Mode.equals("count")){
			    StPrirejanj += 3;
		       }
			}
			if(Mode.equals("count")){
			  StPrimerjav++;
		    }
			Index--;
			i--;
			if(i==StUrejenih-1){
			 Index = velikost-1;
			 i=Index-1;
			 StUrejenih++;
			   if(Mode.equals("trace")){ // izpis sledi
			      for(int j = 0;j<velikost;j++){
			    	 if(j==velikost-1){
			    	    System.out.print(tab[j]);
			    	   }else{
			    		System.out.print(tab[j]+" ");
			    	   }
					   if(j==StUrejenih-1){
					    System.out.print("| ");	 
					   } 
				  }
				 System.out.println();
			   }
			} 
		 }
		 }else{
			while(StUrejenih != velikost-1){
			 
			if(tab[i] < tab[Index]){
			int tmp = tab[i];
			tab[i] = tab[Index];
			tab[Index] = tmp;
			   if(Mode.equals("count")){
			    StPrirejanj += 3;
		       }
			}
			if(Mode.equals("count")){
			  StPrimerjav++;
		    }
			Index--;
			i--;
			if(i==StUrejenih-1){
			 Index = velikost-1;
			 i=Index-1;
			 StUrejenih++;
			   if(Mode.equals("trace")){ // izpis sledi
			      for(int j = 0;j<velikost;j++){
			    	 if(j==velikost-1){
			    	    System.out.print(tab[j]);
			    	   }else{
			    		System.out.print(tab[j]+" ");
			    	   }
					   if(j==StUrejenih-1){
					    System.out.print("| ");	 
					   } 
				  }
				 System.out.println();
			   }
			} 
		 } 
		 }
		 if(Mode.equals("count")){
		  System.out.println(StPrimerjav+" "+StPrirejanj);
		 }
     return tab;
	}
////////////////////////////////////////////////////////////////// SelectionSort (65)
	public static int[] SelectionS(int[] tab,String Mode,String Way){
		int Current = tab[0];
		int Index = 0;
		int i = 1;
		int StUrejenih = 0;
		int velikost = tab.length;
		int StPrimerjav = 0;
		int StPrirejanj = 0; 
		 if(Mode.equals("trace")){  
		  for(int j = 0;j<velikost;j++){
		     if(j==StUrejenih){
		     System.out.print("| ");	 
		     }
		     if(j==velikost-1){
			  System.out.print(tab[j]); 
			 }else{
			  System.out.print(tab[j]+" ");  
			 }
		  }
		  System.out.println();
		 }
		 while(StUrejenih != velikost-1){
			if((Current < tab[i] && Way.equals("down")) || (Current > tab[i] && Way.equals("up"))){ // glede na parameter Way se tabela uredi padajoce ali narascajoce
			 Current = tab[i];
			 Index = i;
			}
			if(Mode.equals("count")){// v vsakem ciklu se izvede ena primerjava(primerjava trenutnegaElementa v tabeli z trenutnim najmanjsim/najvecjim elementom iz neurejenega dela tabele)
				StPrimerjav++;
			}
			if(i == velikost-1){
			  int tmp = tab[StUrejenih];
			  tab[StUrejenih] = Current;
		      tab[Index] = tmp;
			  StUrejenih++;
			  
			    if(Mode.equals("trace")){ // izpis sledi
			      for(int j = 0;j<velikost;j++){
			    	   if(j==velikost-1){
			    		System.out.print(tab[j]);
			    	   }else{
			    		System.out.print(tab[j]+" ");
			    	   }
					   if(j==StUrejenih-1){
					    System.out.print("| ");	 
					   } 
				  }
				 System.out.println();
			    }else{ // posodobitev St.Prirejanj. vedno ko se izvede zamenjava elementov se ta stevec poveca za 3.
			     StPrirejanj +=3;
			    } 
			    if(StUrejenih==velikost -1){ // tu se ustavimo. Saj v primeru da je prvih n-1 elementov na svojem mestu, je tudi n-ti element na svojem mestu. funkcija se zakljuci
			     continue; 
			    } 
			  i= StUrejenih; // sledeci ukazi locijo urejeni del od neurejenega. i++ je potreben da v naslednjem izvajanju zanke elementa ne primerjamo s samim seboj
			  Current = tab[i];
			  Index = i; 
			  i++;
			}else{
			i++;	
			}
		 }
		 if(Mode.equals("count")){
		  System.out.println(StPrimerjav +" "+StPrirejanj);
		 }
	 return tab;
    }
////////////////////////////////////////////////////////////////// InsertionSort (69)	
	public static int[] InsertionS(int[] tab,String Mode,String Way){
        int i = 1;
        int k = 0;
        int ii = i;
 		int StUrejenih = 1;
		int velikost = tab.length;
		int StPrimerjav = 0;
		int StPrirejanj = 0; 
		 if(Mode.equals("trace")){  
		  for(int j = 0;j<velikost;j++){
		     if(j==StUrejenih){
		     System.out.print("| ");	 
		     }
		     if(j==velikost-1){
			  System.out.print(tab[j]); 
		     }else{
			  System.out.print(tab[j]+" ");  
		     }
		  }
		  System.out.println();
		 }
		 boolean ZakljuciKrog = false;
		 while(StUrejenih != velikost){ // ta zanka se izvede enkrat za vsak element
			while(true){ // ta zanka se izvede ob vsaki novi primerjavi trenutnega elementa z elementi v urejenem delu
				if((tab[k] < tab[ii] && Way.equals("down")) || (tab[k] > tab[ii] && Way.equals("up"))){ // glede na parameter Way se tabela uredi padajoce ali narascajoce
					int tmp = tab[k];
					tab[k]=tab[ii];
					tab[ii]=tmp;
					k--;
					if(k==-1){
					ZakljuciKrog = true;
					}
					ii--;
					if(Mode.equals("count")){// v vsakem ciklu se izvede ena primerjava(primerjava trenutnegaElementa v tabeli z trenutnim najmanjsim/najvecjim elementom iz neurejenega dela tabele)
				    StPrirejanj += 3;
			        }
				}else{
				ZakljuciKrog = true;	
				}
				if(Mode.equals("count")){// v vsakem ciklu se izvede ena primerjava(primerjava trenutnegaElementa v tabeli z trenutnim najmanjsim/najvecjim elementom iz neurejenega dela tabele)
				    StPrimerjav++;
			    }
				if(ZakljuciKrog){ // element je na svojem mestu, ce Mode=trace izpisemo sled, posodobimo stevce i,ii,k in StUrejenih
					if(Mode.equals("trace")){ // izpis sledi
					      for(int j = 0;j<velikost;j++){
					    	System.out.print(tab[j]+" ");
							   if(j==StUrejenih){
								   if(StUrejenih==velikost){
									   System.out.print("|");   
								   }else{
							    System.out.print("| ");	 
								   }
							   }
						  }
						 System.out.println();
					}
				break;	
				}	
		    }
			ZakljuciKrog = false;
			StUrejenih++;
			i++;
			ii = i;
			k = i-1;
		 }
		 if(Mode.equals("count")){ // izpis count
		  System.out.println(StPrimerjav +" "+StPrirejanj);
		 }
	 return tab;
	}
////////////////////////////////////////////////////////////////// HeapSort (147)
	public static int[] HeapS(int[] tab,String Mode,String Way){
		boolean zgrajenaKopica = false;
		int StPrimerjav = 0;
		int StPrirejanj = 0; 
		int Sinko = tab.length;
		int dolzina = tab.length;
		int StariSinko = Sinko;
		int Ocka = Sinko/2;
		  while(!zgrajenaKopica){ // zgradimo Max ali Min Heap glede na Way
			if((tab[Sinko-1] > tab[Ocka-1] && Way.equals("up")) || (tab[Sinko-1] < tab[Ocka-1] && Way.equals("down"))){ // ce moramo zamenjati oceta in sinka
			  int tmp = tab[Sinko-1];
			  tab[Sinko-1] = tab[Ocka-1];
			  tab[Ocka-1] = tmp;
			  if(Mode.equals("count")){
				 StPrirejanj += 3;  
			  }
			  Sinko = Ocka;
			  Ocka = Sinko/2;
			  if(Ocka == 0){
			   StariSinko--;
			   Sinko = StariSinko;
			   Ocka = Sinko/2;
			  }
			}else{ // v nasprotnem primeru se premaknemo na naslednji list od spodaj
			 StariSinko--;
			 Sinko = StariSinko;
			 Ocka = Sinko/2;
			}
			if(Mode.equals("count")){
				  StPrimerjav++;  
		    }
			
			if(StariSinko == 1){ // ko smo koncali primerjanje levega Sinka korenskega Ocka lahko koncamo 
			 zgrajenaKopica = true;	
			}
		  }
		  
		  if(Mode.equals("trace")){ // izpis zacetne kopice
			 int mem = 1;
			 int stElNaNivoju = 1;
			 int i=0;
			 while(i < dolzina){
			 	if(stElNaNivoju==0){
			 	System.out.print("| ");
			 	stElNaNivoju = mem*2;
			 	mem = mem*2;
			 	}
			 	if(i==dolzina-1){
			 	 System.out.print(tab[i]);
			 	 break;
			 	}else{
			 	 System.out.print(tab[i]+" ");
			     i++;
			     stElNaNivoju--;
			 	}
			 }
			 System.out.println();
		  }
		  int StUrejenih = 0;
		  while(StUrejenih != dolzina-1){
			  int tmp= tab[0];
			  int j = dolzina-1-StUrejenih;
			  tab[0] = tab[j];
			  tab[j] = tmp;
			   if(Mode.equals("count")){
				 StPrirejanj += 3;  
			   }
			   StUrejenih++;
			   int velikost = dolzina - StUrejenih;
			   if(velikost > 1){ // ce je v kopici en element smo koncali ter zgolj izpisemo kopico z zadnjim elementom,drugace uredimo kopico
		         boolean KopicaUrejena = false;
		         Ocka = 1;
		         int leviSinko = 2*Ocka;
		         int desniSinko = 2*Ocka+1;
		         if(desniSinko <= velikost){ // ce sta oba sinova legit ju primerjamo
		          if((tab[leviSinko-1] < tab[desniSinko-1] && Way.equals("up")) || (tab[leviSinko-1] > tab[desniSinko-1] && Way.equals("down"))){
		    	   Sinko = desniSinko;   
		          }else{
		    	   Sinko = leviSinko;   
		          }
		          if(Mode.equals("count")){
					  StPrimerjav++;  
			      }
		         }else if(leviSinko <= velikost){ // ce imamo na voljo zgolj levega sina avtomatsko postane Sinko
		          Sinko = leviSinko;	 
		         }else{ // v tem primeru smo koncali z urejanjem kopice, saj je v njej zgolj 1 element
		          KopicaUrejena = true;	 
		         }
		        while(!KopicaUrejena){
		    	 if((tab[Sinko-1] > tab[Ocka-1] && Way.equals("up")) || (tab[Sinko-1] < tab[Ocka-1] && Way.equals("down"))){ // v primeru da sinko ne ustreza
		    		int temp = tab[Sinko-1];
		    		tab[Sinko-1] = tab[Ocka-1];
		    		tab[Ocka-1] = temp;
		    		 if(Mode.equals("count")){
						 StPrirejanj += 3;  
					 }
		    		Ocka = Sinko;
		    		leviSinko = 2*Ocka;
		    		desniSinko = 2*Ocka+1;
		    		 if(desniSinko <= velikost){ // ce sta oba sinova legit ju primerjamo
				       if((tab[leviSinko-1] < tab[desniSinko-1] && Way.equals("up")) || (tab[leviSinko-1] > tab[desniSinko-1] && Way.equals("down"))){
				    	 Sinko = desniSinko;   
				       }else{
				    	 Sinko = leviSinko;   
				       }
				       if(Mode.equals("count")){
							  StPrimerjav++;  
					    }
				     }else if(leviSinko <= velikost){ // ce imamo na voljo zgolj levega sina avtomatsko postane Sinko
				       Sinko = leviSinko;	 
				     }else{ // v tem primeru smo koncali z urejanjem kopice, saj je v njej zgolj 1 element
				       KopicaUrejena = true;	 
				     }
		    	 }else{
		    	  KopicaUrejena = true; 
		    	 }
		    	 if(Mode.equals("count")){
				  StPrimerjav++;  
				 }
		        }
			 }
		    if(Mode.equals("trace")){ // izpis kopice
			  int mem = 1;
	       	  int stElNaNivoju = 1;
			  int i=0;
			   while(i < velikost){
				 if(stElNaNivoju==0){
				   System.out.print("| ");
				   stElNaNivoju = mem*2;
				   mem = mem*2;
			     }
				 if(i==dolzina-1){
				   System.out.print(tab[i]);
				   break;
				 }else{
				   System.out.print(tab[i]+" ");
				   i++;
				   stElNaNivoju--;
				 }
			   } 
			   System.out.println();
			}			    
		  }
		  if(Mode.equals("count")){
		   System.out.println(StPrimerjav +" "+StPrirejanj);
		  }
	 return tab;
	}
////////////////////////////////////////////////////////////////// QuickSort
	public static int StPrimerjavQuick = 0;
	public static int StPrirejanjQuick = 0;
	public static int[] QuickS(int[] tab,String Mode,String Way,int from,int to){
		if(from>=to){
		return tab;	
		}
		int pivot = tab[(from+to)/2];
		if(Mode.equals("count")){
		 	StPrirejanjQuick ++;
		}
		int i = from;
		int j = to;
		while(i <= j){
				while((tab[i] < pivot && Way.equals("up")) || (tab[i] > pivot && Way.equals("down"))){
					 i++;
					 if(Mode.equals("count")){
					  StPrimerjavQuick ++;
					 }
					}
					while((tab[j] > pivot && Way.equals("up")) || (tab[j] < pivot && Way.equals("down"))){
					 j--;
					 if(Mode.equals("count")){
					  StPrimerjavQuick ++;
					 }
					}
					if(i <= j){
					 int tmp = tab[i];
					 tab[i] = tab[j];
					 tab[j] = tmp;
					 if(Mode.equals("count")){
						StPrirejanjQuick += 3;
					 }
					 i++;
					 j--;
					}
					if(Mode.equals("count")){
					 StPrimerjavQuick +=2;
					}	
		    }
		QuickS(tab,Mode,Way,from,j);
		QuickS(tab,Mode,Way,i,to);
		return tab;
	}
////////////////////////////////////////////////////////////////// MergeSort (88)
	public static int StPrimerjavMerge = 0;
	public static int StPrirejanjMerge = 0;
	public static int[] MergeS(int[] tab,String Mode,String Way,int from,int to){ // from je inkluziven,to pa ne
		int velikost = to-from;
		if(velikost == 1){
		int[] OsnovnaTabela = new int[1];
		OsnovnaTabela[0] = tab[from];
		 if(Mode.equals("count")){
		  StPrirejanjMerge++;
		 }		
		 return OsnovnaTabela;
		}
		int meja = to-from;
		if(meja%2 == 0){ // ce je velikost arraya sodo stevilo
		 meja = meja/2;	
		}else{ // ce je liho, bo levi del za 1 vecji od desnega
		 meja = meja/2 + 1;	
		}
		 if(Mode.equals("trace")){
			  int i = from;
			   while(i < to){
				 if(i == meja+from){
				   System.out.print("| ");
			     }
				 if(i== velikost-1){
				   System.out.print(tab[i]);
				   break;
				 }else{
				   System.out.print(tab[i]+" ");
				   i++;
				 }
			   } 
			   System.out.println();
			}
			int[] Lpodtabela = MergeS(tab,Mode,Way,from,meja+from);
			int[] Dpodtabela = MergeS(tab,Mode,Way,meja+from,to);
			return Merge(Lpodtabela,Dpodtabela,Mode,Way);
		 
	}
	public static int[] Merge(int[] leva,int[] desna,String Mode,String Way){
		int velikost = leva.length + desna.length;
		int[] merged = new int[velikost];
		int i=0,l = 0,d = 0;
		 while(l < leva.length && d < desna.length){
			 if((leva[l] < desna[d] && Way.equals("up")) || (leva[l] > desna[d] && Way.equals("down"))){
			 merged[i]= leva[l];
			 i++;
			 l++;
			 }else{
			 merged[i]= desna[d];
			 i++;
			 d++;
			 }
			 if(Mode.equals("count")){
			  StPrimerjavMerge ++;
			  StPrirejanjMerge ++;
			 }
		 }
		 if(l== leva.length){
			 while(d < desna.length){
				merged[i]= desna[d];
				i++;
				d++;
				if(Mode.equals("count")){
				  StPrirejanjMerge ++;
				}
			 }
		 }else{
			while(l < leva.length){
				merged[i] = leva[l];
				i++;
				l++;
				if(Mode.equals("count")){
				  StPrirejanjMerge ++;
				}
			} 
		 }
		 if(Mode.equals("trace")){
			int j = 0;
			while(j < merged.length-1){
				System.out.print(merged[j]+" ");
				j++;
			}
			System.out.print(merged[j]);
			System.out.println();
		 }
		return merged;
	}
////////////////////////////////////////////////////////////////// CountingSort (32)
	public static void CountingS(int[] tab,String Mode,String Way){ 
		int velikost = tab.length;
		int[] Kumulativa = new int[256];
		int shrani = 0,prepisi = 0;
		 for(int i = 0;i < velikost;i++){
			Kumulativa[tab[i]]++;
		 }
		 for(int i = 1;i < 256;i++){
			 
		 Kumulativa[i]+=Kumulativa[i-1];
		 System.out.print(Kumulativa[i-1]+" "); // ker je Mode lahko samo trace ne potrebujemo pogoja, ter lahko kumulativo izpisujemo ko jo imamo izracunano
		 shrani = Kumulativa[i-1];
		 Kumulativa[i-1] = prepisi;
		 prepisi = shrani;
		  if(i==255){ // za izpis zadnjega elementa
	       System.out.print(Kumulativa[255]);
		  }
		 }
		 System.out.println();
		 int[] rezultat = new int[velikost];
		 for(int i = 0;i < velikost;i++){
			 rezultat[Kumulativa[tab[i]]] = tab[i];
			 Kumulativa[tab[i]]++;
		 }
         for(int i = 0;i < velikost;i++){
        	 if(i==velikost-1){
        	  System.out.print(rezultat[i]);	 
        	 }else{
        	  System.out.print(rezultat[i]+" "); 
			 }
		 }
	}
////////////////////////////////////////////////////////////////// RadixSort
	public static void RadixS(int[] tab,String Mode,String Way,int key){
	}
////////////////////////////////////////////////////////////////// Main
	public static void main(String[] args) {
	    int len = args.length;
	    String Mode = args[0];
	    String Algorithm = args[1];
	    String Direction = args[2];
	    int ArrSize = 0;
	    boolean VelikostPodana = false;
	    if(len > 3){
	    ArrSize = Integer.parseInt(args[3]);	
	    VelikostPodana = true;
	    }
	    int[] OurArray; // v ta array bomo brali (pri podani velikosti potrebujemo zgolj ta array)
	    if(VelikostPodana){
	     OurArray = new int[ArrSize];
	    }else{
	     OurArray = new int[32];
	    }
	    Scanner sc = new Scanner(System.in);
	    int i=0;
	    while(sc.hasNextInt()){
	    		OurArray[i]= sc.nextInt();
	    		 if(i == OurArray.length-1){
	    		  OurArray = ArrayCopy(OurArray);
	             }
	     i++;
	    }
	    sc.close();	
	    OurArray = ArrayTrim(OurArray,i);
	    switch(Algorithm){ // tu se odlocimo za zeleni algoritem glede na parameter Algorithm. Funkciji, ki bo izvedla algoritem poleg nase tabele posljemo kot parametra se kaj zelimo (Mode = {trace,count}), ter nacin urejanja (Direction = {up,down}). 
	    
	     case "bs":
	    	 OurArray = BubbleS(OurArray,Mode,Direction);
	    	   if(Mode.equals("count")){
				   OurArray = BubbleS(OurArray,Mode,Direction);
	    	       if(Direction.equals("up")){
	    	        OurArray = BubbleS(OurArray,Mode,"down");
	    	       }else{
	    	        OurArray = BubbleS(OurArray,Mode,"up");   
	    	       }   
	    	   }
	    	break;
	     case "ss":
	    	 OurArray = SelectionS(OurArray,Mode,Direction);
	    	   if(Mode.equals("count")){
		    	   OurArray = SelectionS(OurArray,Mode,Direction);
		    	   if(Direction.equals("up")){
		    	    OurArray = SelectionS(OurArray,Mode,"down");
		    	   }else{
		    	    OurArray = SelectionS(OurArray,Mode,"up");   
		    	   }
		       }
	    	break;
	     case "is":
	    	 OurArray = InsertionS(OurArray,Mode,Direction);
	    	   if(Mode.equals("count")){
		    	   OurArray = InsertionS(OurArray,Mode,Direction);
		    	   if(Direction.equals("up")){
		    	    OurArray = InsertionS(OurArray,Mode,"down");
		    	   }else{
		    	    OurArray = InsertionS(OurArray,Mode,"up");   
		    	   }
		       }
	    	break;

	     case "hs":
	    	 OurArray = HeapS(OurArray,Mode,Direction);
	    	   if(Mode.equals("count")){
		    	   OurArray = HeapS(OurArray,Mode,Direction);
		    	   if(Direction.equals("up")){
		    	    OurArray = HeapS(OurArray,Mode,"down");
		    	   }else{
		    	    OurArray = HeapS(OurArray,Mode,"up");   
		    	   }
		       }
	    	break;

	     case "qs":
	    	 OurArray = QuickS(OurArray,Mode,Direction,0,OurArray.length-1);
	    	   if(Mode.equals("count")){
	    		     System.out.println(StPrimerjavQuick+" "+StPrirejanjQuick);
		    		 StPrimerjavQuick = 0;
		    	     StPrirejanjQuick = 0;
		    	   OurArray = QuickS(OurArray,Mode,Direction,0,OurArray.length-1);
		    	     System.out.println(StPrimerjavQuick+" "+StPrirejanjQuick);
		    		 StPrimerjavQuick = 0;
		    	     StPrirejanjQuick = 0;
		    	   if(Direction.equals("up")){
		    	    OurArray = QuickS(OurArray,Mode,"down",0,OurArray.length-1);
		    	   }else{
		    	    OurArray = QuickS(OurArray,Mode,"up",0,OurArray.length-1);   
		    	   }
		    	     System.out.println(StPrimerjavQuick+" "+StPrirejanjQuick);
	    	   }
	    	break;

	     case "ms":
	    	 OurArray = MergeS(OurArray,Mode,Direction,0,OurArray.length);
	    	   if(Mode.equals("count")){
		    		 System.out.println(StPrimerjavMerge+" "+StPrirejanjMerge);
		    		 StPrimerjavMerge = 0;
		    	     StPrirejanjMerge = 0;
		    	   OurArray = MergeS(OurArray,Mode,Direction,0,OurArray.length);
		    	   System.out.println(StPrimerjavMerge+" "+StPrirejanjMerge);
			    	 StPrimerjavMerge = 0;
			    	 StPrirejanjMerge = 0;
		    	   if(Direction.equals("up")){
		    	    OurArray = MergeS(OurArray,Mode,"down",0,OurArray.length);
		    	   }else{
		    	    OurArray = MergeS(OurArray,Mode,"up",0,OurArray.length);   
		    	   }
		    	   System.out.println(StPrimerjavMerge+" "+StPrirejanjMerge);
		       }
	    	break;

	     case "cs":
	    	 CountingS(OurArray,Mode,Direction);
	    	break;

	     case "rs":
	    	 RadixS(OurArray,Mode,Direction,0);
	    	break;
	    }   
	}
//////////////////////////////////////////////// funkciji za gradnjo Arraya	
	public static int[] ArrayCopy(int[] Arr){
		int dolzina = Arr.length;
		int[] newArr = new int[dolzina*2];
		for(int i = 0;i < dolzina;i++){
		 newArr[i] = Arr[i];
		}
	 return newArr;
	}
	
	public static int[] ArrayTrim(int[] Arr,int i){
	 int[] newArr = new int[i];
	  for(int j = 0;j < i;j++){
		newArr[j] = Arr[j];
	  }
     return newArr;	
	}
	public static void izpisi(int[] arr){
	 for(int i=0;i<arr.length;i++){
		 System.out.print(arr[i]+" ");
	 }	
	}
}
//////////////////////////////////////////////// konec