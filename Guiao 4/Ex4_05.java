/*
 * Ex4_05.java
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

public class Ex4_05 {
	
	public static void main (String[] args) {
		
		double a=0,m=0,n=0,b=0,c=0;
		
		System.out.printf("Ternos pitagoricos ate a, b <100\nDado um triangulo retangulo seja a o cateto menor, seja b o outro cateto, seja c a hipotenusa.\n");
		
		n=2*(c-b);
		 m=(a+b-c)/(2*(c-b));
		 a = n*(2*m + 1);
		 b = n*(2*Math.pow(m,2)+2*m);
		 c = n*(2*Math.pow(m,2)+2*m+1);
		 System.out.printf("(a,b,c)=%f, %f, %f", a,b,c);
		 
		while ((a<100) && (b<100) && (a<b))
		{
		n=2*(c-b);
		 m=(a+b-c)/(2*(c-b));
		 a = n*(2*m + 1);
		 b = n*(2*Math.pow(m,2)+2*m);
		 c = n*(2*Math.pow(m,2)+2*m+1);
		 System.out.printf("(a,b,c)=%f, %f, %f", a,b,c);
		}
	}
}


