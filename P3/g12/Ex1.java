package g12;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import g04.figures.Circulo;
import g12.ex1.ClassInfo;

/**
 * Ex1
 * 
 * @author Pedro Teixeira
 */

public class Ex1 {

	public static void main(String[] args) throws Exception {
		// For testing purposes
		// test();

		Scanner sc = new Scanner(System.in);

		System.out.println("Class Info\n--------------------------------------\n");
		System.err.println("\nProgram is opening...");

		System.out.print("Test the program or run the program? (T/R) -> ");		
		String op = sc.nextLine();
		if (op.equalsIgnoreCase("t")) {
			test();
		}

		ClassInfo info = null;
		String name = "";
		try {
			System.out.print("Please enter the name of class you want to read -> ");
			name = sc.nextLine();
			info = new ClassInfo(name);
		} catch (ClassNotFoundException e) {
			System.err.println("\nClass Not Found");
		}

		System.out.println("\nClass Info: \n" + info);

		// Instanciate an object
		System.out.print("\nDo you want to create an object of this class? (Y/N) -> ");		
		op = sc.nextLine().toLowerCase();
		Object obj = null;
		switch (op){
			case "n":
				System.exit(1);
				break;
			case "y":
				obj = ClassInfo.newInstance(name);
				break;
		}

		System.out.println("\nCreated " + obj);

		// Invoking a method
		System.out.print("\nDo you want to invoke a method on the created object? (Y/N) -> ");		
		op = sc.nextLine().toLowerCase();
		Object result = null;
		switch (op){
			case "n":
				System.exit(2);
				break;
			case "y":
				result = ClassInfo.invokeMethod(name, obj);
				break;
		}

		System.out.println("\nReturn value of the invoked method: " + result);

		sc.close();
		System.err.println("\nProgram is closing...");
		System.exit(0);

	}

	public static void test() throws ClassNotFoundException {
		List<ClassInfo> list = new LinkedList<>();

		/*
		list.add(new ClassInfo("aula06.prob1.FishType"));
		list.add(new ClassInfo("aula05.prob3_oopOriented.FileOperations"));
		list.add(new ClassInfo("aula06.prob1.Meal"));
		list.add(new ClassInfo("aula11.prob2.Ponto"));
		list.add(new ClassInfo("aula05.prob1.Circulo"));
		list.add(new ClassInfo("utils.DynamicArray"));
		 */

		list.add(new ClassInfo("g12.Test"));
		list.forEach(c -> System.out.println(c + "\n------------------\n"));

		// Alínea a
		try {
			Circulo circle = (Circulo) ClassInfo.newInstance("g04.figures.Circulo");
			System.out.println(circle);
			//Circulo circle = new Circulo(5, 4, 3);
			System.out.println(ClassInfo.invokeMethod("g04.figures.Circulo", circle));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
