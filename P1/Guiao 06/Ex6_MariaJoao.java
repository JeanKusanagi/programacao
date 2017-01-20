import java.util.*;

public class Ex6_MariaJoao {
    // constants
    static final Scanner SC = new Scanner(System.in);

	// global variables
	//static int N;
	//static int array[] = new int[N];
	
    // methods
    public static void main(String[] args) {
        System.out.print("Quantidade de numeros que pretende inserir: ");
        int N = SC.nextInt();
        int array[] = new int[N];
        System.out.println("--------------------------------------------------");
        System.out.println("Digite os " + N + " numeros");
        
        for (int i = 0; i < array.length; i++) {
            System.out.print("Numero " + i + ": ");
            array[i] = SC.nextInt();
		}
		
		System.out.println("--------------------------------------------------");
		
		double media=obterMediaSequencia(array, N);
		System.out.println("Media "+ media);
		System.out.println("Desvio padrao "+ obterDesvioPadrao(media, array, N));
		}
	
	
	//média da sequência
	public static double obterMediaSequencia(int[] array, int N){
		double soma = 0;
		for (int j = 0; j < array.length; j++) {
			soma += array[j];
		}
		return soma/N;
	}
	
	//desvio padrao
	public static double obterDesvioPadrao(double media, int[] array, int N){
		double sumVariance=0; //quadrado da diferença entre o numero e a media
		double desvio;
		//double media=obterMediaSequencia();
		for (int j = 0; j < array.length; j++) {
			sumVariance += Math.pow((array[j]-media),2);}
			
			desvio=Math.sqrt(sumVariance/(N-1));
			
		return desvio;
	}
		
		
}

