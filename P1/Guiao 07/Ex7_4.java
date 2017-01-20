/*
 * Ex7_4.java
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
public class Ex7_4 {

	static Scanner ler=new Scanner (System.in);
	public static void main(String[] args) {

		//Inicialização de variáveis
		String opcao = null;

		//UI 
		System.out.printf("| Calculadora de numeros complexos |\n------------------------------------");

		//Executa o programa enquanto a opção escolhida pelo utilzador for for diferente de "="
		do {
			//Apresenta menu
			printMenu();

			//Lê uma opção
			opcao=ler.next();
			//Validação da opção escolhida
			while ((!opcao.equals("+")) && (!opcao.equals("-")) && (!opcao.equals("*")) && (!opcao.equals("/")) ) {

				//Termina o programa com a a opção "=" 
				if (opcao.equals("=")) {
					System.out.printf("O programa vai terminar...");
					System.exit(0);}

				System.out.printf("\nPor favor introduza uma opcao valida:\n--> ");
				opcao=ler.next();
			}
			//Eecuta a operação 
			runOperations(opcao);
			System.out.printf("\n");

		} while (true); //Repete indefinidamente

	}

	//Função que lê um número complexo
	public static Complexo readComplex () {
		//Cria um número complexo
		Complexo complex=new Complexo();

		//UI
		System.out.printf("Introduza um numero complexo:");
		System.out.printf("\nParte real: ");
		complex.r=ler.nextDouble();
		System.out.printf("Parte imag: ");
		complex.i=ler.nextDouble();

		return complex;
	}

	//Função que imprime um número complexo
	public static void printComplex (Complexo to_print) {
		if (to_print.i<0) System.out.printf("%.2f%.2fi",to_print.r,to_print.i);
		else if (to_print.i>=0){System.out.printf("%.2f+%.2fi",to_print.r,to_print.i);
		}
	}

	//Funções para operações com números complexos
	public static Complexo addComplexs (Complexo c1, Complexo c2) {
		Complexo result=new Complexo();			//Resultado (que será um número complexo) 
		result.r=c1.r+c2.r;
		result.i=c1.i+c2.i;
		return result;
	}

	public static Complexo subtractComplexs (Complexo c1, Complexo c2) {
		Complexo result=new Complexo();
		result.r=c1.r-c2.r;
		result.i=c1.i-c2.i;
		return result;
	}

	public static Complexo productComplexs (Complexo c1, Complexo c2) {
		Complexo result=new Complexo();
		result.r=(c1.r*c2.r-c1.i*c2.i);
		result.i=(c1.i*c2.r+c1.r*c2.i);
		return result;
	}

	public static Complexo divideComplexs (Complexo c1, Complexo c2) {
		Complexo result=new Complexo();
		result.r=((c1.r*c2.r+c1.i*c2.i)/(Math.pow(c2.r, 2)+Math.pow(c2.i,2)));
		result.i=((c1.r*c2.r-c1.r*c2.i)/(Math.pow(c2.r, 2)+Math.pow(c2.i,2)));
		return result;
	}

	//Função que imprime a UI estática do menu
	public static void printMenu () {
		System.out.printf("\n| Escolha uma operacao (= termina):|\n");
		System.out.printf("| +  Adicao                        |\n");
		System.out.printf("| -  Subtracao                     |\n");
		System.out.printf("| *  Multiplicacao                 |\n");
		System.out.printf("| /  Divisao                       |\n--> ");
	}

	//Função que executa as operações
	public static void runOperations (String opcao) {
		Complexo c1=new Complexo();     //Números complexos a serem lidos. Pode ser pedido ao utilizador
		Complexo c2=new Complexo(); 	//(criar uma array c/números complexos e criar ciclos for dentro das funções p/operações)
		Complexo result=new Complexo(); //Resultado das operações

		//Pede ao utilizador 2 numeros complexos (os operandos)
		c1=readComplex();
		c2=readComplex();

		//Executa a operação pedida e imprime o seu resultado
		switch (opcao) {
		case "+": {
			result=addComplexs(c1,c2); 
			printComplex(c1);
			System.out.printf(" + ");
			printComplex(c2);
			System.out.printf(" = ");
			printComplex(result); 
			break;
		}
		case "-": {
			result=subtractComplexs(c1,c2); 
			printComplex(c1);
			System.out.printf(" - ");
			printComplex(c2);
			System.out.printf(" = ");
			printComplex(result);
			break;
		}			
		case "*": {
			result=productComplexs(c1,c2); 
			printComplex(c1);
			System.out.printf(" * ");
			printComplex(c2);
			System.out.printf(" = ");
			printComplex(result);
			break;
		}
		case "/": {
			result=divideComplexs(c1,c2); 
			printComplex(c1);
			System.out.printf(" / ");
			printComplex(c2);
			System.out.printf(" = ");
			printComplex(result);
			break;
		}
		}
	}
}

//Classe para representar um número complexo 
class Complexo {
	double r;
	double i;
}