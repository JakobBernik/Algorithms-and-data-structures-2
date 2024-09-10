import java.util.Scanner;

public class Izziv2 {
	int n;
	int[] stevila;
	int dolzKopice;
	
	public void preberiStevila(Scanner scan) {
		this.stevila = new int[n];
		for(int i = 0;i < n;i++) {
			this.stevila[i] = scan.nextInt();
		}
	}
	public void zgradiZacetnoKopico() {
		for(int i = ((this.n - 1) / 2);i >= 0;i--) {
			pogrezni(i);
		}
	}
	
	public void pogrezni(int oce) {
		int sin;
		if(oce <= ((dolzKopice - 2) / 2)) {
			sin = oce * 2 + 1;
			if((sin) <= (dolzKopice-2)) {
				if(stevila[sin] < stevila[sin+1]) {
					sin = sin + 1;
				}
			}
			if(stevila[oce] < stevila[sin]) {
				int tmp = stevila[oce];
				stevila[oce] = stevila[sin];
				stevila[sin] = tmp;
				pogrezni(sin);
			}
		}
	}
	
	public void izpisiKopico() {
		int j=1;
		int k=1;
		for(int i=0;i < dolzKopice;i++) {
			System.out.print(this.stevila[i]);
			if(i!=dolzKopice-1) {
				System.out.print(" ");
			}
			j--;
			if(j == 0 && i!= dolzKopice-1) {
				System.out.print("| ");
				j=(int)Math.pow(2,k);
				k++;
			}
		}
		System.out.println();
	}
	
	public void heapSort() {
		this.izpisiKopico();
		dolzKopice--;
		if(dolzKopice!=0) {
			int tmp= stevila[0];
			stevila[0] = stevila[dolzKopice];
			stevila[dolzKopice] = tmp;
			if(dolzKopice != 1) {
				pogrezni(0);	
			}
			heapSort();
		}
	}
	
	public static void main(String[] args) {
	
		Scanner scan = new Scanner(System.in);
		Izziv2 izziv2 = new Izziv2();
		izziv2.n = scan.nextInt();
		izziv2.dolzKopice = izziv2.n;
		izziv2.preberiStevila(scan);
		izziv2.zgradiZacetnoKopico();
		izziv2.heapSort();
	}

}
