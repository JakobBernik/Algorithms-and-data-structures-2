
import java.util.Scanner;

public class Izziv10{
	public static int[] dolzine;
	
    public static void BellmanFord(vertex[] graf,lista gradniki,int k){ // glavna funkcija 
    	if(k <= graf.length){
    		lista noviGradniki = null; // novi elementi za obdelavo v  naslednji izvedbi funkcije
    		lista tmpG = null;
    		int[] noveDolzine = new int[dolzine.length]; // spremembe v dolzinah
    		boolean sprememba = false; // prislo je do spremembe v dolzinah, potreben prepis
    		int[] indexi = new int[dolzine.length]; // mesta ki so se spremenila in jih je potrebno prepisati
    		lista tmp = gradniki;
    		while(tmp != null){
    			vertex current = graf[(int)tmp.element];
    			lista tmpE = current.inEdges;
    			while(tmpE != null){
    				if(dolzine[((EdgeE)tmpE.element).dest] == Integer.MAX_VALUE){
    					if(dolzine[((EdgeE)tmpE.element).src] < Integer.MAX_VALUE){
    						noveDolzine[((EdgeE)tmpE.element).dest] = dolzine[((EdgeE)tmpE.element).src] + ((EdgeE)tmpE.element).price;
    						indexi[((EdgeE)tmpE.element).dest] = 1;
    						if(!sprememba){
    							sprememba = true;
    						}
    					}
    				}else{
    					if(dolzine[((EdgeE)tmpE.element).src] < Integer.MAX_VALUE){
    						noveDolzine[((EdgeE)tmpE.element).dest] = Math.min(dolzine[((EdgeE)tmpE.element).dest], dolzine[((EdgeE)tmpE.element).src] + ((EdgeE)tmpE.element).price); 
    						indexi[((EdgeE)tmpE.element).dest] = 1;
    						if(!sprememba){
    							sprememba = true;
    						}
    					}
    				}
    				tmpE = tmpE.next;
    			}
    			tmpE = current.outEdges;
    			while(tmpE != null){ // vozlisca za naslednjo obdelavo
    				if(noviGradniki == null){
    					noviGradniki = new lista((int)((EdgeE)tmpE.element).dest);
    				}else{
    					tmpG = noviGradniki;
    					while(tmpG != null){
    						if((int)tmpG.element == ((EdgeE)tmpE.element).dest){
    							break;
    						}
    						if(tmpG.next == null){
    							tmpG.next = new lista((int)((EdgeE)tmpE.element).dest);
    							break;
    						}
    						tmpG = tmpG.next;
    					}
    				}
    				tmpE = tmpE.next;
    			}
    			tmp = tmp.next;
    		}
    		if(sprememba){
    			if(k < dolzine.length){
    			prepisi(noveDolzine,indexi);
    			}else{
    				boolean napaka = preglej(noveDolzine);
    				if(napaka){
    					System.out.println("Napaka.Graf vsebuje negativne cikle.");
    				}
    			}
    		}
    		if(k < dolzine.length){
    			izpis(k);
    			BellmanFord(graf,noviGradniki,k+1);
    		}
    	}
    }
    
    public static int[] start(int[] dolzine){ // nastavitev zacetnih dolzin na 0 oz 'Inf'
    	dolzine[0] = 0;
    	for(int i = 1;i < dolzine.length; i++ ){
    		dolzine[i] = Integer.MAX_VALUE;
    	}
    return dolzine;	
    }
    
    public static void prepisi(int[] src,int[] indexi){ // prepis dolzin iz src v dolzine na indexi
    	for(int i = 0; i < src.length;i++){
    		if(indexi[i] == 1){
    		dolzine[i] = src[i];
    		}
    	}	
    }
    
    public static boolean preglej(int[] noveDolzine){ // zadnji pregled za negativne cikle
    	for(int i = 0;i< dolzine.length;i++){
    		if(noveDolzine[i] != dolzine[i]){
    			return true;
    		}
    	}
    	return false;
    }
    
    public static void izpis(int k){ // izpis stanja dolzin
    	System.out.print("h"+k+": ");
    	for(int i = 0;i < dolzine.length;i++){
    		if(dolzine[i] == Integer.MAX_VALUE){
    			System.out.print("Inf ");
    		}else{
    			System.out.print(dolzine[i]+" ");
    		}			
    	}
    	System.out.println();
    }
    
    public static void main(String args[]){
        int stVozlisc = Integer.parseInt(args[0]);
        Scanner sc = new Scanner(System.in);
        vertex[] graf = new vertex[stVozlisc];
        for(int i = 0; i < stVozlisc; i++){
            graf[i] = new vertex(i);
        }
        while(sc.hasNextInt()){
            EdgeE e = new EdgeE(sc.nextInt(),sc.nextInt(),sc.nextInt());
            graf[e.src].add(e,0);
            graf[e.dest].add(e,1);
        }
        sc.close();
        dolzine = start(new int[stVozlisc]);
        lista inProgress = new lista(0);
        BellmanFord(graf,inProgress,0); 
    }  
}
class vertex{
    int index;
    lista inEdges;
    lista outEdges;
    public vertex(int i){
        index = i;
        
    }
    public void add(EdgeE p,int s){
    	if(s == 1){
    		if(inEdges == null){
    			inEdges = new lista(p);
    		}else{
    			lista tmp = inEdges;
    			while(tmp.next != null){
    				tmp = tmp.next;
    			}
    			tmp.next = new lista(p);
    		}
    	}else{
    		if(outEdges == null){
    			outEdges = new lista(p);
    		}else{
    			lista tmp = outEdges;
    			while(tmp.next != null){
    				tmp = tmp.next;
    			}
    			tmp.next = new lista(p);
    		}
    	}
    }
}
class EdgeE{
    int src;
    int dest;
    int price;
    
    public EdgeE(int s, int d, int p){
        price = p;
        src = s;
        dest = d;
    }
}

class lista{
	Object element;
	lista next;
	public lista(Object e){
		element = e;
	}
} 

