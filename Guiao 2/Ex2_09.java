/*
 * Ex9.java
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
 * 
 */

import java.util.Scanner;
public class Ex2_09 {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);
		
		double temp, temp_converted;
		int temp_type;
		
		System.out.printf("Conversor de Temperaturas \n");
		System.out.println("--------------------\n");
		System.out.printf("Introduza o valor da temperatura: \n");
		temp=ler.nextDouble();
		
		System.out.printf("\nIntroduza o tipo de temperatura lida: \n");
		System.out.println("--------------------");
		System.out.println("0: Celsius ");
		System.out.println("1: Fahrenheit ");
		System.out.println("--------------------");
		temp_type=ler.nextInt();
		
		switch (temp_type)
		{
			case 0: 
						temp_converted=1.8*temp+32;
						System.out.printf("\n%5.2f graus Celsius e equivalente a %5.2f graus Fahrenheit. \n", temp, temp_converted);
						break;
					
			case 1: 
						temp_converted=0.56*(temp-32);
						System.out.printf("\n%5.2f graus Fahrenheit e equivalente a %5.2f graus Celsius. \n", temp, temp_converted);
						break;
					
			default: 
						System.out.printf("\nTipo de temperatura invalido. \n");
						break;
					
		
	}
}
}

