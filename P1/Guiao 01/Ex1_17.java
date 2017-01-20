import java.util.Scanner;
public class Ex1_17
{	
	public static void main (String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		double valor_produtos, desconto, taxaIVA, total;
		
		System.out.print("Introduza o valor dos produtos, em euros: ");
		valor_produtos=sc.nextDouble();
		
		System.out.print("Introduza a taxa de IVA aplicavel aos produtos, em percentagem: ");
		taxaIVA=sc.nextDouble();
		
		System.out.print("Introduza o valor do desconto, em euros: ");
		desconto=sc.nextDouble();
		
		total=(valor_produtos+valor_produtos*(taxaIVA/100))-desconto;
		System.out.printf("Total Liquido= %4.1f euros\n", total);
	}
}
