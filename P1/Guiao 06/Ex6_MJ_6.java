
/*
 * Ex_6_6_MJ.java
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

/*
import java.util.*;

public class Ex_6_6_MJ {
	//constantes
	static Scanner sc = new Scanner(System.in);
	
	
	public static void main (String[] args) {
		System.out.print("Insira uma frase: ");
        String frase = sc.nextLine();
		
		char countingArray[] = new char[frase.length()];  
       
        
      
        boolean iguaisCarateres = frequenciaCarateres(frase.length(), countingArray, frase);
		if (iguaisCarateres=true) {
			System.out.print("A sequencia tem pelo menos um elemento ");
			for (int j = 0; j < frase.length(); j++) 
				System.out.print(countingArray[j]);	
		}
	}
        
     public static boolean frequenciaCarateres(int comprimentoFrase, char[] array, String frase){
		  
		for (int i = 0; i < comprimentoFrase; i++) {
			for (int j = 0; j < comprimentoFrase; j++) {
				if (frase.charAt(i)==frase.charAt(j)) {
					array[i]=frase.charAt(j);
					return true;
					
				}
			}
		}
	 return false;
	}
}
*/
