/**
 * E7
 * 
 * Crie um programa que dado um numero racional pertencente ao intervalo aberto entre zero
 * e um, e expresso como uma fracção (n=d), escreva essa fracção como sendo uma soma de
 * fracções unitárias com denominadores diferentes6. Uma fraccção unitária é uma fracção em
 * que o numerador é igual a um. O programa a desenvolver deve fazer uso de um algoritmo
 * recursivo.
 * 
 * Seguem alguns exemplos da execução pretendida do programa:
 * java -ea UnitaryFractionSum 3 4 3/4 = 1/2 + 1/4
 * java -ea UnitaryFractionSum 3 7 3/7 = 1/3 + 1/11 + 1/231
 * java -ea UnitaryFractionSum 1 8 1/8 = 1/8
 * java -ea UnitaryFractionSum 2 20 2/20 = 1/10
 * 
 * 
 * @author Pedro Teixeira
 * Copyright 2017, MIECT - DETI UA
 */

package e07;

import java.util.Scanner;

public class E7_Fractions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Read strings
		
		// Through console if arguments are not given
		if (args.length == 0) {
			Scanner read = new Scanner(System.in);
			
			System.out.println("Please insert the fraction: ");
			String str = read.nextLine();
			Fraction f = new Fraction(str.charAt(0), str.charAt(2));
			getUnitaryFractions(f);
			
		// Or through args	
		} else {
			for (String i: args) {
				Fraction f = new Fraction(i.charAt(0), i.charAt(2));
				System.out.println(f + " = ");
				getUnitaryFractions(f);
			}
		}
		
		System.exit(0);

	}
	
	public static Fraction getUnitaryFractions (Fraction argFrac) {
		// calculate unitary fraction
		Fraction d = new Fraction(1, (int) Math.ceil(argFrac.den() / argFrac.num()));
		if (argFrac.den() % argFrac.num() == 0) {
			return d;
		}
		else return (d.add(getUnitaryFractions(argFrac.subtract(d))));
	}

}

class Fraction {
	// Fields
	private double num, den;
	
	// Constructor
	public Fraction (double argNum, double argDen) {
		this.num = argNum;
		this.den = argDen;
	}
	
	public Fraction() {
	}

	// Getters
	public double num() {
		return this.num;
	}
	
	public double den() {
		return this.den;
	}
	
	public String toString() {
		return this.num + "/" + this.den;
	}
	
	public Fraction add(Fraction b) {
		Fraction r = new Fraction();  // fracção para o resultado
		r.num = num*b.den + den*b.num;
		r.den = den*b.den;
		return r;
	}
	
	public Fraction subtract(Fraction b) {
		Fraction r = new Fraction();  // fracção para o resultado
		r.num = num*b.den - den*b.num;
		r.den = den*b.den;
		return r;
	}
	
}
