/*
 * Ex8_8.java
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
public class Ex8_8 {

	static Scanner ler = new Scanner (System.in);

	public static void main (String[] args) {
		//UI inicial
		System.out.printf("Conversor de números inteiros para base decimal\n------------------------\n");
		System.out.printf("<0 termina>\n");
		String num="";                

		do {
			//Leitura do número
			System.out.printf("\nNúmero inteiro a converter -> ");
			num=ler.next();

			//Leitura da base
			int base=getBase();

			//Valida o número
			boolean valid=verifyNumber(num, base);

			//Obtenção do número na nova base
			int num_dec = 0;

			//Se o número for válido, converte
			if (valid) num_dec=baseToNum (num,base);

			//Senão, pede um novo número e base
			else if (!valid) {
				System.out.printf("\nNúmero inválido.\nNúmero inteiro a converter -> ");
				num=ler.next();
				base=getBase();
				valid=verifyNumber(num, base);
			}

			//Impressão do resultado
			System.out.printf("Número %s na base decimal : %d\n", num, num_dec); 

		} while (num!="0");

	}

	//Função que lê, com validação, a base do número 
	public static int getBase () {
		//Leitura da base
		System.out.printf("Base em que se encontra o número dado --> ");
		int base=ler.nextInt();

		//Valida a base (base>=2 e <=10) 
		while (base<2 || base>10) { 
			System.out.print("\nBase inválida. Por favor introduza uma base entre 2 e 10: ");
			base = ler.nextInt(); 	
		}

		return base;
	}

	//Função que determina se um dado número pertence a uma dada base (um número em base N só pode ter dígitos na gama 0, …, N-1)
	public static boolean verifyNumber (String n, int b) {
		boolean valid=false;

		for (int i=0; i<n.length(); i++){                         
			if(Character.getNumericValue(n.charAt(i))<=b) valid = true; 
			else valid=false;
		}

		return valid;	
	}

	//Função que converte um número n numa base b para a base decimal 
	public static int baseToNum (String n, int b) {
		int new_num=0;
		int j=0;		//Posição de cada algarismo do número 

		//Para cada elemento c no índice i do número original
		for(int i=n.length()-1; i>=0; i--){ 
			new_num += ((Character.getNumericValue(n.charAt(i)))*(Math.pow(b, j)));  //Adicionar a new_num c*b^j (sistema posicional) 
			j++;  
		}                

		return new_num;
	}
}
