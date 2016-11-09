import java.util.Scanner;
//import javax.swing.JOptionPane;

public class Ex1_13
{	
	/* Tentativa subprograma
	public static void calculo (String[] args)
	{
		double dist;
		
	}
	*/
	
	public static void main (String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		double x1,y1,x2,y2, factor, dist;
		
		System.out.print("Introduza as coordenadas cartesianas da localidade A, em centimetros: ");
		x1=sc.nextDouble();
		y1=sc.nextDouble();
		
		System.out.print("Introduza as coordenadas cartesianas da localidade B, em centimetros: ");
		x2=sc.nextDouble();
		y2=sc.nextDouble();
		
		/* É obrigatório o utilizador introduzir o factor
		System.out.print("OPCIONAL: Introduza o factor de escala para obter uma distancia em km\n(caso tenha introduzido as coordenadas cartesianas em centimetros introduza 100): ");
		factor=sc.nextDouble();
		dist=(Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2)))*factor;
		System.out.printf("A distancia, em linha reta, entre a localidade A e B e %4.1f km.\n", dist);
		*/
		
		// Permite um valor predefinido, sem introdução da parte do utilizador
		
		//Não funciona
		System.out.printf("OPCIONAL: Pretende introduzir manualmente um factor de escala. Responda Sim apenas caso nao tenha introduzido as coordenadas cartesianas em centimetros. Caso contrario, responda Nao.\n");
		/*String input = System.console().readLine();*/
		String input = sc.next();
		
		/*
		String input = JOptionPane.showInputDialog(null,"OPCIONAL: Pretende introduzir manualmente um factor de escala. Responda Sim apenas caso nao tenha introduzido as coordenadas cartesianas em centimetros. Caso contrario, responda Nao.");
		*/
		
		if (input.equals("Sim"))
			{
				System.out.print("Introduza o factor de escala para obter uma distancia em km: ");
				factor=sc.nextDouble();
				dist=(Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2)))*factor;
				System.out.printf("A distancia, em linha reta, entre a localidade A e B e %4.1f km.\n", dist);
			}
		else if (input.equals("Nao"))
			{
				factor=100;
				dist=(Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2)))*factor;
				System.out.printf("A distancia, em linha reta, entre a localidade A e B e %4.1f km.\n", dist);
			}
	}
}
