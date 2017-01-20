/*
 * Ex7_6.java
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
public class Ex7_6 {

	static Scanner ler = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//Número de dias a serem analisados
		int n=5;
	
		//Inicialização da array com as informações sobre temperaturas para cada um dos n dias 
		TempsDay[] temp_days= new TempsDay[n];
		
		//Ler informações sobre temperaturas para os n dias
		for (int i=0; i<n; i++) {
			System.out.printf("\nDia %d:\n", i+1);
			temp_days[i]=getTemps();
		}

		//Obter amplitude térmica máxima e o dia correspondente
		//Inicializar variáveis
		double max_amp=temp_days[0].amp;
		int day_max=0;
		
		for (int j=1; j<n; j++) {
			if (temp_days[j].amp>max_amp) {
				max_amp=temp_days[j].amp;
				day_max=j;
			}
		}
		
		//Imprimir resultados
		printResults(max_amp,day_max+1);
		
	}
	
	//Ler informações sobre temperaturas para 1 dia 
	public static TempsDay getTemps () {
		TempsDay values = new TempsDay();
		values.tmin=getTemp("Temperatura minima: ",-20,50);
		values.tmax=getTemp("Temperatura maxima: ",values.tmin,50);
		values.amp=values.tmax-values.tmin;
		
		return values;
	}
	
	//Ler valor da temperatura mínima e máxima com validação
	public static double getTemp (String message, double lim1, double lim2)
	{
		double lim_superior=0, lim_inferior=0, n=0;
		
		//Determina qual é o limite mínimo e qual é o limite superior
		if (lim1>lim2) {lim_superior=lim1; lim_inferior=lim2;}
		else if (lim1<lim2) {lim_superior=lim2; lim_inferior=lim1;}
		
		//UI
		System.out.printf("%s", message);
		
		//Lê números enquanto este não estiver dentro do intervalo pretendido
		n=ler.nextDouble();
		while ((n>lim_superior) || (n<lim_inferior))
		{System.out.printf("\nPor favor introduza um valor maior que %f e menor que %f: ", lim_inferior, lim_superior);
		n=ler.nextDouble();
		}
		return n;
	}
	
	//Imprimir resultados
	public static void printResults (double max, int day) {
		System.out.printf("\nAmplitude termica maxima : %f, no dia %d", max, day);
	}
}

//Dados de cada dia
class TempsDay {
	double tmin;		//Temperatura mínima
	double tmax;		//Temperatura máxima
	double amp;			//Amplitude térmica
}