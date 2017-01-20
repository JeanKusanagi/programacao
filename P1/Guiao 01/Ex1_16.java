import java.util.Scanner;
public class Ex1_16
{	
	public static void main (String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		double despesa, despesafinal, dia;
		
		System.out.print("Introduza a despesa do primeiro dia, em euros: ");
		despesa=sc.nextDouble();
		
		/*for (int i=1;i<4; i=i+1) -----i<=3
		{
			despesa=despesa+0.2*despesa;  -----FALTA UMA VARIAVEL 
			
		}
		*/
		
		/*do
		{
			despesa=despesa+0.2*despesa;
			dia=dia+1;
		} while (dia<=4);
		*/
		
		
		//dia<=3, sendo dia=0 no inicio, significa repetir o ciclo for QUATRO vezes
		//Partindo de despesafinal=despesa, basta somar 20% da despesa a despesa final, quatro vezes
		
		for (dia=0, despesafinal=despesa ; dia<=3; dia++)
		{
			despesafinal=despesafinal+0.2*despesa;
		}
		
		System.out.printf("A despesa final do turista, ao fim de 4 dias, e %4.1f.\n", despesafinal);
		//System.out.print(+dia);
		
	}
}
