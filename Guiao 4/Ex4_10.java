/*
 * Ex4_10.java
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
public class Ex4_10 {

	public static void main(String[] args) {
		
		//Obtenção do número
		int num=getIntPos("Verificacao de numero perfeito\nIntroduza um numero inteiro positivo: ");
		
		//Obtenção da soma dos divisores próprios do número
		int soma_divisores=0;
		for (int i=1; i<num; i++) {
			if (num%i==0) soma_divisores+=i;
		}
		
		//Apresentação do resultado
		if (num==soma_divisores) {System.out.printf("O numero %d e um numero perfeito.", num);}
		else if (num!=soma_divisores) {System.out.printf("O numero %d nao e um numero perfeito.", num);}
		
	}

	public static int getIntPos (String message)
	{
		Scanner ler = new Scanner(System.in);
		System.out.printf("%s",message);
		int n=ler.nextInt();
		while (n<0) 
		{System.out.printf("\nTem de introduzir um numero inteiro POSITIVO: ");
		n=ler.nextInt();}
		return n;
	}
	
}
