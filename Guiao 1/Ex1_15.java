import java.util.Scanner;
public class Ex1_15
{	
	public static void main (String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		double tp1, tp2, api, ep, notafinal;
		String nome;
		
		System.out.print("Introduza o nome e numero mecanografico do aluno de P1: ");
		//Ler String
		nome= sc.nextLine();
		
		System.out.print("Introduza a nota do TP1, em valores: ");
		tp1=sc.nextDouble();
		
		System.out.print("Introduza a nota do TP2, em valores: ");
		tp2=sc.nextDouble();
		
		System.out.print("Introduza a nota de API, em valores: ");
		api=sc.nextDouble();
		
		System.out.print("Introduza a nota de EP, em valores: ");
		ep=sc.nextDouble();
		
		notafinal=tp1*0.15+tp2*0.15+api*0.30+ep*0.40;
		
		//Para uma string, utilizar %s
		System.out.printf("A nota do aluno %s na disciplina de Programacao I e %4.1f valores.\n", nome, notafinal);//nome,notafinal);
	}
}
