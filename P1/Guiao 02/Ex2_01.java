/*
 * Ex1.java
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

public class Ex2_01
{	
	public static void main (String[] args) 
	{
		Scanner ler = new Scanner(System.in);
		
		double TP1, TP2, API, EP, nota;
		String nome;
		
		System.out.printf("PROGRAMACAO I\n");
		System.out.println("------------");
		System.out.printf("Introduza o nome e o numero mecanografico do aluno: ");
		nome=ler.nextLine();
		
		System.out.printf("Introduza a nota dos trabalhos TP1, TP2, API e EP, por ordem, da disciplina de P1: ");
		TP1=ler.nextDouble();
		TP2=ler.nextDouble();
		API=ler.nextDouble();
		EP=ler.nextDouble();
		
		nota=0.10*TP1+0.10*TP2+0.30*API+0.5*EP;
		
		if (nota<9.5 && nota >=0) System.out.printf("\n%s : Reprovado na disciplina.\n", nome);
		else if (nota>=9.5 && nota<=20) System.out.printf("\n%s : Aprovado na disciplina.\n", nome);
		else System.out.printf("\nOs valores que introduziu nao sao validos\n");
		
		/*
		if (nota >= 9.5)
		{
			System.out.println("Aprovado.");
		}
		else 
		{
			System.out.println("Reprovado.");
		}
		*/
	}
}

