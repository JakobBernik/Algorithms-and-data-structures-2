import java.util.Scanner;

public class Naloga3 {
	public static int numberOfOutputLines;
	public static izpis start;
	public static izpis end;
	
	public static void izpisi(int limit){
		int diff = numberOfOutputLines -limit;
		izpis tmp = start;
		if(diff <= 0 || limit == -1){
			while(tmp != null){
				System.out.println(tmp.line);
				tmp = tmp.next;
			}
		}else{
			int count = 0;
			while(count < diff){
				tmp = tmp.next;
				count++;
			}
			while(tmp != null){
				System.out.println(tmp.line);
				tmp = tmp.next;
			}
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////// 2c	
	public static int stPobarvanih;
	
	public static void c2(tocka[] vozlisca,seznam working,int color,int c){ 
		if(start == null){
			 start = new izpis(c+" : 0");
			 end = start; 
			 numberOfOutputLines++;
		}else{
			end.next = new izpis(c+" :");
			end = end.next;
			numberOfOutputLines++;
			seznam tmp = working;
			while(tmp != null){
				 end.line += " "+tmp.element;
				 tmp = tmp.next;
			}
		}
		boolean napaka = false;
		seznam novWorking = null;
		seznam moving = null;
		while(working != null && !napaka){
			int trenutni = working.element;
			if(vozlisca[trenutni].barva == -1){
				vozlisca[trenutni].barva = color;
				stPobarvanih++;
				LstElement tmp = vozlisca[trenutni].povezave;
				while(tmp != null){
					if(vozlisca[tmp.element].barva == color){
						end.next = new izpis("NOK");
						end = end.next;
						numberOfOutputLines++;
						napaka = true;
					}else if(vozlisca[tmp.element].barva == -1){
						if(novWorking == null){
							novWorking = new seznam(tmp.element);
						}else{
							moving = novWorking;
							seznam nov = new seznam(tmp.element);
							boolean dodan = false;
							while(moving.next != null || moving == novWorking){
								if(moving.element > nov.element && moving == novWorking){
									nov.next = novWorking;
									novWorking = nov;
									dodan = true;
									break;
								}else if(moving.element == nov.element){
									dodan = true;
									break;
								}else if(moving.element < nov.element){
										if(moving.next != null){
											if(nov.element < moving.next.element){
												nov.next = moving.next;
												moving.next = nov;
												dodan = true;
												break;
											}
										}else{
											moving.next = nov;
											dodan = true;
											break;
										}	
								}
								moving = moving.next;
							}
							if(!dodan){
								if(moving.element < nov.element){
									moving.next = nov;
								}
							}
						}
					}
					tmp = tmp.next;
				}	
			}
			working = working.next;
		}
		if(!napaka && stPobarvanih != vozlisca.length){
			c2(vozlisca,novWorking,(color+1)%2,c+1);
		}else if(stPobarvanih == vozlisca.length && !napaka){
			end.next = new izpis("OK");
			end = end.next;
			numberOfOutputLines++;
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////////////gr
	
	public static void gr(tocka[] vozlisca){
		for(int i = 0;i < vozlisca.length; i++){
			LstElement link = vozlisca[i].povezave;
			int[] barve = new int[vozlisca.length];
			while(link != null){
				if(vozlisca[link.element].barva != -1){
					barve[vozlisca[link.element].barva] = 1;
				}	
				link = link.next;
			}
			vozlisca[i].barva = pobarvaj(barve);
			if(start == null){
				start = new izpis(i+": "+vozlisca[i].barva);
				end = start;
				numberOfOutputLines++;
			}else{
				end.next = new izpis(i+": "+vozlisca[i].barva);
				end = end.next;
				numberOfOutputLines++;
			}
		}
	}
	
	public static int pobarvaj(int[] barve){
		for(int i = 0; i < barve.length; i++){
			if(barve[i] == 0){
				return i;
			}
		}
		return -1;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////ex
	public static void ex(tocka[] vozlisca,int k){
		if(start == null){
			start = new izpis("k = "+k);
			end = start;
			numberOfOutputLines++;
		}else{
			end.next = new izpis("k = "+k);
			end = end.next;
			numberOfOutputLines++;
		}
		int pow =(int) Math.pow(k, vozlisca.length);
		boolean veljavno = false;
		for(int i= 0;i < pow;i++){
			String value = popraviValue(vozlisca.length,Integer.toString(i, k));
			end.next = new izpis("");
			end = end.next;
			numberOfOutputLines++;
			for(int l = 0; l < vozlisca.length; l++){
				vozlisca[l].barva = Character.digit(value.charAt(l), k);
				end.line += vozlisca[l].barva+" ";
			}
			veljavno = preveriVeljavnost(vozlisca);
			if(veljavno){
				end.line += "OK";
				break;
			}else{
				end.line += "NOK";
			}
		}
		if(!veljavno){
			ex(vozlisca,k+1);
		}
	}
	
	public static String popraviValue(int l, String base){ // dodajanje nicel na zacek stringa ce je prekratek
		int diff = l- base.length();
		String corrected = "";
		while(diff > 0){
			corrected += "0";
			diff --;
		}
		corrected += base;
		return corrected;
	}
	
	public static boolean preveriVeljavnost(tocka[] tocke){ // preveri ce med vozlisci ni konflikta zaradi barvanja
		for(int i = 0; i < tocke.length; i++){
			LstElement link = tocke[i].povezave;
			while(link != null){
				if(tocke[link.element].barva == tocke[i].barva && tocke[i].barva != -1){
					return false;
				}
				link = link.next;
			}
		}
	return true;	
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////bt
	public static void bt(tocka[] vozlisca,int k){ // glavna funkcija, klice inside dokler ta ne najde pravega barvanja
		if(start == null){
			start = new izpis("k = "+k);
			end = start;
			numberOfOutputLines++;
		}else{
			if(end.line == ""){	
				end.line += "k = "+k;
			}else{
				end.next = new izpis("k = "+k);
				end = end.next;
				numberOfOutputLines++;
			}	
		}
		end.next = new izpis("");
		end = end.next;
		numberOfOutputLines++;
		boolean finish = inside(vozlisca,0,0,k);
		if(!finish){
			vozlisca = reset(vozlisca);
			bt(vozlisca,k+1);
		}
	}
	
	public static boolean inside(tocka[] tocke,int index,int barva,int k){ // funkcija ki rekurzivno obdela posamezen k
		String old = end.line;
		String local = old;
		while(barva < k && barva <= index){
			tocke[index].barva = barva;
			if(preveriVeljavnost(tocke)){
				local += barva+" ";
				if(index < tocke.length -1){
					end.next = new izpis(local);
					numberOfOutputLines++;
				}
				local += "OK";
				if(index == tocke.length - 1){
					end.line = local;
					return true;	
				}else{
					
					end.line = local;
					end = end.next;
					boolean finish = inside(clone(tocke),index+1,0,k);
					if(finish){
						return true;	
					}else{
						if(index == 0){
							break;
						}else{
							local = old;
							barva++;
						}
						
					}
				}
			}else{
				if(barva < k-1){
					end.next = new izpis(local);
					numberOfOutputLines++;
				}else{
					end.next = new izpis("");
					numberOfOutputLines++;
				}
				local += barva+" ";
				local += "NOK";
				end.line = local;
				end = end.next;
				local = old;
				barva++;
			}
		}
		return false;
	}
	
	public static tocka[] reset(tocka[] tocke){ // barve vozlisc postavi na default
		for(int i = 0; i < tocke.length; i++){
			tocke[i].barva = -1;
		}
	return tocke;	
	}
	
	public static tocka[] clone(tocka[] tocke){
		tocka[] nov = new tocka[tocke.length];
		for(int i = 0; i < tocke.length; i++){
			nov[i] =copy(tocke[i]);
		}
	return nov;	
	}
	public static tocka copy(tocka t){
		tocka novaT = new tocka(t.index);
		novaT.barva = t.barva;
		LstElement tmp = t.povezave;
		while(tmp != null){
			novaT.add(tmp.element);
			tmp = tmp.next;
		}
	return novaT;	
	}
////////////////////////////////////////////////////////////////////////////////////////////////////dp
	
////////////////////////////////////////////////////////////////////////////////////////////////////main	
	public static void main(String[] args) {
		String ukaz = args[0];
		int limit = -1;
		if(args.length > 1){
			if(args[1].equals("-n")){
				limit = Integer.parseInt(args[2]);
			}
		}
		Scanner sc = new Scanner(System.in);
		int stVozlisc = sc.nextInt();
		int stPovezav = sc.nextInt();
		tocka[] tocke = new tocka[stVozlisc];
		for(int i= 0;i < stVozlisc;i++){
			tocke[i] = new tocka(i);
		}
		for(int i = 0;i < stPovezav;i++){
			povezava p = new povezava(sc.nextInt(),sc.nextInt());
			tocke[p.t1].add(p.t2);
			tocke[p.t2].add(p.t1);
		}
		sc.close();
		switch(ukaz){
		case "2c":
			seznam w = new seznam(0);
			c2(tocke,w,0,0);
			izpisi(limit);
			break;
		case "gr":
			gr(tocke);
			izpisi(limit);
			break;
		case "ex":
			ex(tocke,2);
			izpisi(limit);
			break;
		case "bt":
		    bt(tocke,2);
			izpisi(limit);
			break;
		case "dp":
			//dp(stVozlisc,povezave,limit);
			//izpisi(limit);
			break;
		
		}
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////// classes
class tocka{ // vozlisce s listo povezav
	int index;
	LstElement povezave;
	int barva = -1;
	public tocka(int i){
		index = i;
	}
	public void add(int p){ // dodajanje povezave
		if(povezave == null){
		povezave = new LstElement(p);
		}else{
		LstElement nov = new LstElement(p);
		LstElement tmp = povezave;
		while(tmp.next != null){
		 tmp = tmp.next;	
		}
		tmp.next = nov;
		}
	}
}

class povezava{ 
	int t1;
	int t2;
	public povezava(int f,int s){
		t1 = f;
		t2 = s;
	}
}
class LstElement{ // lista
	int element;
	LstElement next;
	public LstElement(int p){
		element = p;
	}
}

class seznam{ // seznam elementov, ki jih barvamo v danem trenutku
	int element;
	seznam next;
	public seznam(int i){
		element = i;
	} 
}

class izpis{ // struktura hrani celoten izpis, zaradi stikala -n
	String line;
	izpis next;
	izpis(String l){
		line = l;
	}
}