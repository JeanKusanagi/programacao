/**
 * E4
 * 
 * Implemente uma função recursiva factors que recebendo um numero inteiro como argumento devolve uma String com o
 * produto dos seus factores.
 * Por exemplo, a invocação do programa:
 * java -ea Factors 0 1 10 4 10002
 * deve ter como resultado:
 * 0 = 0
 * 1 = 1
 * 10 = 2 * 5
 * 4 = 2 * 2
 * 10002 = 2 * 3 * 1667
 * 
 * @author Pedro Teixeira
 * Copyright 2017, MIECT - DETI UA
 */

package e04;
import java.util.Scanner;
public class E4 {
	
	public static void main(String[] args){
		Scanner read = new Scanner (System.in); 

		// Get int(s)
		@SuppressWarnings("unused")
		int num;

		// From args
		if (args.length < 1) {
			System.out.println("Please insert the number: ");
			num = read.nextInt(); 
			//System.out.printf("\n'%s' -> '%s'", num, factor(num));
		}
		// Or from console
		else {
			for (@SuppressWarnings("unused") String i : args) {
				//System.out.println(i + " -> " + factor(Integer.parseInt(i)));
			}
		}
	}
	
	//public String factor (int argsInt) {
	//	String f = "";
		
		
	//}
}
