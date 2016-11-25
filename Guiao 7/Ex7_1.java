/*
 * Ex7_1.java
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
 * EXEMPLO do pretendido:
 * $ java TestaHora
 * Começou às 09:23:05.
 * Quando termina?
 * horas? 11
 * minutos? 7
 * segundos? 2
 * Início: 09:23:05 Fim: 11:07:02.
 */

import java.util.Scanner;

public class Ex7_1 {
	static Scanner ler = new Scanner(System.in);

	public static void main(String[] args) {
		Hora inicio;  // tem de definir o novo tipo Hora!
		Hora fim;

		inicio = new Hora();
		inicio.h = 9;
		inicio.m = 23;
		inicio.s = 5;

		System.out.print("Começou às ");
		printHora(inicio);  // crie esta função!
		System.out.println(".");
		System.out.println("Quando termina?");
		
		fim = lerHora();  // crie esta função!
		System.out.print("\nInício: ");
		printHora(inicio);
		System.out.print("\nFim: ");
		printHora(fim);
	}
	
	// Função que escreve uma hora no formato HH:MM:SS
	public static void printHora(Hora tmp){
		System.out.printf("%02d:%02d:%02d", tmp.h,tmp.m,tmp.s);
	}
	
	// Função que lê uma hora, com validação (hora [0,23], minutos [0,59], segundos [0,59]
	public static Hora lerHora(){
		Hora read_h = new Hora();

		System.out.printf("Horas? ");
		do {
			read_h.h=ler.nextInt();
		} while ((read_h.h<0) || (read_h.h>23));
		
		System.out.printf("Minutos? ");
		do {
			read_h.m=ler.nextInt();
		} while ((read_h.m<0) || (read_h.m>59));
		
		System.out.printf("Segundos? ");
		do {
			read_h.s=ler.nextInt();
		} while ((read_h.s<0) || (read_h.s>59));
		
		return read_h;
	}
}

class Hora {
	int h, m, s;
}

