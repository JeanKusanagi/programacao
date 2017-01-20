/*
 * Ex3_7.java
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
public class Ex3_07 {

	public static void main(String[] args) {
		Scanner ler=new Scanner(System.in);
		
		//Leitura das variáveis
		System.out.printf("Algoritmo da Multiplicacao russa\nIntroduza um x e um y: ");
		int x=ler.nextInt();
		int y=ler.nextInt();
		
		//Inicialização das variáveis
		int x_inicial=x;						//Variáveis p/apresentação do resultado
		int y_inicial=y; 						//Variáveis p/apresentação do resultado
		int soma=0;
		if ((x%2)!=0) {soma=y;}
		
		//Algoritmo da Multiplicação russa
		while (x>1) 							//Divide-se X sucessivamente por dois até se obter o quociente 1
		{
			x=x/2;								//Divisão de X por 2
			y=y*2;								//Ao mesmo tempo multiplica-se Y por 2
			if ((x%2)!=0) {soma=soma+y;}		//Adicionam-se ao resultado os valores de Y sempre que X é ímpar.
		}
		
		//Apresentação do resultado
		System.out.printf("%d x %d = %d", x_inicial, y_inicial, soma);
	}
}
