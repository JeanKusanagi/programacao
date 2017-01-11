/*
 * Ex12_3.java
 * 
 * Copyright 2017 Pedro <Pedro@UA>
 * MIECT - DETI UA
 */

import java.io.*;
import java.util.Scanner;
public class Ex12_3 {
	
	static Scanner read = new Scanner (System.in);
	
	public static void main (String[] args) throws IOException {
		//Array
		Robot[] robots = new Robot [0];
		
		//UI
		System.out.printf("Micro-Rato 2013 - Gestão da prova:");
		System.out.printf("\n 1 - Adicionar informação relativa a um robô");
		System.out.printf("\n 2 - Imprimir informação dos robôs em prova");
		System.out.printf("\n 3 - Vencedor da prova e tempos médios de prova");
		System.out.printf("\n 4 - Média do número de elementos por equipa");
		System.out.printf("\n 5 - Mostrar o nome dos robôs em maiúsculas");
		System.out.printf("\n 6 - Alterar informação de um robô");
		System.out.printf("\n 7 - Remover robôs da competição");
		System.out.printf("\n 8 - Gravar informação da prova num ficheiro");
		System.out.printf("\n 9 - Terminar o programa");
		
		//Ler opção
		int option=0;
		
		do {
			System.out.printf("\n\nOpção -> ");
			option = read.nextInt();
			switch (option) {
				case 1:
					robots=addInfo(robots);
					break;
				case 2:
					printInfo(robots);
					break;
				case 3:
					showMoreInfo(robots);
					break;
				case 4:
					printMean(robots);
					break;
				case 5:
					printInfo2(robots);
					break;
				case 6:
					changeInfo(robots);
					break;
				case 7:
					removeInfo(robots);
					break;
				case 8:
					saveInfo(robots);
					break;
				case 9:
					break;
				default:
					System.out.printf("Opção inválida.");
					break;
			}
		} while (option!=9);
		
		//Terminar programa
		System.out.printf("Programa terminado.");
		System.exit(0);
		
	}
	
	//Adicionar informação (mantém os valores anterores da array e adiciona o novo valor) : OK
	public static Robot[] addInfo (Robot[] array) {
		int dim=array.length+1;
		Robot[] robots = new Robot [dim];
		
		//Copiar os valores para a nova array
		for (int i=0; i<array.length; i++) {
			robots[i] = new Robot();
			robots[i] = array[i];
		}
		
		//-------------------------------------------------
		System.out.printf("\nCriar novo registo");

		//Inicialização
		robots[array.length]= new Robot();

		//Leitura informação
		String name="";

		System.out.printf("\nNome do robô %d: ", array.length+1);
		while (name.equals("")) {
			name=read.nextLine();
			robots[array.length].name=name;
		}

		System.out.printf("Tempo de prova em segundos: ");
		int time=read.nextInt();
		robots[array.length].time=time;

		System.out.printf("Número de elementos da equipa: ");
		int num=read.nextInt();
		robots[array.length].num=num;


		return robots;
	}
	
	//Imprimir informação : OK
	public static void printInfo (Robot[] array) {
		if (array.length==0) System.out.printf("Sem informação.");
		for (int i=0; i<array.length; i++) {
			if (array[i]!=null) System.out.printf("\nRobô %s (equipa de %d elementos) | Tempo: %s", array[i].name, array[i].num, convertTime(array[i].time));	
		}
	}
	
	//Nome dos robos em maisculas por ordem crescente : OK
	public static void printInfo2 (Robot[] array) {
		if (array.length==0) System.out.printf("Sem informação.");
		orderValues(array,1);
		for (int i=0; i<array.length; i++) {
			if (array[i]!=null) System.out.printf("\nRobô %s (equipa de %d elementos) | Tempo: %s", capitalizeName(array[i].name), array[i].num, convertTime(array[i].time));	
		}
	}

	//Robo vencedor (menor tempo) Tempo médio (soma e depois conversão) : OK
	public static void showMoreInfo (Robot[] array) {
		//Robô vencedor
		orderValues(array,0);

		//A menor temperatura estará no primeiro elemento da array agora ordenada
		System.out.printf("\nVencedor: robô %s (equipa de %d elementos) | Tempo: %s", array[0].name, array[0].num, convertTime(array[0].time));
		
		//--------------------------------------------------------------
		//Calcular média
		int sum=0;
		for (int i=0; i<array.length; i++) {
			sum=sum+array[i].time;		
		}
		int mean= (int) sum/array.length;
		
		System.out.printf("\nTempo médio: %s ", convertTime(mean));
	}
	
	//Calcular média número elementos
	public static void printMean (Robot[] array) {
		int sum=0;
		for (int i=0; i<array.length; i++) {
			sum=sum+array[i].num;		
		}
		int mean= (int) sum/array.length;
		
		System.out.printf("\nMédia do número de elementos por equipa: %d ", mean);
		
	}

