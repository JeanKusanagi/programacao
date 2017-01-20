/*
 * Ex7_2.java
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
public class Ex7_2 {

	static Scanner ler= new Scanner (System.in);
	
	public static void main(String[] args) {
		
		//Inicializa o ponto origem (0,0)
		Ponto2D origem= new Ponto2D();
		origem.x=0;
		origem.y=0;
		
		//Contador dos pontos introduzidos
		int i=0;
		double soma=0;
		double dist=0, maior_dist=0; 
		Ponto2D maior_point=new Ponto2D();

		//Pede ao utilizador pontos até introdução do ponto (0,0) 
		Ponto2D points=new Ponto2D();
		
		do {
			points=read_Point();
			dist=dist_Points(points, origem);
			i++;
			if (dist>maior_dist) {maior_dist=dist; maior_point=points;};
			soma+=dist;
		
		} while ((points.x!=0) || (points.y!=0));
		
		System.out.printf("A soma das distancias dos %d pontos a origem: %f", i, soma);
		System.out.printf("\nO ponto mais afastado da origem foi: (%f, %f)", maior_point.x, maior_point.y);
		

	}

	//Função que lê um ponto
	public static Ponto2D read_Point () {
		
		Ponto2D point = new Ponto2D(); 
		
		System.out.print("Introduza um ponto: ");		//UI (ie User Interface)
		System.out.printf("\nCoordenada x: ");			//UI
		point.x=ler.nextDouble();
		System.out.printf("Coordenada y: ");			//UI
		point.y=ler.nextDouble();
		
		return point;	
	}
	
	//Função que escreve no monitor um ponto 
	public static void print_Point (Ponto2D to_print) {
		System.out.printf("\n(%f,%f)",to_print.x, to_print.y);
	}
	
	//Função que calcula a distãncia entre 2 pontos
	public static double dist_Points (Ponto2D point1, Ponto2D point2) {
		
		double dist=Math.sqrt(Math.pow(point2.x-point1.x, 2)+Math.pow(point2.y-point1.y, 2));
		return dist;
		
	}
	
}

class Ponto2D {
	double x,y;
}
