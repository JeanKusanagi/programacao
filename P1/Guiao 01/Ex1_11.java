import java.util.Scanner;
public class Ex1_11
{	
	public static void main (String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		double qtDol, taxa, qtEuro;
		
		System.out.print("Introduza a quantia em dolares: ");
		qtDol=sc.nextDouble();
		
		System.out.print("Introduza a taxa de conversao: ");
		taxa=sc.nextDouble();
		
		qtEuro=qtDol*taxa;
		System.out.printf("%4.1f dolares equivalem a %4.1f euros.\n", qtDol, qtEuro);
	}
}
