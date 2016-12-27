/*
 * Ex10_2.java
 * 
 * Copyright 2016 Pedro <Pedro@UA>
 * MIECT - DETI UA
 */

import java.util.Scanner;
public class Ex10_2 {
	
	public static void main(String[] args) {
		int[] seq=lerChave();
		mostraChave(seq);
	}
	
	//Função para ler números entre um dado intervalo (alterada)
	public static int getIntRange (String message, int lim1, int lim2, int[] chave)
	{
		Scanner ler = new Scanner(System.in);
		int lim_superior=0, lim_inferior=0, n=0;
		
		if (lim1>lim2) {
			lim_superior=lim1; 
			lim_inferior=lim2;
		}
		else if (lim1<lim2) {
			lim_superior=lim2; 
			lim_inferior=lim1;
		}
		
		System.out.printf("%s", message);
		n=ler.nextInt();
		
		while ((n>lim_superior) || (n<lim_inferior) || pertenceChave(chave,n)) {		//Se o número lido não pertencer ao intervalo ou já tiver sido lido
			System.out.printf("\nTem de introduzir um número não repetido e pertencente ao intervalo [%d, %d]\n --> ", lim_inferior, lim_superior);
			n=ler.nextInt();
		}
		
		return n;
	}
	
	//Função para ler a chave (6 elementos)
	public static int[] lerChave () {
		int[] chave = new int[6];
		
		for (int i=1; i<=chave.length; i++) {
			String message="Elemento "+i+" da chave = ";		//UI
			chave[i-1]=getIntRange(message, 1, 49, chave);
		}

		return chave;
	}

	//Função de pesquisa que indique se um número pertence à chave já introduzida.  
	public static boolean pertenceChave (int[] array, int num) {
		
		boolean pertence=false;
		int n=0;
		
		do {
			if(array[n++] == num) {
				pertence=true;
			}
		} while(n <array.length);
		
		return pertence;
	} 
	
	//Função que escreve a aposta
	public static void mostraChave (int[] chave) {
		//UI
		System.out.printf("\n    Aposta de totoloto");

		//Imprime a aposta
		for (int i=1; i<=49; i++) {
			//Muda de linha quando a posição na linha é 7 ou múltiplo
			if ((((i-1) % 7)==0)) {
				System.out.printf("\n");
			} 		
			
			boolean drawX=pertenceChave(chave,i);   //OK
			if (drawX) System.out.print(" X  ");	//Desenha um X quando i pertence à chave
			else System.out.printf ("%2d  ",i);		//Se não pertencer, imprime o número
			
			//Debug
			//if (drawX) System.out.printf("%d pertence à chave", i);
			//else System.out.print("%d);
		}
	}
	
}
