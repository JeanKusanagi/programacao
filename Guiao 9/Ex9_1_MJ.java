import java.util.*;
import java.io.*;

public class Ex9_1_MJ {
	static Scanner kb = new Scanner(System.in);
	public static void main (String[] args) throws IOException{ 
		String nameIn;
		System.out.print("Ficheiro de entrada: ");
		nameIn = kb.nextLine();

		File fin = new File(nameIn);
		
		if (verify(fin)){
			Scanner read_from_file = new Scanner(fin);
			while(read_from_file.hasNextLine())
				System.out.println(read_from_file.nextLine());
				read_from_file.close(); 
			}
		}
	
	// verificacoes do ficheiro de entrada
	public static boolean verify(File nameIn){ 
		if (!nameIn.exists()) {
			System.out.println("ERROR: input file " + nameIn + " does not exist!");
			return false;
		}
		if (nameIn.isDirectory()) {
			System.out.println("ERROR: input file " + nameIn + " is a directory!");
			return false;
		}
		if (!nameIn.canRead()) {
			System.out.println("ERROR: cannot read from input file " + nameIn+ "!");
			return false;
		}
	return true;
} 

}
