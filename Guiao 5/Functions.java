/**
 * Function - Define several functions, and test them.
 * 
 * Nesta classe deve definir novas funções e testá-las na função main.
 * 
 * 
 **/
import java.util.Scanner;
public class Functions {
	
	public static void main (String args[]) {
		
		System.out.printf("Exercicio 1 e 2 do Guiao 5 de P1\n \n");
		// Invoque as funções pedidas no enunciado para as testar:
		
		// Exercício 1
		System.out.printf("sqr(%f) = %f\n", 10.1, sqr(10.1));
		System.out.printf("sqr(%f) = %f\n", -2.0, sqr(-2.0));
		System.out.printf("sqr(%f) = %f\n", 100.0, sqr(100.0));
		System.out.printf("sqr((sqr(%f)) = %f\n", 10.0, sqr(sqr(10.0)));
	
		// Exercício 2a)
		System.out.printf("\nf(%d) = %f\n", 5, f(5));
		System.out.printf("f(%d) = %f\n", 8, f(8));
		System.out.printf("f(%d) = %f\n", 9, f(9));

		// Testar as restantes funções desenvolvidas
		// Exercício 2b)
		System.out.printf("\nmax(%f,%f) = %f\n", 3.1, 5.2, max(3.1, 5.2));
		System.out.printf("max(%f,%f) = %f\n", 5.2, 3.1, max(5.2, 3.1));
		
		// Exercício 2c)
		System.out.printf("\nmax(%d,%d) = %d\n", 3, 5, max(3, 5));
		System.out.printf("max(%d,%s) = %d\n", 3, "b", max(3, 'b'));
		
		// Exercício 2d)
		System.out.printf("\nPoly (a,b,c,d,x)=(%f,%f,%f,%f,%f)=%f\n", 1.0, 2.0, 3.0, 4.0, 5.0, poly3(1.0,2.0,3.0,4.0,5.0));
		System.out.printf("Poly (a,b,c,d,x)=(%f,%f,%f,%f,%f)=%f\n", 5.3, 2.1, 5.8, 1.3, 2.1, poly3(5.3, 2.1, 5.8, 1.3, 2.1));
		System.out.printf("Poly (a,b,c,d,x)=(%f,%f,%f,%f,%f)=%f\n", 0.0, 0.0, 0.0, 1.0, 0.0, poly3(0.0, 0.0, 0.0, 1.0, 0.0));
		
		// Exercício 2e)
		System.out.printf("\n%d!=%d\n", 5, fact (5));
		System.out.printf("%d!=%d\n", 6, fact (6));
		System.out.printf("%d!=%d\n", 9, fact (9));
		
		// Exercício 2f)
		//int ano = 
		getIntPos("Ano?");
		//System.out.printf("ano = %d\n", ano);
		
		// Exercício 2g)
		System.out.printf("\n\n");
		getIntRange("Mes?",12, 1);
		getIntRange("Mes?",1, 12);
		
		// Exercício 2h)
		System.out.printf("\n\n");
		printNtimes(3,"Ola");
		printNtimes(5,"P1");
	}
	
	// Defina as funções pedidas no enunciado:
	
	//Exercício 1: Quadrado de um número
	public static double sqr(double x) {
		double y;	// variavel para resultado
		y = x*x;	// calculo do resultado a partir dos dados
		return y;	// devolver o resultado
	}

	//Exercício 2a)
	public static double f (double n) 
	{
		double y;
		y=1/(1+n*n);
		return y;
	} 
	
	//Exercício 2b)
	public static double max (double n, double y)
	{
		double maior=0;
		if (n>y) {maior=n;}
		else if (n<y) {maior=y;}
		return maior;
	}
	
	//Exercício 2c)
	public static int max (int n, int y)
	{
		int maior=0;
		if (n>y) {maior=n;}
		else if (n<y) {maior=y;}
		return maior;
	}
	
	//Exercício 2d)
	public static double poly3 (double a, double b, double c, double d, double x)
	{
		double polinomio;
		polinomio=a*Math.pow(x,3)+b*Math.pow(x,2)+c*x+d;
		return polinomio;
	}
	
	//Exercício 2e)
	public static int fact (int n)
	{
		int factorial=1;
		for (int i=1; i<=n; i++)
		{factorial=factorial*i;}
		return factorial;
	}
	
	//Exercício 2f)
	public static int getIntPos (String message)
	{
		Scanner ler = new Scanner(System.in);
		System.out.printf("%s",message);
		int n=ler.nextInt();
		while (n<0) 
		{System.out.printf("\nTem de introduzir um numero inteiro POSITIVO: ");
		n=ler.nextInt();}
		return n;
	}
	
	//Variante do Exercício 2f)
	public static int getIntLim_Min (String message, int lim_min)
	{
		Scanner ler = new Scanner(System.in);
		System.out.printf("%s",message);
		int n=ler.nextInt();
		while (n<lim_min) 
		{System.out.printf("\nTem de introduzir um numero inteiro maior do que %d: ", lim_min);
		n=ler.nextInt();}
		return n;
	}
	
	public static int getIntLim_Max (String message, int lim_max)
	{
		Scanner ler = new Scanner(System.in);
		System.out.printf("%s",message);
		int n=ler.nextInt();
		while (n>lim_max) 
		{System.out.printf("\nTem de introduzir um numero inteiro menor do que %d: ", lim_max);
		n=ler.nextInt();}
		return n;
	}
	
	
	//Exercício 2g)
	public static int getIntRange (String message, int lim1, int lim2)
	{
		Scanner ler = new Scanner(System.in);
		int lim_superior=0, lim_inferior=0, n=0;
		
		if (lim1>lim2) {lim_superior=lim1; lim_inferior=lim2;}
		else if (lim1<lim2) {lim_superior=lim2; lim_inferior=lim1;}
		
		System.out.printf("%s", message);
		n=ler.nextInt();
		while ((n>lim_superior) || (n<lim_inferior))
		{System.out.printf("\nTem de introduzir %s pertencente ao intervalo [%d, %d] ", message, lim_inferior, lim_superior);
		n=ler.nextInt();
		}
		return n;
	}
	
	//Exercício 2h)
	public static void printNtimes (int vezes, String message)
	{
		for (int i=1; i<=vezes; i++)
		{
			System.out.printf("%s",message);
		}
	}
	}
