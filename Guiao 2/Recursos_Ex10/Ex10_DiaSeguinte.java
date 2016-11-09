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
public class Ex10_DiaSeguinte {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);
		
		int dia, mes, ano, ultimo_dia, bissexto;
		//Leitura das variáveis
		System.out.printf("Introduza o dia, mes e ano: \n");
		dia=ler.nextInt();
		mes=ler.nextInt();
		ano=ler.nextInt();
		ultimo_dia=0;
		
		//Qual é o último dia de cada mês?
		switch (mes)
		{
			case 1: 
						ultimo_dia=31;
						
			case 3: 
						ultimo_dia=31;
									
			case 5: 
						ultimo_dia=31;
							
			case 7: 
						ultimo_dia=31;
							
			case 8: 
						ultimo_dia=31;
						
			case 10: 
						ultimo_dia=31;
							
			case 12: 
						ultimo_dia=31;
						
			case 4: 
						ultimo_dia=30;
							
			case 6: 
						ultimo_dia=30;
									
			case 9: 
						ultimo_dia=30;
						
			case 11: 
						ultimo_dia=30;
							
			case 2: 
						if ((ano % 400 ==0)) 
						{bissexto=1;
						ultimo_dia=29;}
			
						else if ((ano % 4 ==0) && (ano % 100 !=0))
						{bissexto=1;
						ultimo_dia=29;}
			
						else {{
						bissexto=0;
						ultimo_dia=28;}
		}					
		//Debug
		System.out.printf("\n------\nDebug:\n");
		if (bissexto==0) {System.out.printf("Ano bissexto: Nao\n");}
		else {System.out.printf("Ano bissexto: Sim\n");}
		System.out.printf("Dia introduzido: %2d-%2d-%4d\nUltimo dia do mes: %2d\n------\n", dia, mes, ano, ultimo_dia);
		
		
		if (dia==ultimo_dia)
		{
			if (mes==12) {mes=1;
				ano=ano+1;}
			else if (mes!=12)
			{dia=1;
			mes=mes+1;}
		}
		else {dia=dia+1;}
		System.out.printf("\nDia seguinte: %2d-%2d-%4d\n", dia, mes, ano);
}
}
}




