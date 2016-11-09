/*
 * Ex7.java
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
public class Ex2_07 {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);
		
		int num1, num2, num3;
		
		System.out.print("Introduza 3 numeros inteiros: ");
		num1=ler.nextInt();
		num2=ler.nextInt();
		num3=ler.nextInt();
		
		if ((num1<=num2) && (num1 <= num3))
		{if (num2 <= num3) {System.out.printf("Maior numero: %d\n", num3);}
         else {System.out.printf("Maior numero: %d\n", num2);}
		}
      
		else if ((num2 <= num1) && (num2 <= num3))
		{if (num1 <= num3) {System.out.printf("Maior numero: %d\n", num3);}
		 else {System.out.printf("Maior numero: %d\n", num1);}
		}
		else
		{
         if (num1 <= num2) {System.out.printf("Maior numero: %d\n", num2);}
         else {System.out.printf("Maior numero: %d\n", num1);}
		}
		
	}
}

