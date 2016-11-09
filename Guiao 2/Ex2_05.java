/*
 * Ex5.java
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
public class Ex2_05 {
	
	public static void main (String[] args) {
		Scanner ler = new Scanner(System.in);
				
		double x1,x2,x3,x4,y1,y2,y3,y4,c_x,c_y,distAB,distBC,distCD,distDA,distRA,distRB,distRC,distRD;
		
		System.out.print("Introduza as coordenadas do ponto A (x,y): ");
		x1=ler.nextDouble();
		y1=ler.nextDouble();
		
		System.out.print("Introduza as coordenadas do ponto 2 (x,y): ");
		x2=ler.nextDouble();
		y2=ler.nextDouble();
		
		System.out.print("Introduza as coordenadas do ponto 3 (x,y): ");
		x3=ler.nextDouble();
		y3=ler.nextDouble();
		
		System.out.print("Introduza as coordenadas do ponto 4 (x,y): ");
		x4=ler.nextDouble();
		y4=ler.nextDouble();
		
		//Coordenadas do centro do quadrado
		c_x=(x1+x2+x3+x4)/4;
		c_y=(y1+y2+y3+y4)/4;
		
		//Distâncias entre um vértice X e Y
		distAB=(Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2)));
		distBC=(Math.sqrt(Math.pow(x3-x2,2)+Math.pow(y3-y2,2)));
		distCD=(Math.sqrt(Math.pow(x4-x3,2)+Math.pow(y4-y3,2)));
		distDA=(Math.sqrt(Math.pow(x4-x1,2)+Math.pow(y4-y1,2)));
		
		//Distâncias entre cada um dos vértices e o centro R
		distRA=(Math.sqrt(Math.pow(x1-c_x,2)+Math.pow(y1-c_y,2)));
		distRB=(Math.sqrt(Math.pow(x2-c_x,2)+Math.pow(y2-c_y,2)));
		distRC=(Math.sqrt(Math.pow(x3-c_x,2)+Math.pow(y3-c_y,2)));
		distRD=(Math.sqrt(Math.pow(x4-c_x,2)+Math.pow(y4-c_y,2)));
		
		//Debug
		//System.out.printf("%f, %f, %f, %f, %f, %f, %f, %f",distAB,distBC,distCD,distDA,distRA,distRB,distRC,distRD);
		
		//Será quadrado sse as arestas forem iguais (distâncias entre os vértices) e a distância entre cada um dos vértices e o centro for igual, qualquer que seja o vértice
		if ((distRA==distRB) && (distRB==distRC) && (distRC==distRD) && (distAB==distBC) && (distBC==distCD) && (distCD==distDA)) 
		{System.out.println("Trata-se de um quadrado.");}
		else {System.out.println("Nao se trata de um quadrado.");}
		
	}
}

