import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Izziv7 {
      public static int stVozlisc;
      
	public static void main(String[] args) {
	
	 stVozlisc = Integer.parseInt(args[0]);
	 Network omrezje = new Network(stVozlisc);
	 Scanner sc = new Scanner(System.in);	
     while(sc.hasNextInt()){
      int from = sc.nextInt();
      int to = sc.nextInt();
      int cap = sc.nextInt();
      omrezje.addConnection(from, to, cap);
     }
     boolean urejeno = false;
      while(!urejeno){ // ce uspemo priti od izvora do ponora se izvede naslednja iteracija,drugace se algoritem ustavi
       urejeno = Ford_Fulkerson(omrezje);
       omrezje.resetMarks();
      }
	}

	public static boolean Ford_Fulkerson(Network omrezje){ //izvede sprehod od izvora do ponora po danem omrezju
	  omrezje.nodes[0].marked = true; //oznacimo izvor
	  int id = 0;
	  Node trenutni = omrezje.nodes[0]; 
	  ArrayList<Node> oznaceni= new ArrayList<Node>(); // hrani listo oznacenih nodeov, ki se niso obiskani
	  boolean koncano = oznaci(omrezje,id,oznaceni); // oznacimo vsa vozlisca v povezavi z izvorom
	  int index = poisciNaslednjega(oznaceni);
	  trenutni = oznaceni.get(index);
	  oznaceni.remove(index);
	  while(true){ // dokler ne oznacimo ponora
		 koncano=oznaci(omrezje,trenutni.id,oznaceni);
		 if(koncano || oznaceni.isEmpty()){ // ce je koncano, potem smo prisli do ponora. ce pa smo porabili vsa oznacena vozlisca smo zakljucili z celotnim algoritmom
		 break;	 
		 }
		 index = poisciNaslednjega(oznaceni); // poiscemo naslednjega iz katerega bomo oznacevali naprej
		 trenutni = oznaceni.get(index);
		 oznaceni.remove(index); // odstranimo ga iz seznama oznacenih, a se ne obiskanih 
	  }
	  if (koncano){
		izpisi(omrezje);
	  return false;  
	  }else{
	  return true;
	  }
	} 
    
	public static int poisciNaslednjega(ArrayList<Node> oznaceni){ // poisce naslednji oznacen se neobiskan node
		int index=0;
		int min=stVozlisc; 
		for(int i = 0 ; i< oznaceni.size();i++){
		   Node N = oznaceni.get(i);
			if(N.id < min){
			  min = N.id;
			  index=i;
		   }
		}
	return index;	
	}
	
	public static boolean oznaci(Network omrezje,int id,ArrayList<Node> oznaceni){ // v arrayList oznaceni doda id-je trenutno oznacenih in se ne obiskanih nodeov,ter le-te oznaci
	  for( Iterator<Edge> iterator = omrezje.nodes[id].outEdges.iterator(); iterator.hasNext(); ){
		Edge E = iterator.next();
		if(!omrezje.nodes[E.endID].marked && E.currFlow < E.capacity){
		 omrezje.nodes[E.endID].marked=true;
		 omrezje.nodes[E.endID].augmEdge = E;
	     if(omrezje.nodes[id].incFlow == -1){
	 	 omrezje.nodes[E.endID].incFlow = E.capacity - E.currFlow;
		 }else{
	      omrezje.nodes[E.endID].incFlow = Math.min(omrezje.nodes[id].incFlow,E.capacity - E.currFlow);
		 } 
	     if(omrezje.nodes[E.endID].id == stVozlisc-1){	
		  return true;
	     } 
		  oznaceni.add(omrezje.nodes[E.endID]);
		}
	  }
	  for( Iterator<Edge> iterator = omrezje.nodes[id].inEdges.iterator(); iterator.hasNext(); ){
		Edge E = iterator.next();
		if(!omrezje.nodes[E.startID].marked && E.currFlow > 0){
		 omrezje.nodes[E.startID].marked=true;
		 omrezje.nodes[E.startID].augmEdge = E;
		 omrezje.nodes[E.startID].incFlow = Math.min(E.capacity,E.currFlow);
	     oznaceni.add(omrezje.nodes[E.startID]);
	    }	
	  }
	return false;
	}
	
	public static void izpisi(Network omrezje){
		int i = stVozlisc-1;
		Node trenutni = omrezje.nodes[i];
		int Flow = trenutni.incFlow;
		System.out.print(Flow+": ");
		while(true){
			System.out.print(i);
		 	if(i != 0){
			 if(trenutni.augmEdge.endID == i ){ // pozitivna povezava	 
			 trenutni.augmEdge.currFlow += Flow;
			 System.out.print("+ ");	
			 i = trenutni.augmEdge.startID;
			 }else{ // negativna povezava
			 trenutni.augmEdge.currFlow -= Flow;	 
			 System.out.print("- ");	
			 i = trenutni.augmEdge.endID;
			 }
			}else{
			break;
			}
	
		  trenutni = omrezje.nodes[i];
		}
		System.out.println();
	}
}

class Node{	
	int id;
	//marks for the algorithm
	//------------------------------------
	boolean marked = false;
	Edge augmEdge = null; //the edge over which we brought the flow increase
	int incFlow = -1; //-1 means a potentially infinite flow
	//------------------------------------
	ArrayList<Edge> inEdges;
	ArrayList<Edge> outEdges;
	
	public Node(int i) {
		id = i;
		inEdges = new ArrayList<Edge>();
		outEdges = new ArrayList<Edge>();
	}
}

class Edge{
	int startID; 
	int endID;
	int capacity; 
	int currFlow;
	
	public Edge(int fromNode, int toNode, int capacity2) {
		startID = fromNode;
		endID = toNode;
		capacity = capacity2;
		currFlow = 0;
	}
}

class Network{
	Node[] nodes;
	
	/**
	 * Create a new network with n nodes (0..n-1).
	 * @param n the size of the network.
	 */
	public Network(int n){
		nodes = new Node[n];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i]= new Node(i);
		}
	}
	/**
	 * Add a connection to the network, with all the corresponding in and out edges.
	 * @param fromNode
	 * @param toNode
	 * @param capacity
	 */
	public void addConnection(int fromNode, int toNode, int capacity){
		Edge e = new Edge(fromNode, toNode, capacity);
		nodes[fromNode].outEdges.add(e);
		nodes[toNode].inEdges.add(e);
	}

	/**
	 * Reset all the marks of the algorithm, before the start of a new iteration.
	 */
	public void resetMarks(){
		for (int i = 0; i < nodes.length; i++) {
			nodes[i].marked = false;
			nodes[i].augmEdge = null;
			nodes[i].incFlow = -1;
		}
	}
}