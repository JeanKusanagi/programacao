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
public class Ex2_03 {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);	
		
		double idade;
		
		System.out.print("Introduza a idade do visitante: ");
		idade=ler.nextDouble();
		
		if (idade<6) {System.out.println("Tipo de bilhete:: Isento de pagamento");}
		else if (idade >=6 && idade<=12) {System.out.println("Tipo de bilhete:: Bilhete de crianca");}
		else if (idade >=13 && idade <=65) {System.out.println("Tipo de bilhete:: Bilhete normal");}
		else  {System.out.println("Tipo de bilhete:: Bilhete de terceira idade");}
		
	}
}

