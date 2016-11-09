/*
 * Ex4.java
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
public class Ex2_04 {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);	
		
		int mes, ano;
		
		System.out.print("Introduza o mes e o ano: ");
		mes=ler.nextInt();
		ano=ler.nextInt();
		
		if (mes==2) 
		{
			/* Código antigo
			if ((ano % 4) == 0)
			{
				if ((ano % 400) == 0)
					{
						if ((ano % 100) !=0)
							{System.out.printf("O mes 2 do ano %d tem 29 dias.\n", ano);}
						else System.out.printf("O mes 2 do ano %d tem 28 dias.\n", ano);
					}
				else
					{System.out.printf("O mes 2 do ano %d tem 28 dias.\n", ano);} 
			}
			else {System.out.printf("O mes 2 do ano %d tem 28 dias.\n", ano);}
			*/
			//Verificação do ano bissexto
			if ((ano % 400 ==0)) 
			{System.out.printf("O mes 2 do ano %d tem 29 dias.\n", ano);}
			
			else if ((ano % 4 ==0) && (ano % 100 !=0))
			{System.out.printf("O mes 2 do ano %d tem 29 dias.\n", ano);}
			
			else {System.out.printf("O mes 2 do ano %d tem 28 dias.\n", ano);}
				
		}
		else switch(mes)
			{
				case 4: System.out.printf("O mes 4 do ano %d tem 30 dias.\n", ano);
				break; 
				case 6: System.out.printf("O mes 6 do ano %d tem 30 dias.\n", ano); 
				break;
				case 9: System.out.printf("O mes 9 do ano %d tem 30 dias.\n", ano); 
				break;
				case 11: System.out.printf("O mes 11 do ano %d tem 30 dias.\n", ano); 
				break;
				default: System.out.printf("O mes %d do ano %d tem 31 dias.\n", mes, ano); 
				break;
			}
		}
	}


