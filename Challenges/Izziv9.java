import java.util.Scanner;

public class Izziv9 {
	public static element zadnji;
	public static element prvi;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int stElementov = Integer.parseInt(args[0]);
        element[] elementi = new element[stElementov];
        for(int i = 0; i < stElementov; i++){
           elementi[i] = new element();
            elementi[i].prostornina = sc.nextInt(); 
        }
        for(int i = 0; i < stElementov; i++){
            elementi[i].prostornina = sc.nextInt();     
        }
        int nahrbtnik = sc.nextInt();
        Nahrbtnik(elementi,stElementov,nahrbtnik);
        sc.close();
    }

    public static void Nahrbtnik(element[] elementi,int stElementov,int nahrbtnik){
        element potencialni = new element(0,0);
        prvi = potencialni;
        zadnji = potencialni;
        System.out.println("0: (0, 0)");
        int stPotencialnih = 1;
        for(int i = 0; i < stElementov; i++){
            stPotencialnih = dodaj(potencialni,elementi[i],stPotencialnih);
            stPotencialnih = odstrani(potencialni,stPotencialnih,nahrbtnik);
            izpisi(potencialni,i);
        } 
    }

    public static int dodaj(element potencialni,element novi,int stPotencialnih){
        element trenutni = potencialni;
        int i = 0;
        while(i < stPotencialnih){
            element nov = new element(trenutni.prostornina + novi.prostornina, trenutni.cena + novi.cena);
            zadnji.naslednji = nov;
            nov.prejsnji = zadnji;
            zadnji = nov;
            trenutni = trenutni.naslednji;
            i++;
        }
        uredi(potencialni,stPotencialnih);
    return stPotencialnih*2;    
    }

    public static int odstrani(element potencialni,int stPotencialnih,int nahrbtnik){
    	element trenutni = potencialni;
    	for(int i = 0; i < stPotencialnih; i++){
    		element primerjalni = potencialni;
    		for(int j = 0; j < stPotencialnih; j++){
        		if((trenutni.cena <= primerjalni.cena && trenutni.prostornina > primerjalni.prostornina)||(trenutni.cena < primerjalni.cena && trenutni.prostornina == primerjalni.prostornina)||(trenutni.cena == primerjalni.cena && trenutni.prostornina == primerjalni.prostornina && i != j)||trenutni.prostornina > nahrbtnik){
        			if(trenutni == zadnji){
        				zadnji = trenutni.prejsnji;
        				trenutni.prejsnji.naslednji = null;
        			}else{
        				trenutni.prejsnji.naslednji = trenutni.naslednji;
        				trenutni.naslednji.prejsnji = trenutni.prejsnji;
        				stPotencialnih--;
        			}
        		}
        		
        	}
    	}
    return stPotencialnih;	
    }
    public static void uredi(element potencialni,int stPotencialnih){
    	element novi = potencialni;
    	novi.naslednji = null;
    	element trenutni = novi;
    	element premikajoci = potencialni.naslednji;
    	for(int i=0;i< stPotencialnih;i++){
    		while(true){
    			if(trenutni.prostornina < premikajoci.prostornina){
    				if(trenutni.naslednji != null){
    				 trenutni = trenutni.naslednji;
    				}else{
    				 trenutni.naslednji = premikajoci;
    				 premikajoci.prejsnji = trenutni;
    				 break;
    				}
    			}else if(trenutni.prostornina == premikajoci.prostornina){
    				if(trenutni.cena >= premikajoci.cena){
    					if(trenutni.naslednji != null){
    						trenutni = trenutni.naslednji;
    	    			}else{
    	    				trenutni.naslednji = premikajoci;
    	    				premikajoci.prejsnji = trenutni;
    	    				break;
    	    			}
    				}else{
    					 premikajoci.naslednji = trenutni;
     					premikajoci.prejsnji = trenutni.prejsnji;
     					trenutni.prejsnji.naslednji = premikajoci;
     					break;
    				}
    			}else{
    				    premikajoci.naslednji = trenutni;
    					premikajoci.prejsnji = trenutni.prejsnji;
    					trenutni.prejsnji.naslednji = premikajoci;
    					break;
    			}
    		trenutni = novi;
    		premikajoci = premikajoci.naslednji;
    		}
    		
    	}
    }
    
    public static void izpisi(element potencialni,int i){
        element trenutni = potencialni;
        if(i!=-1){
        System.out.print(i+": ");
        }
        while(trenutni != null){
            System.out.print("("+trenutni.prostornina+","+trenutni.cena+") ");
            trenutni = trenutni.naslednji;
        }
    }
}

class element{ 
    int prostornina;
    int cena;
    element naslednji;
    element prejsnji;
    public element(){}
    public element(int p,int c){
        prostornina = p;
        cena = c;
    }
} 