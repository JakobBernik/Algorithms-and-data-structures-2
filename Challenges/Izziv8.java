import java.util.Scanner;

public class Izziv8 {

	public static void main(String[] args) {
    
	 Scanner sc = new Scanner(System.in);
	 int N = sc.nextInt();
	 int k = sc.nextInt();
	 jajca(N,k);
	 sc.close();
	}
	
	public static void jajca(int N,int k){		
		int[][] tabela = new int[N+1][k];
		String format = "%4d";
		int iskano = 0;
		System.out.print("     ");
		for(int i = 1; i < k+1; i++){
			System.out.format(format, i);	
		}
		System.out.format("\n\n");		
		for(int i = 0; i < N+1; i++){
			System.out.format(format+"|",i); 
			for(int j = 0; j < k; j++){
				if(i == 0){
					tabela[i][j] = 0;
				}else if(i == 1){
					tabela[i][j] = 1;
				}else{
					if(j == 0){
						tabela[i][j] = i;	
					}else{
						int down = 0;  
						int up = i-1;
					    int min = N;
					    int max = 0;
						for(int l = 0; l < i; l++){
							max = Math.max(tabela[down][j-1],tabela[up][j]);
							down++;
							up--;
							if(min > max){
								min = max;	 
							}
						}
						tabela[i][j] = 1 + min;
					    iskano = tabela[i][j];
					}
				}
					System.out.format(format,tabela[i][j]);	
			}
			System.out.println();
		}
	}
}