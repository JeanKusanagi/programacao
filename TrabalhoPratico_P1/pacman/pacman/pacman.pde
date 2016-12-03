/* Maria João Lavoura
 * Pedro Teixeira
 *
 * Programação I | Trabalho Prático
 * Turma P8
 */
//-----------------------------------------------------------------------------------------------------------------------------------------
//Importação de bibliotecas utilizadas
import java.io.*;
import java.util.Scanner;
import ddf.minim.*;

//Parâmetros do labirinto
int nCol, nLin;                                 // Nº de linhas e de colunas
int tamanho = 50;                               // Tamanho (largura e altura) das células do labirinto  
int espacamento = 2;                            // Espaço livre entre células
float margemV, margemH;                         // Margem livre na vertical e na horizontal para assegurar que as células são quadrangulares
color corObstaculos =  color(100, 0, 128);      // Cor de fundo dos obstáculos
color ui=#FFF308;                               // Cor do texto e dos limites dos menus

//Parâmetros Pacman
float px_pac, py_pac, pRaio;  //Posição
float vx_pac, vy_pac;         //Velocidade

//Parâmetros dos fantasmas
float px_ghost, py_ghost;     //Posição
float vx_ghost, vy_ghost;     //Velocidade

//Estado do jogo (0=Menu, 1=Single Player, 2=Multiplayer, 3=Scores, 4=Help)
int gamestate, old_gamestate; 

//Detectada colisão? (1=Sim, 2=Não)
int detectedColision;

//Vencedor (1=Pacman, 2=Fantasma)
int win;

//Som


//-----------------------------------------------------------------------------------------------------------------------------------------
void setup() {
  //Tamanho e título da janela
  size(720, 520);
  background(0);
  frame.setTitle("Pacman | Maria João Lavoura | Pedro Teixeira");

  //Número de linhas e de colunas
  nCol = (int)width/tamanho;
  nLin = (int)height/tamanho;

  //Assegurar que nº de linhas e nº de colunas é maior ou igual a 3
  assert nCol >= 5 && nLin >= 5;

  //Determinar margens para limitar a área útil do jogo 
  margemV = (width - nCol * tamanho) / 2.0;
  margemH = (height - nLin * tamanho) / 2.0;

  //Posição Inicial do Pacman
  px_pac = centroX(1);
  py_pac = centroY(2);
  pRaio = (tamanho - espacamento) / 2;

  //Posição Inicial dos Fantasmas
  px_ghost = centroX(13);
  py_ghost = centroY(1);

  //Velocidades
  vx_ghost=0;
  vy_ghost=0;

  gamestate=0;
}

//-----------------------------------------------------------------------------------------------------------------------------------------
void draw() {
  switch (gamestate) {

  case 0:   //Mostra as opções
    background(0);      //Limpa o ecrã
    showMenu(); 
    break;

  case 1:   //Inicia o jogo Single Player
    background(0);
    startGame(); 
    break;

  case 2:   //Inicia o jogo Multiplayer
    background(0);
    startGameMultiplayer(); 
    break;

  case 3:   //Mostra as pontuações
    background(0);
    showScores(); 
    break; 

  case 4:   //Mostra a ajuda
    background(0);
    showHelp(); 
    break; 

  case 5:   //Termina o jogo
    background(0);
    endGame(win);
    break;
  }

  //Detecta quando o Pacman ganha (obtêm a pontuação máxima)
  //if () {
  //  gamestate=5;
  //  win=1;
  //}

  //Detecta quando o fantasma ganha (colisão entre o fantasma e o Pacman)
  if (detectedColision==1) {
    gamestate=5;
    win=2;
  }
}
//-----------------------------------------------------------------------------------
//Imprime o menu
void showMenu() {
  //Fundo e limite
  PImage background;
  background = loadImage("background.jpg");
  background(background);

  fill(0, 0);
  stroke(ui);
  strokeWeight(espacamento);
  rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

  //Texto
  PFont f=createFont("LithosPro-Black", 30, false); 
  textFont(f);
  fill(ui);

  text("1 - 1 Jogador", width/2, height/2+110);
  text("2 - 2 Jogadores", width/2, height/2+150);
  text("3 - Pontuações", width/2, height/2+190);
  textSize(15);
  text("H - Ajuda e Opções", width/2, height/2+230);
  textAlign(CENTER);

  //Executa as opções
  switch (key) { 
  case '1' : 
    gamestate=1;
    break;
  case '2' : 
    gamestate=2;
    break;
  case '3' : 
    gamestate=3;
    break;
  case 'H' :
    gamestate=4;
    break;
  case 'h' :
    gamestate=4;
    break;
  }
}

