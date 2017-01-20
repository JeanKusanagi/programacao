/*
 * Ex7_5.java
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
public class Ex7_5 {

	static Scanner ler = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.printf("Calculadora da Taxa de Alcoolemia de um individuo (TAS)\n-------------------------------\n");
		Info info=getInfo();

		//Calcula o coeficiente
		double coef = 0;
		if (info.emJejum) {
			if (info.gender.equals("M")) coef=0.7;
			if (info.gender.equals("F")) coef=0.6;
		}
		else if (!info.emJejum) coef=1.1;
		
		//Densidade do álcool
		double density=0.8;
		
		//Cálcula a TAS
		double tas=calculateTas(info, density, coef);
				
		//Imprime a TAS
		printTas(tas, info);
	}

	//Função que solicita ao utilizador nome, sexo, peso (kg), quantidade bebida (ml), teor alcoólico e consumido em jejum
	public static Info getInfo () {
		Info tmp=new Info();
		tmp.name=getName();
		tmp.gender=getGender();
		tmp.weigth=getDoublePos("Peso (kg): ");
		tmp.quantity=getDoublePos("Quantidade de bebida ingerida (mL): ");
		tmp.content=getDoublePos("Teor alcoólico da bebida (graduação em % volume): ");
		tmp.emJejum=getBooleanInfo();
		return tmp;
	}

	//Função que calcula a TAS
	public static double calculateTas (Info tmp1, double density, double coef) {
		double tas=(density*tmp1.quantity*tmp1.content/100)/(tmp1.weigth*coef);
		return tas;
	}

	//Função que imprime a TAS
	public static void printTas (double tas, Info tmp2) {
		System.out.printf("\n-------------------------------\nEstimativa da taxa de alcoolemia para\n%s - %f kg: %f",tmp2.name,tmp2.weigth, tas);
	}

	//Função que lê o nome de uma pessoa
	public static String getName () {
		System.out.printf("Nome: ");
		String name=ler.next();
		return name;
	}

	//Função que lê o sexo da pessoa, com validação.
	public static String getGender () {
		System.out.printf("Sexo (M - Masculino/F - Feminino):  ");
		String gender=ler.next();
		while ((!gender.equals("M")) && (!gender.equals("F"))) {
			System.out.printf("\nPor favor introduza uma opcao valida (M - Masculino/F - Feminino): ");
			gender=ler.next();
		}
		return gender;
	}

	//Função que lê o peso, a quantidade de bebida ingerida e o teor alcóolico da bebida (valores positivos)
	public static double getDoublePos (String message) {
		System.out.printf("%s",message);
		double n=ler.nextDouble();
		while (n<0) {
			System.out.printf("\nPor favor introduza um numero real positivo: ");
			n=ler.nextDouble();}
		return n;
	}

	//Função que lê se a bebida foi consumida em jejum ou não
	public static boolean getBooleanInfo () {
		System.out.printf("Consumida em jejum (S - Sim/N - Nao): ");
		String answer=ler.next();
		while ((!answer.equals("S")) && (!answer.equals("N"))) {
			System.out.printf("\nPor favor introduza uma opcao valida (S - Sim/N - Nao): ");
			answer=ler.next();
		}
		boolean info = false;
		if (answer.equals("S")) info=true;
		if (answer.equals("N")) info=false; 

		return info; 
	}
}

class Info {
	String name;			//Nome
	String gender;			//Sexo
	double weigth=0;		//Peso (kg)
	double quantity=0;		//Quantidade de bebida ingerida (ml)
	double content=0;		//Teor alcoólico da bebida (% v/v)
	boolean emJejum=false;	//Foi consumida em jejum? (true=Sim, false=Não)

}