	//Alterar informação robot : OK
	public static Robot[] changeInfo (Robot[] array) {
		//Ler número do robô a alterar
		System.out.printf("Introduza o nome do robô que pretende alterar: ");
		String name=read.next();
		
		//Pesquisa binária
		int i = 0;
		int pos = -1; 
		do {
			if(array[i++].name.equals(name)) {
				pos = i-1;
			}
		} while(pos == -1 && i < array.length);
		
		if (pos == -1)  		//Se não existir robô
			System.out.printf("Robô não existente\nInformações não foram alteradas.");
		
		else if (pos != -1) {	//Se existir robô 
			//Alterar informação da string no índice pos
			System.out.printf("Novo nome do robô: ");
			String new_name=read.next();
			array[pos].name=new_name;
	
			System.out.printf("Novo tempo de prova do robô em segundos : ");
			int time=read.nextInt();
			array[pos].time=time;
			
			System.out.printf("Novo número de elementos da equipa: ");
			int num=read.nextInt();
			array[pos].num=num;
			
			System.out.printf("\nInformações foram alteradas.");
		}
		
		return array;
	}
	
	//Remover robos cujo tempo é superior a um determinado valor
	public static Robot[] removeInfo (Robot[] array) {
		//Lê o valor
		System.out.printf("\nRemover robôs da competição\nIntroduza o valor máximo de tempo em segundos: ");
		int value=read.nextInt();
		
		//Ordena a array (ordem crescente)
		orderValues(array,0);
		
		//Índice a partir do qual se remove os valores (array está ordenada de forma crescente)
		int position=0;
				
		//Determina esse índice
		for (int i=0; i<array.length; i++) {
			if (array[i].time>value) {
				position=i;
				break;
			}
		}
		
		for (int j=position; j<array.length; j++) {
			array[j]=null;
			array[j]=new Robot();
		}
		System.out.printf("\nInformação removida com êxito.");
		return array;
		
	}
	
	//Gravar num ficheiro
	public static void saveInfo (Robot[] array) throws IOException {
		//Pede ao utilizador o nome do ficheiro 
		System.out.printf("\nGravar informação num ficheiro\nPor favor introduza o nome do ficheiro de texto (com extensão): ");
		String filename=read.next();
		File file= new File (filename);

		PrintWriter out = new PrintWriter (file);
		out.printf("Micro-Rato 2013 - Informações dos robôs em prova");
		if (array.length==0) out.printf("\nSem informação.");
		for (int i=0; i<array.length; i++) {
			if (array[i]!=null) out.printf("\nRobô %s (equipa de %d elementos) | Tempo: %s", array[i].name, array[i].num, convertTime(array[i].time));	
		}

		out.close();
		System.out.printf("Informação guardada com êxito no ficheiro %s.", filename);
	}
	
	//------------------------------------------------------------------
	//Funções auxiliares
	//Conversão tempo s --> hh:mm:ss : OK
	public static String convertTime (int time) {
		int hours = (int) time / 3600;
		int remain = time % 3600;
		int minutes = (int) remain / 60;
		int seconds = (int) remain % 60;

		String new_time=hours+":"+minutes+":"+seconds;
		return new_time;

	}

	//Ordena por valor de tempo (op=0), por valor de nome (op=1)
	public static Robot[] orderValues (Robot[] array, int op) {	
		Robot tmp1, tmp2;
		boolean switches1, switches2;

		switch (op) {
		case 0:
			do {
				switches1 = false;
				for (int i=0; i<array.length-1; i++) {
					if (array[i].time > array[i+1].time) {
						tmp1=array[i];
						array[i]=array[i+1];
						array[i+1]=tmp1;
						switches1=true;
					}
				}
			} while (switches1);
			break;
		case 1:
			do {
				switches2 = false;
				for (int i=0; i<array.length-1; i++) {
					if (Character.getNumericValue(array[i].name.charAt(0)) > Character.getNumericValue(array[i+1].name.charAt(0))) {
						tmp2=array[i];
						array[i]=array[i+1];
						array[i+1]=tmp2;
						switches2=true;
					}
				}
			} while (switches2);
			break;
		}			

		return array;
	}

	//Cria o nome em maiúsculas
	public static String capitalizeName (String name) {
		String new_name="";
		
		for (int i=0; i<name.length(); i++) {
			char tmp=name.charAt(i);
			if(Character.isLetter(tmp)) {
				tmp=Character.toUpperCase(tmp);
			}
			 new_name= new_name+tmp;
		}
		
		return new_name;
	}
}

class Robot {
	String name;
	int time;
	int num;
}