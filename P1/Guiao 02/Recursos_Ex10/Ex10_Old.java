package Recursos_Ex10;
/*
 * Ex10.java
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

//Dia seguinte está a funcionar

import java.util.Scanner;
public class Ex10_Old {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);
		
		int dia, mes, ano, bissexto, ultimo_dia, dia_anterior, mes_anterior, ano_anterior, dia_seguinte, mes_seguinte, ano_seguinte;

		//-----------------------------------
		//A. Leitura das variáveis dia, mes e ano
		System.out.printf("Calculo da data do dia seguinte e da data do dia anterior\n-----------\n\nIntroduza o dia, mes e ano: \n");
		dia=ler.nextInt();
		mes=ler.nextInt();
		ano=ler.nextInt();
		
		//-----------------------------------
		//B. Inicialização das variáveis
		bissexto=0; 
		dia_anterior=0;
		mes_anterior=0;
		ano_anterior=0;
		dia_seguinte=0;
		mes_seguinte=0;
		ano_seguinte=0;
		ultimo_dia=0;
		
		//-----------------------------------
		//C. Determinação de ano bissexto
		if ((ano % 400 ==0)) 
			{bissexto=1;}
			
			else if ((ano % 4 ==0) && (ano % 100 !=0))
			{bissexto=1;}
			
			else 
			{bissexto=0;}
		
		//-----------------------------------	
		//D. Cálculo do último dia de cada mês
		
			//1. Fevereiro -- dependende de ser ou não ano bissexto
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
		
		//-----------------------------------							
		
		/*Debug
		System.out.printf("\n------\nDebug:\n");
		if (bissexto==0) {System.out.printf("Ano bissexto: Nao\n");}
		else {System.out.printf("Ano bissexto: Sim\n");}
		System.out.printf("Dia introduzido: %2d-%2d-%4d\nUltimo dia do mes: %2d\n------\n", dia, mes, ano, ultimo_dia);
		*/
		
		//-----------------------------------
		//E. Cálculo do dia anterior
		
			//Se for o primeiro dia do mês, mudamos de mês
			if (dia==1)
			{
				//Se for o primeiro mês do ano, também mudamos de ano
				if (mes==1) 
					{
					dia_anterior=31;
					mes_anterior=12;
					ano_anterior=ano-1;
					}
				//Para os restantes meses do ano
				else if (mes!=1) 
					{
						//Se for 1-Março, o dia anterior pode ser 28 ou 29-Fev, consoante seja um ano bissexto ou não
						if (mes==3)
						{
							switch (bissexto)
							{
								case 0: dia_anterior=28; break;
								case 1: dia_anterior=29; break;
							}
						}
						//Se for dia 1 de outro mês que não Janeiro ou Março, sabemos o dia anterior
						else if (mes!=3)
						{
							switch (mes)
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
						}
					mes_anterior=mes-1;
					ano_anterior=ano;
					} 
			}
			
			//Se for outro dia que não o primeiro de cada mês
			else if (dia!=1)
			{
			dia_anterior=dia-1;
			mes_anterior=mes;
			ano_anterior=ano;
			}		 
		
		//-----------------------------------
		//F. Cálculo do dia seguinte
			
			//Se for o último dia do mês, mudamos de mês
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
		//G. Impressão do resultado final
		System.out.printf("\nDia anterior: %2d-%2d-%4d\n", dia_anterior, mes_anterior, ano_anterior);
		System.out.printf("\nDia seguinte: %2d-%2d-%4d\n\n", dia_seguinte, mes_seguinte, ano_seguinte);
}
}
