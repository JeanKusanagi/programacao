/*
 * Ex12_2.java
 * 
 * Copyright 2017 Pedro <Pedro@UA>
 * MIECT - DETI UA
 */

import java.util.Scanner;
public class Ex12_2 {
	
	static Scanner read = new Scanner (System.in);
	public static void main(String[] args) {
		//Dimensão array (debug)
		int DIM=10;
		Piloto[] pilotos = new Piloto [DIM];
		
		//UI
		System.out.printf("Gestão de uma prova automóvel:\n");
		System.out.printf("\n 1 - Inserir informação dos pilotos");
		System.out.printf("\n 2 - Listar pilotos ordenados pelo número da viatura");
		System.out.printf("\n 3 - Alterar informação de um piloto");
		System.out.printf("\n 4 - Remover piloto com base no número da viatura");
		System.out.printf("\n 5 - Listar pilotos ordenados pelo nome");
		System.out.printf("\n 6 - Remover piloto(s) com base no nome");
		System.out.printf("\n 7 - Validar matriculas");
		System.out.printf("\n 8 - Terminar o programa");
		
		int option=0;
		do {
			System.out.printf("\n\nOpção -> ");
			option=read.nextInt();
			switch (option) {
				case 1:
					pilotos=readInfo(DIM);
					break;
				case 2:
					printInfo1(pilotos);
					break;
				case 3:
					changeInfo(pilotos);
					break;
				case 4:
					deleteInfo1(pilotos);
					break;
				case 5:
					printInfo2(pilotos);
					break;
				case 6:
					deleteInfo2(pilotos);
					break;
				case 7:
					verifyIds(pilotos);
					break;
				case 8:
					break;
				default:
					System.out.printf("Opção inválida.");
					break;
			}
		} while (option!=8);
		
		System.out.printf("\nPrograma fechado.");
		System.exit(0);
	}
	
	//Inserir informação dos pilotos : missing validation w/empty name
	public static Piloto[] readInfo (int DIM) {
		Piloto[] pilotos = new Piloto [DIM];
	
		for (int i=0; i<pilotos.length; i++) {
			//Inicialização
			pilotos[i]= new Piloto();

			//Atribuição informação
			String name="";
			
			System.out.printf("\nNome do piloto da viatura %d: ", i+1);
			while (name.equals("")) {
				name=read.nextLine();
				//if(name.isEmpty()) break;		//Termina com a introdução de um nome vazio
				pilotos[i].name=name;
			}
			
			pilotos[i].num=i+1;

			System.out.printf("Matrícula da viatura %d: ", i+1);
			String id=read.nextLine();
			pilotos[i].id=id;

		}

		return pilotos;
	}
	
	//Mostrar a informação dos pilotos (1 - ordenado pelo número, 2 - ordenado pelo nome): OK
	public static void printInfo1 (Piloto[] array) {
		for (int i=array.length-1; i>=0; i--) {
			if (array[i]!=null) System.out.printf("\nViatura %2d | %s (%s)", array[i].num, array[i].id, array[i].name);
		}
	}
	
	public static void printInfo2 (Piloto[] array) {	
		//Ordenação por flutuação
		boolean switches;
		do {
			switches = false;
			for (int i = 0; i < array.length-1; i++) {
				if (Character.getNumericValue(array[i].name.charAt(0)) < Character.getNumericValue(array[i+1].name.charAt(0))) {
					Piloto tmp=array[i];
					array[i] = array[i+1];
					array[i+1] = tmp;
					switches = true;
				}
			}
		} while (switches);
		
		printInfo1(array);
	}
	
	//Alterar informação sobre um piloto : OK 
	public static Piloto[] changeInfo (Piloto[] array) {
		//Ler número da viatura a alterar
		System.out.printf("Introduza o número da viatura que pretende alterar: ");
		int num=read.nextInt();
		
		//Pesquisa binária
		int i = 0;
		int pos = -1; 
		do {
			if(array[i++].num == num) {
				pos = i-1;
			}
		} while(pos == -1 && i < array.length);
		
		if (pos == -1)  		//Se não existir viatura
			System.out.printf("Viatura não existente\nInformações não foram alteradas.");
		
		else if (pos != -1) {	//Se existir viatura 
			//Alterar informação da string no índice pos
			System.out.printf("Novo nome do piloto da viatura %d: ", num);
			String name=read.next();
			array[pos].name=name;
	
			System.out.printf("Nova matrícula da viatura %d: ", num);
			String id=read.next();
			array[pos].id=id;
			
			System.out.printf("\nInformações foram alteradas.");
		}
		
		return array;
	}
	
