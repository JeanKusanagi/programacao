/*
 * Ex3.java
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
public class Ex3_03 {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);
		
		//Variáveis
		double num_inicial, num=0, soma=0, media=0, min, max;
		int num_elementos=0;
		
		//Introdução do primeiro número
		System.out.print("Introduza uma lista de numeros (terminada com com o primeiro numero introduzido): \n");
		num_inicial=ler.nextDouble();
		max=num_inicial;
		min=num_inicial;
		soma=soma+num_inicial;
		
		//Introdução dos restantes números
		while (num!=num_inicial)
		 {
			soma=soma+num;
			num_elementos++;
			num=ler.nextDouble();
			if (num<min) {min=num;}
			if (num>max) {max=num;}
		  }
		
		media=soma/num_elementos;
		
		//Se num_elementos=1, não faz sentido falar de valor máximo e mínimo, uma vez que só foi introduzido 1 número.
		if (num_elementos==1) {System.out.printf("\nFoi introduzido %d numero:\n- Valor maximo=valor minimo= %f\n- Media= %f\n\n", num_elementos, max, media);}
		else if (num_elementos>1) {System.out.printf("\nForam introduzidos %d numeros:\n- Valor maximo= %f\n- Valor minimo= %f\n- Media= %f\n\n", num_elementos, max, min, media);}
		
	}
}

