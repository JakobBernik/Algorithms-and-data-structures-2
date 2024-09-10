import java.util.Scanner;

public class izziv3 {
	
	public void mergeSort(int[] tab1,int[] tab2) {
		int[] urejenaTabela = new int[tab1.length+tab2.length];
		int IDX3 = 0;
		int idx1 = 0;
		int idx2 = 0;
		int mode = 0;
		while(IDX3 < urejenaTabela.length) {
			if(mode == 0) {
				if(tab1[idx1] <= tab2[idx2]) {
					urejenaTabela[IDX3] = tab1[idx1];
					IDX3++;
					idx1++;
					System.out.print("a");
					if(idx1 == tab1.length) {
						mode = 2;
					}
				}
				else {
					urejenaTabela[IDX3]= tab2[idx2];
					IDX3++;
					idx2++;
					System.out.print("b");
					if(idx2 == tab2.length) {
						mode = 1;
					}
				}
			}
			else if(mode == 1) {
				while(idx1 <tab1.length) {
					urejenaTabela[IDX3]=tab1[idx1];
					idx1++;
					IDX3++;
					System.out.print("a");
				} 
			}
			else {
				while(idx2 <tab2.length) {
					urejenaTabela[IDX3]=tab2[idx2];
					idx2++;
					IDX3++;
					System.out.print("b");
				} 
				
			}
		}
		System.out.println();
		for(int i=0;i<urejenaTabela.length;i++) {
			if(i<IDX3-1) {
			System.out.print(urejenaTabela[i]+" ");
			}else{
				System.out.print(urejenaTabela[i]);
			}
		}
	}
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		izziv3 izziv3 = new izziv3();
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int[] tabelaA = new int[n];
		int[] tabelaB = new int[m];
		for(int i=0;i<n;i++){
			tabelaA[i]=scanner.nextInt();
		}
		for(int i=0;i<m;i++){
			tabelaB[i]=scanner.nextInt();
		}
		scanner.close();
		izziv3.mergeSort(tabelaA,tabelaB);
	}

}
