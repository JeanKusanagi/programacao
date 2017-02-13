/*
 * Ex01_2.java
 * 
 * Pedro Teixeira <pedro.teix@ua.pt> 
 * N. Mec 84715
 * MIECT - DETI UA
 */

import java.util.Scanner;

public class Ex01_2 {

	public static void main(String[] args) {
		//UI Inicial
		System.out.printf("Cálculo da nota de Programação 2\n----------------------------\n");
		
		//Ler notas
		String message="Introduza a nota do ";
		int[] notas = new int[4];
		notas[0]=getIntRange(message+"API1: ", 0, 20);
		notas[1]=getIntRange(message+"API2: ", 0, 20);
		notas[2]=getIntRange(message+"ATPI: ", 0, 20);
		notas[3]=getIntRange(message+"APF: ", 0, 20);
		
		//Cálculo da média
		double media=0.2*notas[0]+0.2*notas[1]+0.2*notas[2]+0.4*notas[3];
		
		//Aprovado?
		String state;
		if (media>=9.5) state="Aprovado";
		else state="REPROVADO";
		
		//Apresentar nota final + aprovação ou não
		System.out.printf("Nota Global: %.0f valores | %s", media, state);

	}
	
	//Ler nota com validação
	public static int getIntRange (String message, int lim1, int lim2)
	{
		Scanner ler = new Scanner(System.in);
		int lim_superior=0, lim_inferior=0, n=0;
		
		if (lim1>lim2) {lim_superior=lim1; lim_inferior=lim2;}
		else if (lim1<lim2) {lim_superior=lim2; lim_inferior=lim1;}
		
		System.out.printf("%s", message);
		n=ler.nextInt();
		while ((n>lim_superior) || (n<lim_inferior))
		{System.out.printf("\nTem de introduzir um valor pertencente ao intervalo [%d, %d]: ", lim_inferior, lim_superior);
		n=ler.nextInt();
		}
		return n;
	}

}
