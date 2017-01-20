/*
 * Ex4_12.java
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

//O truque é pensar na linha como um array de 7 elementos (ie o ultimo elemento da linha corresponde à posição 7

import java.util.Scanner;
public class Ex4_12 {

	static Scanner ler=new Scanner(System.in);

	public static void main(String[] args) {

		//Introdução das variáveis mês, ano e do dia da semana em que começa esse mês, com validação
		System.out.printf("Calendario mensal\n------------------------\n");

		int mes=getIntRange("  - Introduza o mes:\n     -> ", 1,12);
		System.out.printf("  - Introduza o ano:\n     -> ");
		int ano=ler.nextInt();
		int primeiro_dia=getIntRange("  - Introduza o dia da semana em que começa esse mes: \n    <1= Domingo, 2= Segunda, 3= Terça, 4= Quarta, 5= Quinta, 6= Sexta, 7= Sábado>\n     -> ", 1,7);

		imprimirCalendario(primeiro_dia,mes,ano);

	}	

	//Impressão do calendário
	public static void imprimirCalendario (int primeiro_dia, int mes, int ano) {

		System.out.printf("\n----------------------");
		System.out.printf("\n|   %s %4d    |", getMonthName(mes), ano);
		System.out.printf("\n----------------------");
		System.out.printf("\n|Su Mo Tu We Th Fr Sa|");
		System.out.printf("\n----------------------");
		System.out.printf("\n|");
		
		//Variável p/a posição na linha
		int i=1;

		//Imprime nas i posições antes do primeiro dia
		for (i=1; i<primeiro_dia; i++) {
			System.out.printf("   ");
		}

		boolean bissexto=ano_bissexto(ano);
		int numdias_mes=getNumDays_Month(mes, bissexto);

		//Imprime os dias do mês
		for (int j=1; j<=numdias_mes; j++){

			if (((i % 7)==0)) {System.out.printf("%2d|\n|", j);} 		//Muda de linha quando a posição na linha é 7 ou múltiplo
			else {System.out.printf("%2d ", j);}
			i++;
		}

		//Espaços finais (até a posição ser múltipla de 7, ie fim da linha);
		while ((i%7)!=0) {
			System.out.printf("   ");
			i++;
		}

		System.out.printf("  |\n----------------------\n");
	}

	//Introdução de valores pertencentes a um determinado intervalo, com validação
	public static int getIntRange (String message, int lim1, int lim2)	{
		Scanner ler = new Scanner(System.in);
		int lim_superior=0, lim_inferior=0, n=0;

		if (lim1>lim2) {lim_superior=lim1; lim_inferior=lim2;}
		else if (lim1<lim2) {lim_superior=lim2; lim_inferior=lim1;}

		System.out.printf("%s", message);
		n=ler.nextInt();
		while ((n>lim_superior) || (n<lim_inferior))
		{System.out.printf("\nTem de introduzir um valor pertencente ao intervalo [%d, %d]: ", lim_inferior, lim_superior);
		n=ler.nextInt();
		}
		return n;
	}

	//Determinação do nome do mês
	public static String getMonthName (int mes) {										

		String nome_mes="";

		switch (mes)
		{
		case 1: nome_mes="January"; break;			
		case 2:	nome_mes="February"; break;
		case 3: nome_mes="March"; break;					
		case 4: nome_mes="April"; break;
		case 5: nome_mes="May"; break;			
		case 6: nome_mes="June"; break;	
		case 7: nome_mes="July"; break;			
		case 8: nome_mes="August"; break;		
		case 9: nome_mes="September"; break;
		case 10: nome_mes="October"; break;		
		case 11: nome_mes="November"; break;
		case 12: nome_mes="December"; break;		
		}

		return nome_mes;
	}

	//Determinação de ano bissexto 
	public static boolean ano_bissexto (int ano) { 									
		boolean bissexto;
		if ((ano % 400 ==0)) 
		{bissexto=true;}

		else if ((ano % 4 ==0) && (ano % 100 !=0))
		{bissexto=true;}

		else 
		{bissexto=false;}

		return bissexto;
	}

	//Determinação do último dia do mês (ie número de dias do mês)
	public static int getNumDays_Month (int mes, boolean bissexto) {					

		int ultimo_dia=0;

		if (mes==2) {
			if (bissexto=true) {ultimo_dia=29;}
			else if (bissexto=false) {ultimo_dia=28;}
		}	

		else if (mes!=2)
		{
			switch (mes)
			{
			case 1: ultimo_dia=31; break;			
			case 3: ultimo_dia=31; break;					
			case 5: ultimo_dia=31; break;			
			case 7: ultimo_dia=31; break;			
			case 8: ultimo_dia=31; break;		
			case 10: ultimo_dia=31; break;		
			case 12: ultimo_dia=31; break;		
			case 4: ultimo_dia=30; break;			
			case 6: ultimo_dia=30; break;					
			case 9: ultimo_dia=30; break;		
			case 11: ultimo_dia=30; break;
			}
		}

		return ultimo_dia;
	}
}
