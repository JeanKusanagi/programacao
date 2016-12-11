/*
 * Files_Pacman.java
 *
 * Copyright 2016 Pedro <Pedro@UA>
 */

import java.io.*;
import java.util.Scanner;
import static java.lang.System.*;

public class Files_Pacman {

  public static void main(String[] args) throws IOException {
    System.out.printf("SinglePlayer:\n");


    printArray(readScores_File(1, 10));

    System.out.printf("Multiplayer:\n");
    printArray(readScores_File(2, 10));

    int []array1={500, 129, 28, 49, 2119, 102, 102};
    int []array2={10, 203, 20183, 102982, 1092874, 18283, 102831, 2, 8, 9, 10293, 1928};
    System.out.printf("PONTUAÇÕES SINGLEPLAYER GRAVADAS");
    printScores_File(array1, 1, 10);
    System.out.printf("PONTUAÇÕES MULTIPLAYER GRAVADAS");
    printScores_File(array2, 2, 10);
  }

  //Função que obtém pontuações (do Single Player - 1, do Mulitplayer - 2) de um ficheiro
  static int[] readScores_File (int n, int j) throws IOException {

    //Decide qual o nome do ficheiro a ler
    String path = "";
    switch (n) {
    case 1:
      path="scores_singleplayer.txt"; 
      break;
    case 2:
      path="scores_multiplayer.txt";
      break;
    }

    //Scanner para ler do ficheiro
    File file = new File (path);
    Scanner in = new Scanner (file);

    //Array temporária com todos os valores do ficheiro
    int array_temp[] = new int[(int)file.length()];
    int i=0;

    while (in.hasNextInt()) {
      String s = in.nextLine();
      //if (!in.hasNext()) break;
      array_temp[i] = Integer.parseInt(s);
      i++;
    }

    in.close();

    //Ordenar os valores (ordem decrescente)
    orderArray(array_temp);

    //Criar uma nova array com os j valores (neste caso pontuações) pretendidas
    int array[]=new int[j];
    for (int k=0; k<j; k++) {
      array[k]=array_temp[k];
    }
    return array;
  }

  //Função que imprime as pontuações (do Single Player - 1, do Mulitplayer - 2) num ficheiro
  static void printScores_File (int[] array, int n, int j) throws IOException {
    //Decide qual o nome do ficheiro a ler
    String path = "";
    switch (n) {
    case 1:
      path="scores_singleplayer.txt";
      break;
    case 2:
      path="scores_multiplayer.txt";
      break;
    }

    //Scanner para escrever no ficheiro
    File file = new File (path);
    PrintWriter out=new PrintWriter (file);

    //Ordenar os valores (ordem decrescente)
    orderArray(array);

    //Determina quantos valores x a imprimir no ficheiro: j valores ou todos os valores da array (se array.lenght<j)
    int x=0;
    if (array.length>j) x=j;
    if (array.length<=j) x=array.length;

    //Imprime os n valores (neste caso pontuações) no ficheiro 
    for (int i=0; i<x; i++) {
      int num=array[i];
      out.println(num);
    }
    out.close();
  }

  static void orderArray (int[] array) {
    int temp, u=0;
    do {
      u=0;
      for (int i=0; i<=array.length-2; i++) {
        if (array[i] < array[i+1]) {
          temp = array[i];
          array[i] = array[i+1];
          array[i+1] = temp;
          u++;
        }
      }
    } while (u!=0);
  }

  //Função que imprime as pontuações no ecrã
  void printScores (int array[], int col, int lin) {
    //col e lin dão a posição do texto (horizontal e vertical)
    //Formatação
    textSize(18);
    textAlign(LEFT);

    for (int i=0; i<array.lenght; i++ ) {
      text(array[i], col, lin+i*40);
    }
  }