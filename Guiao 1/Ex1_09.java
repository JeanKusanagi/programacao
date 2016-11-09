import java.util.Scanner;
public class Ex1_09
{	
	public static void main (String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		double ladoA;
		double ladoB;
		double perimetro;
		double area;
		
		System.out.print("Introduza a largura do rectangulo: ");
		ladoA=sc.nextDouble();
		
		System.out.print("Introduza o comprimento do rectangulo: ");
		ladoB=sc.nextDouble();
		
		perimetro=(ladoA*2)+(ladoB*2);
		area=ladoA*ladoB;
		
		System.out.println(" ");
		System.out.printf("O perimetro e a area do rectangulo dado sao, respectivamente, %4.1f uc e %4.1f ua.\n", perimetro, area);
	}
}
