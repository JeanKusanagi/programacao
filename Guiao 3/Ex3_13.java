
/*
 * Ex3_13.java
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
public class Ex3_13
{
	public static void main(String args[])
	{
		int num, contrario = 0;
		
		//Leitura do número
		num=getIntPos("Introduza o numero a inverter: ");

		//Inversão do número
		while (num!=0)
		{
			contrario=contrario*10;
			contrario= contrario+num%10;
			num=num/10;
		}
		
		//Apresentação do resultado
		System.out.printf("Inverso do numero introduzido: %d", contrario);
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