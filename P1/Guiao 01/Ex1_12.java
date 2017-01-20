import java.util.Scanner;
public class Ex1_12
{	
	public static void main (String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		double tempo_sec, horas, minutos, minutos_temp, segundos;
		
		System.out.print("Introduza o tempo em segundos: ");
		tempo_sec=sc.nextDouble();
		
		horas=tempo_sec/3600;
		minutos_temp=tempo_sec%3600;
		minutos=minutos_temp/60;
		segundos=minutos_temp%60;
		System.out.printf("%4.1f segundos equivalem a %4.0f horas %4.0f minutos e %4.0f segundos.\n", tempo_sec, horas, minutos, segundos);
	}
}