	//Remover um piloto (1 - com base no número i : OK, 2 - com base no nome : OK)
	public static Piloto[] deleteInfo1 (Piloto[] array) {
		//Ler número da viatura a remover
		System.out.printf("Introduza o número da viatura que pretende remover: ");
		int num=read.nextInt();

		//Pesquisa binária
		int i = 0;
		int pos = -1; 
		do {
			if(array[i++].num == num) {
				pos = i-1;
			}
		} while(pos == -1 && i < array.length);

		if (pos == -1)  		//Se não existir viatura
			System.out.printf("Viatura não existente");

		else if (pos != -1) {	//Se existir viatura 
			for (int j=pos; j<array.length; j++) {
				array[j]=array[j+1]; //"Puxa" os restantes valores da array 1 índice para baixo
				array[j+1]=null;
				if (j+1==array.length-1) break;
			}
			System.out.printf("\nInformações foram removidas com sucesso.");
		}
		
		return array;
	}
	
	public static Piloto[] deleteInfo2 (Piloto[] array) {
		/*
		 * Remover um ou vários pilotos com base no nome, parcial ou completo, pedido ao
		 * utilizador. Sugere-se a utilização da função indexOf da classe String. No caso de
		 * haver mais do que um piloto que respeite a condição, deve ser perguntado ao
		 * utilizador qual ou quais os pilotos a remover.
		 */
		
		int z=0;
		int del=0;
		int []pos=new int[10];
		
		//Ler nome do piloto a remover
		System.out.printf("Introduza o nome (ou parte) do piloto que pretende remover: ");
		String name=read.next();
		
		//Procura o nome
		for(int i = 0 ; i < array.length; i++){
			//Se a string name existir, na posição i da array, ele marca essa posição na array pos 
			if(array[i].name.indexOf(name)!=-1){
				pos[z] = i;
				z++;
			}
		}
		
		//Determina se existe 1 ou + pilotos com o mesmo nome 
		int k=0;
		for(k = 0 ; k<10 ; k++){
			if(pos[k]==0 && k!=0) break;
		}
		
		if(k==1) del=0;
		
		else{
			System.out.print("Selecione o piloto que deseja remover: ");
			for(int i=0; i<k; i++){
				System.out.print("\n"+(i+1)+": "+array[pos[i]].name);
			}
			System.out.print("\nOpção (Número):" );
			del=(read.nextInt()-1);
		}

		int i=pos[del];
		for(; i < array.length; i++){
			array[i]=array[i+1];	//"Puxa" os restantes valores da array 1 índice para baixo
			array[i+1]=null;
			if (i+1==array.length-1) break;
		}

		System.out.print("\nPiloto removido com êxito");
			
		return array;
	}
	
	//Validar matrículas : OK
	public static void verifyIds (Piloto[] array) {
		for (int i=0; i<array.length; i++) {
			
			boolean match1=matchPattern(array[i].id, "00-00-AA");                 
			boolean match2=matchPattern(array[i].id, "00-AA-00");
			boolean match3=matchPattern(array[i].id, "AA-00-00");
			
			//Em caso de matrícula inválida, pedir ao utilizador nova matrícula para o piloto em causa
			if (!match1 && !match2 && !match3) {
				System.out.printf("\nMatrícula inválida. Por favor introduza uma matrícula válida para o piloto %s (viatura %d): ", array[i].name, array[i].num);
				String id=read.next();
				array[i].id=id;
				System.out.printf("\nMatrícula alterada com êxito.");
			}
		}
		
		System.out.printf("\nMatrículas verificadas com êxito.");
	}
	
	public static boolean matchPattern (String str, String pattern) {
		boolean match=true; 
		
		//Se a String introduzida for de dimensões diferentes do padrão, não concide com este
		if(pattern.length()!=str.length()) match=false; 
		
		for(int i=0; i<str.length(); i++){                        
			//Quando se tem uma letra maíscula na posição i do padrão
			if (pattern.charAt(i)=='A'){                                  
				//Se não se tiver uma letra maíuscula, não coincide com o padrão
				if(!Character.isUpperCase((str.charAt(i)))) match=false; 
			} 
			
			//Quando se tem um dígito na posição i do padrão
			else if (pattern.charAt(i)=='0'){ 
				//Se não se tiver um dígito, não coincide com o padrão
				if(!Character.isDigit((str.charAt(i)))) match=false; 
			} 
			
			//Quando se tem um - na posição i do padrão
			else if (pattern.charAt(i)=='-'){ 
				//Se não se tiver um -, não coincide com o padrão
				if(str.charAt(i)!='-') match=false; 
			} 
			
			else{ 
				match=false; 
				break; 
			} 
		
		} 
		return match;
	}
	
}

class Piloto {
	int num;		//número da viatura
	String name;	//nome do piloto
	String id;		//matrícula da viatura
}
