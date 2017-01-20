import java.util.Scanner;
public class Ex1_10
{	
	public static void main (String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		double tempC, tempF;
		
		System.out.print("Introduza a temperatura em Celsius: ");
		tempC=sc.nextDouble();
		
		tempF=1.8*tempC+32;
		System.out.printf("%4.1f Celsius e equivalente a %4.1f Fahrenheit.\n", tempC,tempF);
	}
}
