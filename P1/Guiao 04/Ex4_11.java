/*
 * Ex4_11.java
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

public class Ex4_11 {
	public static void main(String[] args) {
		
		/*Debug
		char letra = (char)('a' - 6);
		System.out.print(letra);
		*/
		
		System.out.printf("Tabela com as coordenadas das casas de um tabuleiro de xadrez\n\n");

		//8 Colunas por 8 Linhas
		//Fazer as i linhas
		for (int i=8; i>=1; i--) {
			//Em cada linhas, fazer as j colunas 
			for (int j=0; j<=7; j++) {
				char letra=(char) ('a'+j); 
				System.out.printf("%c%d ", letra, i);
			}
			System.out.printf("\n");
	}
	}

}