//-----------------------------------------------------------------------------------
//Começa o jogo Single Player
void startGame() {
  background(0);
  desenharLabirinto();
  desenharPontos();
  desenharPacman(rotatePacmanStop(), rotatePacmanStart());
  desenharFantasma(); 
  moveGhost();

  //Impede que os fantasmas saiam dos limites do ecrã
  if (px_ghost > centroX(nCol))
    vx_ghost = -vx_ghost;
  if (px_ghost < centroX(1))
    vx_ghost = -vx_ghost;
  
  //Torna a velocidade funcional
  px_ghost += vx_ghost;
  py_ghost += vy_ghost;
}
//-----------------------------------------------------------------------------------
//Começa o jogo multijogador
void startGameMultiplayer() {
  background(0);
  desenharLabirinto();
  desenharPontos();
  desenharPacman(rotatePacmanStop(), rotatePacmanStart());
  desenharFantasma(); 
}
//-----------------------------------------------------------------------------------
//Função que termina o jogo mostrando uma mensagem e retornando ao menu
void endGame(int winner) {
  //Termina o jogo
  setup();

  //Texto "Game Over"
  PFont f=createFont("LithosPro-Black", 30, false); 
  textFont(f);
  fill(ui);
  text("Game Over", width/2, height/2-50);

  //Indica quem ganhou
  if (winner==1) {	
    text("Jogador 1 foi eliminado", width/2, height/2+50);
    //Print Pontuação para ficheiro
  } else if (winner==2) {
    text("Jogador 2 foi eliminado", width/2, height/2+50);
    //Print Pontuação para ficheiro
  }

  //Mensagem para retornar ao menu
  textSize(15);
  text("Pressione uma tecla para voltar ao menu", width/2, height/2+100);
  textAlign(CENTER);

  //Retorna ao menu;
  gamestate=0;
}

