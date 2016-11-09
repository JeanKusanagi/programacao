//Math.pow to ^2
//package com.mkyong.test;

import java.util.Scanner;

public class Ex1_14
{	
	public static void main (String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		double catA, catB, hipC, coss, ang_Rad, ang_Deg;
		
		System.out.print("Introduza o valor dos catetos A e B, por ordem: ");
		catA=sc.nextDouble();
		catB=sc.nextDouble();
		
		//Math Class!!
		hipC= Math.sqrt(Math.pow(catA,2)+Math.pow(catB,2));
		coss= catA/hipC;
		ang_Rad=Math.acos(coss);
		ang_Deg=Math.toDegrees(ang_Rad);
		
		System.out.printf("A hipotenusa do triangulo rectangulo considerado e%4.1f uc.\n", hipC);
		System.out.printf("O angulo entre a hipotenusa e o cateto A e %4.1f graus.\n", ang_Deg);
	}
}
