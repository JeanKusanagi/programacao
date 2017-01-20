/*
 * Ex7_3.java
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
public class Ex7_3 {

	// Complete o programa
	static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// Cria um registo de estatisticas:
		Statistics xstat = new Statistics();
		
		//UI
		System.out.printf("Statistics Calculator\n--------------------------\n");
		System.out.printf("Por favor introduza numeros (letras ou simbolos termina): ");
		
		// Inicializa o registo (evita mínimo=0 quando todos os números lidos são >0 [e idêntico para o máximo]) 
		double x=sc.nextDouble();
		xstat.max=x;
		xstat.min=x;
		xstat.num++;
		xstat.sum=x;
		xstat.sum2=Math.pow(x, 2);
		
		// Enquanto houver um valor para ler:
		while (sc.hasNextDouble()) {
			// Lê um valor:
			x = sc.nextDouble();
			// Atualiza o registo de estatisticas:
			updateStats(xstat, x);
		}

		// Escreve resultados:
		System.out.printf("numero de valores = %d\n", xstat.num);
		System.out.printf("soma dos valores = %f\n", xstat.sum);
		System.out.printf("soma dos quadrados = %f\n", xstat.sum2);
		if (xstat.num > 0) {
			System.out.printf("min = %f\n", xstat.min);
			System.out.printf("max = %f\n", xstat.max);
			System.out.printf("média = %f\n", mean(xstat));
			System.out.printf("variancia = %f\n", variance(xstat));
		} else {
			System.out.printf("Lista vazia!");
		}
	}

	// Definir funções updateStats, mean e variance!
	public static void updateStats (Statistics tmp, double x) {	
		//Actualizar minimo
		if (x<=tmp.min) tmp.min=x;
		//Actualizar máximo
		if (x>=tmp.max) tmp.max=x;
		//Actualizar contagem valores
		tmp.num++;
		//Actualizar somatório dos valores
		tmp.sum+=x;
		//Actualizar quadrado dos valores
		tmp.sum2+=Math.pow(x, 2);
		
	}
	
	public static double mean (Statistics tmp1) {
		double mean=tmp1.sum/tmp1.num;
		return mean;
	}
	
	public static double variance (Statistics tmp2) {
		double variance=Math.pow(tmp2.sum2/(tmp2.num-Math.pow(mean(tmp2),2)), 2); 
		return variance;
	}
}

//Definir classe Statistics
class Statistics {
	double max;			//Valor máximo
	double min;			//Valor mínimo
	int num=0;			//Contagem dos valores
	double sum=0;			//Somatório dos valores
	double sum2=0; 		//Somatório do quadrado dos valores
}