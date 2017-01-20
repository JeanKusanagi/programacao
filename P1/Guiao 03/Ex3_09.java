/*
 * Ex3_9.java
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
public class Ex3_09 {

	public static void main(String[] args) {
		Scanner ler=new Scanner(System.in);
		
		//Declaração e inicialização de variáveis
		double num;
		int count=0, count_pos=0, count_neg=0, count_range1=0, count_range2=0;
		
		//Leitura inicial de um numero real
		System.out.printf("Introduza uma lista de numeros reais terminada pelo valor 0: ");
		num=ler.nextDouble();
		
		//Leitura de uma lista de numeros 
		while (num!=0) 
		{
		count++;
		if (num>0) {count_pos++;}									//Se positivo
		if ((num>=100) && (num<=1000)) {count_range1++;}			//Se pertencente ao intervalo [100,1000]
		if ((num>=-1000) && (num<=-100)) {count_range2++;}			//Se pertencente ao intervalo [-1000,-100]
		num=ler.nextDouble();
		}
		
		count_neg=count-count_pos;									//#negativos=#total-#positivos
		
		//Apresentação dos resultados
		System.out.printf("Foram introduzidos:\n - %d numeros reais positivos;\n - %d numeros reais negativos;\n - %d numeros reais pertencentes ao intervalo [100, 1000];\n - %d numeros reais pertencentes ao intervalo [-1000, -100].\n", 
				count_pos, count_neg, count_range1, count_range2);
	
		}
}