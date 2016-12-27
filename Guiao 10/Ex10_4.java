/*
 * Ex10_4.java
 * 
 * Copyright 2016 Pedro <Pedro@UA>
 * MIECT - DETI UA
 */

import java.io.*;
import java.util.Scanner;

public class Ex10_4 {
	
	static Scanner read = new Scanner (System.in);
	
	public static void main (String[] args) throws IOException {
		//UI
		System.out.printf("Procurar nomes da turma\n---------------------------------------------------------------------------");
		System.out.printf("\n<0 termina>");
		
		//Inicialização das variáveis
		File in=getFile();
		int num_alunos=getFileCount(in);
		Turma turma=readData_File(in, num_alunos);
		
		//Pede números para procurar
		int num=1;
		do {
			System.out.printf("\nIntroduza o número mecanográfico do aluno que pretende procurar: ");
			num=read.nextInt();
			String name=searchName(turma, num);
			System.out.printf("Nome correspondente ao número %d: \n%s\n", num, name);
		} while (num!=0);
		
		//Termina o programa
		System.exit(0);
		
	}
	
	//Obter ficheiro
	public static File getFile () throws IOException {
		String path;
		File file;

		do{
			System.out.print("\nPor favor introduza o nome de um ficheiro de texto válido (com extensão): ");
			while(!read.hasNext());
			path = read.nextLine();
			file = new File(path);
		}while(!file.isFile() || !file.canRead());
		
		return file;
		
	}
	
	//Contar o número de amostras válidas (número de linhas) gravadas no ficheiro
	public static int getFileCount (File file) throws IOException {
		Scanner in_count = new Scanner (file);
		int count=0;
		while(in_count.hasNextLine()) {
			in_count.nextLine();
			count++;
		}

		in_count.close();

		return count;
	}
	
	//Obter dados do ficheiro (copiar os valores inteiros para uma classe turma)
	public static Turma readData_File (File file, int DIM) throws IOException {		
		//Inicialização das variáveis
		Turma data = new Turma();
		data.num = new int [DIM];
		data.nome = new String [DIM];
		
		Scanner in = new Scanner (file);
		
//		String a[] = new String [DIM];   	//Working (método alternativo ao uso de classe)
//		int b[] = new int [DIM];			
		
		while(in.hasNextLine()) {
			for (int i=0; i<DIM; i++) {
				data.num[i]=in.nextInt();
				data.nome[i]=in.nextLine();
//				a[i]=in.nextLine();    //Working
//				b[i]=in.nextInt();
			}
		}
		
		in.close();
				
		return data;
	}
	
	//Pesquisa
	public static String searchName (Turma alunos, int wanted_num) {
		String name="";
		int n=0;
		
		do {
			if(alunos.num[n] == wanted_num) {			//Percorrendo a array, se encontrar o número pretendido na posição n 
				name=alunos.nome[n];					//O nome pretendido será o que estiver na posição n da array nome
			}
			n++;
		} while(n<alunos.num.length);
		
		if (name.equals("")) name="Nome não encontrado"; 	//Se não for encontrado um nome, dá uma mensagem
		return name;
	}
}

class Turma {
	int[] num;
	String[] nome;
}