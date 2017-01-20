/*
 * Ex11.java
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
public class Ex2_11 {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);
		
		double a,b,c, delta, raiz1, raiz2, imag1, imag2; 
		
		//Leitura dos coeficientes
		System.out.printf("Introduza os coeficientes A, B e C da equacao do segundo grau na forma canonica: \n");
		a=ler.nextDouble();
		b=ler.nextDouble();
		c=ler.nextDouble();
		
		delta=(b*b)-(4*a*c);
		
		if (a==0) 
		 {System.out.print("\nNao se trata de uma equaçao do segundo grau. ");}
		else if (delta >=0)
		 {raiz1=(-b+Math.sqrt(delta))/(2*a);
          raiz2=(-b-Math.sqrt(delta))/(2*a);
          System.out.printf("\nRaizes reais do polinomio dado:\nx=%f ou x=%f\n", raiz1, raiz2);}
        else
         {raiz1=(-b)/2*a;
          raiz2=(-b)/2*a;
          imag1=Math.sqrt(-delta)/2*a;
          imag2=-Math.sqrt(-delta)/2*a;
          System.out.printf("\nRaizes imaginarias do polinomio dado:\n%f i%f\n%f i%f\n", raiz1, imag1, raiz2, imag2);}
		
}
}


