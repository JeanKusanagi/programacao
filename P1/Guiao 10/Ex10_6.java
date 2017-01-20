/*
 * Ex10_6.java
 * 
 * Copyright 2016 Pedro <Pedro@UA>
 * MIECT - DETI UA
 * 
 * Solução mais simples para o Ex 10.2
 */

import java.util.Scanner;
public class Ex10_6 {
	
	public static void main(String[] args) {
		boolean[] seq=lerChave();
		mostraChave(seq);
	}
	
	//Função para ler números entre um dado intervalo (alterada)
	public static int getIntRange (String message, int lim1, int lim2, boolean[] chave)
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
		
		while ((n>lim_superior) || (n<lim_inferior) || chave[n]) { //Se o número lido não pertencer ao intervalo ou já tiver sido lido
			System.out.printf("\nTem de introduzir um número não repetido e pertencente ao intervalo [%d, %d]\n --> ", lim_inferior, lim_superior);
			n=ler.nextInt();
		}
		
		return n;
	}
	
	//Função para ler a chave (6 elementos)
	public static boolean[] lerChave () {
		boolean[] chave = new boolean [50];
		
		for (int i=1; i<=6; i++) {
			String message="Elemento "+i+" da chave = ";		//UI
			int valor=getIntRange(message, 1, 49, chave);		//Obtém o valor da chave
			chave[valor]=true;									//A posição correspondente a esse valor fica true
		}

		return chave;
	}
	
	//Função que escreve a aposta
	public static void mostraChave (boolean[] chave) {
		//UI
		System.out.printf("\n    Aposta de totoloto");

		//Imprime a aposta
		for (int i=1; i<=49; i++) {
			//Muda de linha quando a posição na linha é 7 ou múltiplo
			if ((((i-1) % 7)==0)) {
				System.out.printf("\n");
			} 		
			
			if (chave[i]) System.out.print(" X  ");	//Desenha um X quando i pertence à chave
			else System.out.printf ("%2d  ",i);		//Se não pertencer, imprime o número
			
		}
	}
	
}
