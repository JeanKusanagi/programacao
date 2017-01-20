/*
 * Ex5_3.java
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

/*
 * Pedir ao utilizador uma data composta pelo dia, mês e ano.
 * Depois o programa deve calcular e imprimir no terminal a data do dia seguinte e data do dia
 * anterior.
 * O resultado do programa considerando o dia 31 do mês 1 do ano 2000 será o seguinte:
 * O dia seguinte é 1- 2-2000
 * O dia anterior é 30- 1-2000
 */

import java.util.Scanner;
public class Ex5_3 {

	public static void main (String[] args) {
		int dia, mes, ano, bissexto, ultimo_dia, dia_anterior, mes_anterior, ano_anterior, dia_seguinte, mes_seguinte, ano_seguinte;

		//-----------------------------------
		//A. Leitura das variáveis dia, mes e ano
		System.out.printf("Calculo da data do dia seguinte e da data do dia anterior\n-----------\n\nIntroduza o dia, mes e ano: \n");
		dia=introducao_dia_mes(1,31, "dia");
		mes=introducao_dia_mes(1,12, "mes");
		ano=introducao_ano(0, "ano");

		//-----------------------------------
		//B. Inicialização das variáveis 
		dia_anterior=0;
		mes_anterior=0;
		ano_anterior=0;
		dia_seguinte=0;
		mes_seguinte=0;
		ano_seguinte=0;

		//-----------------------------------
		//C. Cálculo do dia anterior

		//Se for o primeiro dia do mês, mudamos de mês
		if (dia==1)
		{
			//Se for o primeiro mês do ano, também mudamos de ano
			if (mes==1) 
			{	dia_anterior=31;
				mes_anterior=12;
				ano_anterior=ano-1;	}

			//Se for 1-Março, o dia anterior pode ser 28 ou 29-Fev, consoante seja um ano bissexto ou não
			else if ((mes!=1) && (mes==3))
			{	bissexto=ano_bissexto(ano);
				switch (bissexto)
				{case 0: dia_anterior=28; break;
				case 1: dia_anterior=29; break;}
				mes_anterior=2;
				ano_anterior=ano;	}

			//Para os restantes meses do ano, sabemos o dia anterior
			else if ((mes!=1) && (mes!=3)) 
			{	switch (mes)
				{		
				case 2: dia_anterior=31; break;					
				case 4: dia_anterior=31; break;			
				case 6: dia_anterior=31; break;			
				case 8: dia_anterior=31; break;		
				case 9: dia_anterior=31; break;		
				case 11: dia_anterior=31; break;		

				case 5: dia_anterior=30; break;			
				case 7: dia_anterior=30; break;					
				case 10: dia_anterior=30; break;		
				case 12: dia_anterior=30; break;
				}
				mes_anterior=mes-1;
				ano_anterior=ano;	} 
		}
			//Se for outro dia que não o primeiro de cada mês
			else if (dia!=1)
			{	dia_anterior=dia-1;
				mes_anterior=mes;
				ano_anterior=ano;	}

		//-----------------------------------
		//D. Cálculo do dia seguinte

		//Se for o último dia do mês, mudamos de mês
		ultimo_dia=numerodias_mes(mes,ano);
		if (dia==ultimo_dia)
		{
			//Se for Dezembro, também mudamos de ano 
			if (mes==12) 
			{
				dia_seguinte=1;
				mes_seguinte=1;
				ano_seguinte=ano+1;
			}
			//Para os restantes meses, apenas mudamos de mês 
			else if (mes!=12)
			{
				dia_seguinte=1;
				mes_seguinte=mes+1;
				ano_seguinte=ano;
			}
		}

		//Se não for o último dia do mês
		else if (dia!=ultimo_dia)
		{
			dia_seguinte=dia+1;
			mes_seguinte=mes;
			ano_seguinte=ano;
		}

		//-----------------------------------
		//E. Impressão do resultado final
		System.out.printf("\nDia anterior: %2d-%2d-%4d\n", dia_anterior, mes_anterior, ano_anterior);
		System.out.printf("\nDia seguinte: %2d-%2d-%4d\n\n", dia_seguinte, mes_seguinte, ano_seguinte);
	}
	
	public static int ano_bissexto (int ano)  //Determinação de ano bissexto
	{
		int bissexto=0;
		if ((ano % 400 ==0)) 
		{bissexto=1;}

		else if ((ano % 4 ==0) && (ano % 100 !=0))
		{bissexto=1;}

		else 
		{bissexto=0;}

		return bissexto;
	}

	public static int numerodias_mes (int mes, int ano) //Cálculo do último dia de cada mês (equivalente a dizer cálculo do numero de dias do mes)
	{
		//1. Fevereiro -- dependende de ser ou não ano bissexto
		int bissexto=ano_bissexto(ano);
		int ultimo_dia=0;

		if (mes==2) 
		{
			if (bissexto==1) {ultimo_dia=29;}
			else if (bissexto==0) {ultimo_dia=28;}
		}	

		//2. Outros meses -- último dia é conhecido
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
	
	public static int introducao_dia_mes (int lim1, int lim2, String message) //Para introduzir dia e mês e validar
	{
		Scanner ler = new Scanner(System.in);
		int lim_superior=0, lim_inferior=0, n=0;
		
		//Determinar qual é o limite inferior e qual é o superior
		if (lim1>lim2) {lim_superior=lim1; lim_inferior=lim2;}
		else if (lim1<lim2) {lim_superior=lim2; lim_inferior=lim1;}
		
		//Leitura do número
		n=ler.nextInt();
		
		//Validação do número, tem de estar entre lim_inferior e lim_superior
		while ((n>lim_superior) || (n<lim_inferior))
		{System.out.printf("\nTem de introduzir %s entre %d e %d.\n", message, lim_inferior, lim_superior);
		n=ler.nextInt();}
		
		//Devolver o número
		return n;
	}
	
	public static int introducao_ano (int lim, String message) //Para introduzir ano e validar
	{
		Scanner ler = new Scanner(System.in);
		int n=0;
		n=ler.nextInt();
		while (n<lim)
		{System.out.printf("\nTem de introduzir %s positivo\n", message);
		n=ler.nextInt();}
		return n;
	}
}