//-----------------------------------------------------------------------------------
//Função que imprime a ajuda
void showHelp() {
  fill(0, 0);
  stroke(ui);
  strokeWeight(espacamento);
  rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

  PFont f=createFont("LithosPro-Black", 30, false); 
  textFont(f);
  fill(#FFF308);

  //Título
  textAlign(CENTER);	
  text("Ajuda & Opções", width/2, 60);

  //Subtítulos
  textAlign(CENTER);
  textSize(22);
  text("Controlos", (width/2), 110);
  text("Opções", (width/2), 420);

  //Texto
  textSize(18);

  //Controlos
  textAlign(LEFT);
  text("|  Movimenta o Pacman", (width/7)+50, 160);
  textAlign(LEFT);
  text("W A S D", (width/7)-50, 200);
  text("|  Movimenta o Fantasma (modo 2 Jogadores)", (width/7)+50, 200);
  textAlign(LEFT);
  text("P/Space", (width/7)-50, 240);
  text("|  Pausa o Jogo", (width/7)+50, 240);
  textAlign(LEFT);
  text("M", (width/7)-50, 280);
  text("|  Ativar/Desativar Som", (width/7)+50, 280);
  textAlign(LEFT);
  text("H", (width/7)-50, 320);
  text("|  Mostrar texto de Ajuda", (width/7)+50, 320);
  textAlign(LEFT);
  text("ESC", (width/7)-50, 360);
  text("|  Voltar ao menu", (width/7)+50, 360);

  //Opções
  textAlign(LEFT);
  text("C", (width/7)-50, 460);
  text("|  Definir cor do labirinto aleatoriamente", (width/7)+50, 460);

  textSize(10);
  textAlign(CENTER);
  text("Autores: Maria João Lavoura | Pedro Veloso Teixeira", (width/2), 500);

  /* Texto: setas direcionais
   * necessário utilizar outra fonte pois LithosPro-Black não possui estes caracteres) */
  PFont f1=createFont("Arial", 30, false); 
  textFont(f1);
  textSize(19);
  textAlign(LEFT);
  text("\u2190 \u2192 \u2191 \u2193", (width/7)-50, 160); // u2... : UNICODE do caracter
}
//-----------------------------------------------------------------------------------
//Função que imprime as pontuações
void showScores() {
  fill(0, 0);
  stroke(ui);
  strokeWeight(espacamento);
  rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

  PFont f=createFont("LithosPro-Black", 30, false); 
  textFont(f);
  fill(ui);

  //Título
  textAlign(CENTER);
  text("Pontuações", width/2, 60);

  //Subtítulos
  textAlign(LEFT);
  textSize(22);
  text("1 Jogador", (width/7), 110);
  text("2 Jogadores", 4*(width/7)+50, 110);

  //Imprimir pontuações (score1[] para single player, score2[] para multiplayer)
  //for (int i=0; i<scores1.lenght; i++) {
  //  String score=scores1[i];
  //   textSize(18);
  //  textAlign(LEFT);
  //  text(score, (width/7)+50, 160+40*i);
  //}

  //for (int i=0; i<scores2.lenght; i++) {
  //  String score=scores2[i];
  //   textSize(18);
  //  textAlign(LEFT);
  //  text(score, (width/7)+50, 160+40*i);
  //}

  //Fazer reset das pontuações : ainda não implementado
  textSize(18);
  textAlign(LEFT);
  text("R | Reinciar as pontuações", (width/7)-50, 460);
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Função que desenha o Pacman
void desenharPacman(float start, float stop) {
  fill(232, 239, 40);
  arc(px_pac, py_pac, pRaio, pRaio, stop, start);
}

//-----------------------------------------------------------------------------------
//Função que desenha os fantasmas
void desenharFantasma() {
  PImage ghost;
  ghost=loadImage("ghost.png");
  imageMode(CENTER);
  image(ghost, px_ghost, py_ghost, 30, 30);
}

//-----------------------------------------------------------------------------------
//Função que move aleatoriamente os fantasmas
void moveGhost() {
  
  if (px_pac==px_ghost) {
    if (py_pac<py_ghost) {
      vx_ghost=0; 
      vy_ghost=-2;
    }
    if (py_pac>py_ghost) {
      vx_ghost=0; 
      vy_ghost=2;
    }
  }
  if (py_pac==py_ghost) {
    if (px_pac<px_ghost) {
      vy_ghost=0; 
      vx_ghost=-2;
    }
    if (px_pac>px_ghost) {
      vy_ghost=0; 
      vx_ghost=2;
    }
  } 

  if ((px_pac==px_ghost) && (py_pac==py_ghost)) 
    detectedColision=1;
  else {
    detectedColision=0;
  }
}

//-----------------------------------------------------------------------------------------------------------------------------------------
//Função chamada quando existe input do teclado
void keyPressed() {

  //Move o Fantasma (WASD) 
  if (gamestate==2) {      //Garante que só é possivel controlar o fantasma no modo Multijogador
    //Left A Key
    if ( key == 'a' || key == 'A' ) {
      PImage ghost_left;
      ghost_left=loadImage("ghost_left.png");
      image(ghost_left, px_ghost, py_ghost, 30, 30);

      float cx = px_ghost-50;                //Para a célula ao lado esquerdo da actual
      float cy = py_ghost;                   //...da mesma linha
      color c = get((int)cx, (int)cy);       //Obtém a cor dessa célula
      if (c != corObstaculos) {              //Se essa célula não for obstáculo
        px_ghost = px_ghost - 50;            //Move o fantasma
      }

      if (px_ghost < centroX(1)) {           //impede Fantasma sair da janela
        px_ghost = px_ghost + 50;
      }
    }

    //----------------------------------------------
    //Right D Key
    if ( key == 'd' || key == 'D' ) {
      PImage ghost_right;
      ghost_right=loadImage("ghost_right.png");
      image(ghost_right, px_ghost, py_ghost, 30, 30);

      float cx = px_ghost+50;
      float cy = py_ghost;

      color c = get((int)cx, (int)cy);

      if (c != corObstaculos) {
        px_ghost = px_ghost + 50;
      }

      if (px_ghost > centroX(nCol)) { 
        px_ghost = px_ghost - 50;
      }
    }

    //----------------------------------------------
    //Up W Key
    if ( key == 'w' || key == 'W' ) {
      float cx = px_ghost;
      float cy = py_ghost-50;
      color c = get((int)cx, (int)cy);

      if (c != corObstaculos) {
        py_ghost = py_ghost - 50;
      }

      if (py_ghost < centroY(1)) { 
        py_ghost = py_ghost + 50;
      }
    }

    //----------------------------------------------
    //Down S Key
    if ( key == 's' || key == 'S' ) {
      PImage ghost_down;
      ghost_down=loadImage("ghost_down.png");
      image(ghost_down, px_ghost, py_ghost, 30, 30);

      float cx=px_ghost;
      float cy=py_ghost+50;
      color c = get((int)cx, (int)cy);

      if (c != corObstaculos) {
        py_ghost = py_ghost + 50;
      }

      if (py_ghost > centroY(nLin)) { 
        py_ghost = py_ghost - 50;
      }
    }
  }
  //------------------------------------------------------------------------------------------------------------ 
  //Move o Pacman (arrow keys) 

  //Left Arrow Key
  if ( keyCode == LEFT ) {
    float cx = px_pac-50;              //Para a célula ao lado esquerdo da actual
    float cy = py_pac;                 //...da mesma linha
    color c = get((int)cx, (int)cy);   //Obtém a cor dessa célula

    if (c != corObstaculos) {          //Se essa célula não for obstáculo
      px_pac = px_pac - 50;            //Move o Pacman
    }

    if (px_pac < centroX(1)) { //impede pacman sair da janela
      px_pac = px_pac + 50;
    }
  }
  //----------------------------------------------
  //Right Arrow Key
  if ( keyCode == RIGHT ) {
    float cx = px_pac+50;
    float cy = py_pac;
    color c = get((int)cx, (int)cy);

    if (c != corObstaculos) {
      px_pac = px_pac +  50;
    }

    if (px_pac > centroX(nCol)) {
      px_pac = px_pac - 50;
    }
  }
  //----------------------------------------------
  //Up Arrow Key
  if ( keyCode == UP ) {
    float cx = px_pac;
    float cy = py_pac-50;
    color c = get((int)cx, (int)cy);

    if (c != corObstaculos) {
      py_pac = py_pac - 50;
    }

    if (py_pac < centroY(1)) {
      py_pac = py_pac + 50;
    }
  }
  //----------------------------------------------
  //Down Arrow Key
  if ( keyCode == DOWN ) {
    float cx=px_pac;
    float cy=py_pac+50;
    color c = get((int)cx, (int)cy);

    if (c != corObstaculos) {
      py_pac = py_pac + 50;
    }

    if (py_pac > centroY(nLin)) {
      py_pac = py_pac - 50;
    }
  }
  //------------------------------------------------------------------------------------------------------------ 
  //Detecta colisões
  if ((px_pac==px_ghost) && (py_pac==py_ghost)) 
    detectedColision=1;
  else {
    detectedColision=0;
  } 

  //------------------------------------------------------------------------------------------------------------ 
  //Interacção com o utilizador
  //Pausa o jogo
  PFont f=createFont("LithosPro-Black", 30, false);
  textFont(f);
  fill(ui);

  if ((key == 'P') || (key == 'p') || (key == ' ')) { 
    if (gamestate==1 || gamestate==2) {      //Garante que só entra em pausa se estiver no jogo
      if (looping) {
        noLoop();
        text("Em Pausa", width/2, height/2);
        textSize(15);
        text("Pressione P/Space para continuar", width/2, height/2+50);
        textAlign(CENTER);
      } else loop();
    }
  }

  //Mostra a ajuda
  if (key == 'h' || key == 'H') {
    if (gamestate!=4) {
      old_gamestate=gamestate;  //Permite retornar ao ecrã anterior
      gamestate=4;
    } else if (gamestate==4) {
      gamestate=old_gamestate;
    }
  }

  //Reincia o jogo/retorna ao menu 
  if (key == ESC) {
    key=0;
    setup();
  }

  //Aleatoriza a cor dos obstáculos
  if (key == 'c' || key == 'C') {
    do {
      corObstaculos=color(random(255), random(255), random(40, 255));     //random(40,255) impede um Brightness menor que 40, impedido cores demasiado claras
    } while (corObstaculos==#000000);  //Impede que a cor do obstáculo seja preto
  }

  //Desliga/Liga o Som
  if (key == 'm' || key == 'M') {
    //mute();
  }

  //Reincia as pontuações
}

////----------------------------------------------------------------------------------------------------------------------------------------
//Função que roda pacman
float rotatePacmanStop() {
  if ( keyCode == LEFT ) {
    return radians(135);
  } else {
    if ( keyCode == RIGHT ) {
      return radians(315);
    } else {
      if ( keyCode == UP ) {
        return radians(225);
      } else {
        if ( keyCode == DOWN ) {
          return radians(45);
        } else {
          return radians(315);
        }
      }
    }
  }
}

float rotatePacmanStart() {
  if ( keyCode == LEFT ) {
    return radians(-135);
  } else {
    if ( keyCode == RIGHT ) {
      return radians(45);
    } else {
      if ( keyCode == UP ) {
        return radians(-45);
      } else {
        if ( keyCode == DOWN ) {
          return radians(-225);
        } else {
          return radians(45);
        }
      }
    }
  }
}

//-----------------------------------------------------------------------------------------------------------------------------------------
//Função que desenha o labirinto
void desenharLabirinto () {

  //Desenha a fronteira da área de jogo
  fill(0);
  stroke(corObstaculos);
  strokeWeight(espacamento);
  rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

  //Desenha obstáculos
  desenharObstaculo(3, 2, 1, 2); // P
  desenharObstaculo(2, 2, 1, 4); // P
  desenharObstaculo(5, 2, 3, 1);
  desenharObstaculo(5, 5, 3, 1);
  desenharObstaculo(6, 3, 1, 2);
  //desenharObstaculo(2, 4, 1, nLin-4);
  //desenharObstaculo(5, 4, nCol-4, nLin-4);

  /* Desenha um obstáculo interno de um labirinto:
   x: índice da célula inicial segundo eixo dos X - gama (1..nCol) 
   y: índice da célula inicial segundo eixo dos Y - gama (1..nLin)
   numC: nº de colunas (células) segundo eixo dos X (largura do obstáculo)
   numL: nº de linhas (células) segundo eixo dos Y (altura do obstáculo) 
   	 */
}

//-----------------------------------------------------------------------------------
//Função que desenha obstáculos
void desenharObstaculo(int x, int y, int numC, int numL) {
  float x0, y0, larg, comp;

  x0 = margemH + (x-1) * tamanho;
  y0 = margemV + (y-1) * tamanho;
  larg = numC * tamanho;
  comp = numL * tamanho;

  fill(corObstaculos);
  noStroke();
  //strokeWeight(espacamento/2);
  rect(x0, y0, larg, comp);
}

/* Desenhar pontos nas células vazias (que não fazem parte de um obstáculo). 
 * Esta função usa a cor de fundo no ecrã para determinar se uma célula está vazia ou se faz parte de um obstáculo.
 */

//-----------------------------------------------------------------------------------
//Função que desenha pontos
void desenharPontos() {
  float cx, cy;

  ellipseMode(CENTER);
  fill(255);
  noStroke();

  // Insere um ponto nas células vazias
  for (int i=1; i<=nCol; i++)                  
    for (int j=1; j<=nLin; j++) {
      cx = centroX(i);
      cy = centroY(j);
      color c = get((int)cx, (int)cy);
      if (c != corObstaculos) {
        fill(255);
        ellipse(cx, cy, pRaio/2, pRaio/2);
      }
    }
}

//-----------------------------------------------------------------------------------------------------------------------------------------
//Transformar o índice de uma célula em coordenada no ecrã
float centroX(int col) {
  return margemH + (col - 0.5) * tamanho;
}

//Transformar o índice de uma célula em coordenada no ecrã
float centroY(int lin) {
  return margemV + (lin - 0.5) * tamanho;
}
//

//-----------------------------------------------------------------------------------------------------------------------------------------
//Funções para Pontuações -- ainda não testadas nem verificadas
//Função que obtém pontuações de um ficheiro
int[] ReadScores_File () throws IOException {  
  File file= new File ("scores.txt");

  Scanner in = new Scanner (file);
  int scores[] = new int[(int) file.length()];
  int i=0;

  while (in.hasNextInt()) {
    String s = in.next();
    if (!in.hasNext()) break;
    scores[i] = Integer.parseInt(s);
    i++;
  }

  in.close();
  return scores;
}


//Função que imprime as pontuações num ficheiro
void PrintScores_File (int[] scores, int[] scores2) throws IOException {
  //Single Player Mode
  File file = new File ("scores_singleplayer.txt");       
  PrintWriter out=new PrintWriter (new FileWriter(file));  //FileWriter impede que o texto anterior seja apagado.

  for (int i=0; i<scores.length; i++) {
    int num=scores[i];
    out.print(num);
    out.print(" ");
  }
  out.close();

  //Multiplayer Mode
  File file2 = new File ("scores_multiplayer.txt");       
  PrintWriter out2=new PrintWriter (new FileWriter(file2));  //FileWriter impede que o texto anterior seja apagado.

  for (int j=0; j<scores.length; j++) {
    int num=scores2[j];
    out2.print(num);
    out2.print(" ");
  }
  out2.close();
}