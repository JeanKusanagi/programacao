/*
 * Ex3_10.java
 * 
 * Copyright 2016 Pedro <Pedro@UA>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 */
import java.util.Scanner;
public class Ex3_10 {

	public static void main(String[] args) {
		//Declaração e inicialização de variáveis
		int num=0, maior=0, menor=0;

		//Leitura inicial de um numero inteiro
		num=getIntPos("Introduza uma lista de numeros inteiros positivos terminada pelo dobro do numero imediatamente anterior: ");
		int i=num*2;
		maior=num;
		menor=num;
		
		//Leitura de uma lista de numeros
		num=getIntPos("");
		while (num!=i) 
		{
			i=num*2;
			if (num>maior) {maior=num;}
			if (num<menor) {menor=num;} 
			num=getIntPos("");
		}
	
		//Apresentação dos resultados
		System.out.printf("O numero %d foi responsavel pela paragem da leitura.\nO maior numero introduzido foi %d e o menor %d.", num, maior, menor);
	}
	
	public static int getIntPos (String message)
	{
		Scanner ler = new Scanner(System.in);
		System.out.printf("%s",message);
		int n=ler.nextInt();
		while (n<0) 
		{System.out.printf("Tem de introduzir um numero inteiro POSITIVO: ");
		n=ler.nextInt();}
		return n;
	}
}
