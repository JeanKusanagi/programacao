/*
 * Ex01_3.java
 * 
 * Pedro Teixeira <pedro.teix@ua.pt> 
 * N. Mec 84715
 * MIECT - DETI UA
 */

import java.util.Scanner;

public class Ex01_3 {

	public static void main(String[] args) {

		//Leitura número
		int n=getIntPos("Introduza um número inteiro positivo: ");

		//Verificar se o número n é primo
		boolean prime = false;

		if (n==1) prime=false;                 
		for(int i=2; i<n; i++) { 
			if(n % i == 0) prime=false; 
		} 
		
		if(prime) System.out.printf("O número %d é primo.", n); 
		else System.out.printf("O número %d não é primo.", n);

	}


	public static int getIntPos (String message)
	{
		Scanner ler = new Scanner(System.in);
		System.out.printf("%s",message);
		int n=ler.nextInt();
		while (n<0) {
			System.out.printf("\nTem de introduzir um numero inteiro POSITIVO: ");
			n=ler.nextInt();
		}
		return n;
	}
}
