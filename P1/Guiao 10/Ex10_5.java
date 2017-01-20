/*
 * Ex10_5.java
 * 
 * Copyright 2016 Pedro <Pedro@UA>
 * MIECT - DETI UA
 * 
 */

import java.util.Scanner;
public class Ex10_5 {
	
	public static void main(String[] args) {
		long seq=lerChave();
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
	public static long lerChave () {
		//Inicializar array
		boolean[] chavePertence = new boolean [50];
		for (int k=0; k<chavePertence.length; k++)
			chavePertence[k]=false;
		
		//Inicializar chave
		long chave=(long) Math.pow(2, 51);
			
		for (int i=1; i<=6; i++) {
			String message="Elemento "+i+" da chave = ";		//UI
			int valor=getIntRange(message, 1, 49, chavePertence);		//Obtém o valor da chave
			chavePertence[valor]=true;									//A posição correspondente a esse valor fica true
			chave+=(Math.pow(2, valor-1));
		}

		return chave;
	}
	
	//Função que escreve a aposta
	public static void mostraChave (long chave) {
		//UI
		System.out.printf("\n    Aposta de totoloto");
		String chave1 = (Long.toBinaryString(chave));
		//Imprime a aposta
		for (int i=0; i<49; i++) {
			//Muda de linha quando a posição na linha é 7 ou múltiplo
			if (((i % 7)==0)) {
				System.out.printf("\n");
			} 		
			
			if (chave1.charAt(51-i)=='1') System.out.print(" X  ");	//Desenha um X quando i pertence à chave
			else System.out.printf ("%2d  ",i+1);		//Se não pertencer, imprime o número
			
		}
	}
	
}
