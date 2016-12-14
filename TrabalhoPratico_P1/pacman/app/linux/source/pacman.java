import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.io.*; 
import java.util.Scanner; 
import ddf.minim.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class pacman extends PApplet {

/* Maria Lavoura N. Mec. 84681
 * Pedro Teixeira N. Mec. 84715
 *
 * Programa\u00e7\u00e3o I | Trabalho Pr\u00e1tico
 * Turma P8
 */

//-----------------------------------------------------------------------------------------------------------------------------------------
//Importa\u00e7\u00e3o de bibliotecas utilizadas


                             //Som

//Par\u00e2metros do labirinto
int nCol, nLin;                                 //N\u00ba de linhas e de colunas
int tamanho = 50;                               //Tamanho (largura e altura) das c\u00e9lulas do labirinto
int espacamento = 2;                            //Espa\u00e7o livre entre c\u00e9lulas
float margemV, margemH;                         //Margem livre na vertical e na horizontal para assegurar que as c\u00e9lulas s\u00e3o quadrangulares
int corObstaculos;                            //Cor de fundo dos obst\u00e1culos (original RGB: 100,0,128)
int cor_default = color(145, 168, 208);
int ui=0xffFFF308;                               //Cor do texto e dos limites dos menus
String font="LithosPro-Black.otf";              //Fonte da UI

//Par\u00e2metros do Pacman
float px_pac, py_pac, pRaio;                    //Posi\u00e7\u00e3o
float vx_pac, vy_pac;                           //Velocidade
int dir;                                        //Direc\u00e7\u00e3o e sentido do Pacman (modo multijogador)
                                                //Evita que o Pacman volte \u00e0 sua rota\u00e7\u00e3o original quando o fantasma move-se

//Inicializa\u00e7\u00e3o dos fantasmas
Ghost red=new Ghost();
Ghost pink=new Ghost();
Ghost orange=new Ghost();
Ghost blue=new Ghost();
int red_ghost_img;                              //No modo Singleplayer, determina qual a imagem a desenhar consoante a tecla pressionada
PImage death_ghost;                             //Imagem do fantasma morto

//Estado do jogo (0=Menu, 1=Single Player, 2=Multiplayer, 3=Pontua\u00e7\u00f5es, 4=Ajuda, 5=Gameover)
int gamestate, old_gamestate;

//Estado do jogo (novo jogo)
boolean justStarted = true;

//Detectada colis\u00e3o? (1=Sim, 2=N\u00e3o)
int detectedColision;

//Vencedor (1=Pacman, 2=Fantasma)
int win;

//Som
Minim minim;
AudioPlayer sound[]=new AudioPlayer[3];       //Array com as m\u00fasicas utilizadas
boolean soundEnabled=true;                    //Som activo/desactivado

//Array bolas=comida
float comida[][];

//Pontua\u00e7\u00f5es (1=Single Player, 2=Multiplayer)
int max_points;
int initial_points_sp=120;                    //N\u00famero de pontos do labirinto do modo Single Player
int initial_points_mp=116;                    //N\u00famero de pontos do labirinto do modo Multiplayer
int drawn_points;                             //N\u00famero de pontos desenhados (gameover quando drawn_points=0)
int score;                                    //Pontua\u00e7\u00e3o do jogo (pontua\u00e7\u00e3o=[pontua\u00e7\u00e3o m\u00e1xima-drawn_points]*factor)
int factor=100;                               //Quanto vale cada ponto em termos de pontua\u00e7\u00e3o
int scores1[]=new int[10];                    //Array de inteiros com as pontua\u00e7\u00f5es para o modo Single Player
int scores2[]=new int[10];                    //Array de inteiros com as pontua\u00e7\u00f5es para o modo Multi Player
int num_scores=8;                             //N\u00famero de pontua\u00e7\u00f5es m\u00e1ximas a apresentar
boolean called_saveScores=true;

//Ponto especial
boolean blinker;                              //Permite tornar o ponto intermitente
int px_specialpoint=14;                        //Posi\u00e7\u00e3o do ponto
int py_specialpoint=6;
int color_specialpoint=0xff990000;             //Cor do ponto
boolean drawnSpecialPoint;                    //O ponto foi desenhado?
boolean ghost_dead;                           //O ponto foi comido -- os fantasmas foram mortos?

//-----------------------------------------------------------------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que come\u00e7a o jogo. Executada quando o programa inicia ou reinicia (atrav\u00e9s da tecla ESC)
public void setup() {
  println("Trabalho Pratico de Programacao I : Pacman Multijogador");
  println("DETI - UA");
  println("Maria Lavoura, N. Mec. 84681");
  println("Pedro Teixeira, N. Mec. 84715");
  println("Jogo Iniciado");
  println(" ");

  //Tamanho, t\u00edtulo e \u00edcone da janela
  
  background(0);
  surface.setTitle("Pacman Multijogador | Maria Lavoura | Pedro Teixeira");
  PImage icon = loadImage(dataPath("icon.png"));
  surface.setIcon(icon);

  //N\u00famero de linhas e de colunas
  nCol = (int)width/tamanho;
  nLin = (int)height/tamanho;

  //Assegurar que n\u00ba de linhas e n\u00ba de colunas \u00e9 maior ou igual a 5
  assert nCol >= 5 && nLin >= 5;

  //Determinar margens para limitar a \u00e1rea \u00fatil do jogo
  margemV = (width - nCol * tamanho) / 2.0f;
  margemH = (height - nLin * tamanho) / 2.0f;

  //Inicializa\u00e7\u00e3o da cor dos obst\u00e1culos
  corObstaculos=cor_default;

  //Inicaliza\u00e7\u00e3o da posi\u00e7\u00e3o inicial do Pacman
  px_pac = centroX(2);
  py_pac = centroY(1);
  pRaio = (tamanho - espacamento) / 2;

  //Inicaliza\u00e7\u00e3o dos par\u00e2metros dos fantasmas
  red.px = centroX(13);      //Posi\u00e7\u00f5es iniciais
  red.py = centroY(1);
  pink.px = centroX(4);
  pink.py = centroY(7);
  orange.px = centroX(14);
  orange.py = centroY(10);
  blue.px= centroX(1);
  blue.py= centroY(10);
  red.vx=0;                  //Velocidades inicias
  red.vy=0;
  pink.vx=0;
  pink.vy=0;
  orange.vx=0;
  orange.vy=0;
  blue.vx=0;
  blue.vy=0;

  //Inicializa\u00e7\u00e3o das imagens
  red.images[0]=loadImage(dataPath("red.png"));
  red.images[1]=loadImage(dataPath("red_up.png"));
  red.images[2]=loadImage(dataPath("red_left.png"));
  red.images[3]=loadImage(dataPath("red_right.png"));

  pink.images[0]=loadImage(dataPath("pink.png"));
  pink.images[1]=loadImage(dataPath("pink_up.png"));
  pink.images[2]=loadImage(dataPath("pink_left.png"));
  pink.images[3]=loadImage(dataPath("pink_right.png"));

  orange.images[0]=loadImage(dataPath("orange.png"));
  orange.images[1]=loadImage(dataPath("orange_up.png"));
  orange.images[2]=loadImage(dataPath("orange_left.png"));
  orange.images[3]=loadImage(dataPath("orange_right.png"));

  blue.images[0]=loadImage(dataPath("blue.png"));
  blue.images[1]=loadImage(dataPath("blue_up.png"));
  blue.images[2]=loadImage(dataPath("blue_left.png"));
  blue.images[3]=loadImage(dataPath("blue_right.png"));

  death_ghost=loadImage(dataPath("death_ghost.png"));

  //Inicializar os sons  (1: Som de In\u00edcio, 2: Som de Fim de Jogo, 3: Som Comer Ponto)
  minim = new Minim(this);
  sound[0]=minim.loadFile("start.mp3");
  sound[1]=minim.loadFile("gameover.mp3");
  sound[2]=minim.loadFile("eatpoint.mp3");

  //Som de In\u00edcio
  if (soundEnabled==true && gamestate!=5) sound[0].play(0);
  gamestate=0;

  //Coordenadas da comida
  comida = new float[nCol][nLin];
  arrayComida();

  //Ponto especial
  drawnSpecialPoint=false;
  ghost_dead=false;
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que executa os diferentes modos do jogo (0: Menu, 1: Jogo Single Player, 2: Jogo Multiplayer, 3: Pontua\u00e7\u00f5es, 4: Ajuda, 5: Gameover)
public void draw() {
  switch (gamestate) {

  case 0:   //Mostra as op\u00e7\u00f5es
    background(0);      //Limpa o ecr\u00e3
    showMenu();
    break;

  case 1:   //Inicia o jogo Single Player
    background(0);
    startGame();
    drawSpecialPoint();
    break;

  case 2:   //Inicia o jogo Multiplayer
    background(0);
    startGameMultiplayer();
    drawSpecialPoint();
    break;

  case 3:   //Mostra as pontua\u00e7\u00f5es
    background(0);
    showScores();
    break;

  case 4:   //Mostra a ajuda
    background(0);
    showHelp();
    break;

  case 5:   //Termina o jogo
    background(0);
    gameoverSound();
    endGame(win);
    break;
  }

  if (gamestate==1) {
    //Sinaliza que o Pacman esteve numa c\u00e9lula
    comida[((int)px_pac - 35)/50][((int)py_pac - 35)/50] = 2.0f;
    startGame();

    //Detecta quando o Pacman ganha (n\u00e3o s\u00e3o desenhados mais pontos)
    if (drawn_points==0) {
      gamestate=5;
      win=1;
    }
  }

  if (gamestate==2) {
    comida[((int)px_pac - 35)/50][((int)py_pac - 35)/50] = 2.0f;
    startGameMultiplayer();

    if (drawn_points==0) {
      gamestate=5;
      win=1;
    }
  }

  //Detecta quando o Pacman perde (colis\u00e3o entre um fantasma e o Pacman)
  if (detectedColision==1) {
    gamestate=5;
    win=2;
  }
}
//-----------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que imprime o menu inicial
public void showMenu() {
  //Fundo e limite
  PImage background;
  background = loadImage(dataPath("background.jpg"));
  background(background);

  fill(0, 0);
  stroke(ui);
  strokeWeight(espacamento);
  rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

  //Texto
  PFont f=createFont(font, 30, false);
  textFont(f);
  fill(ui);

  text("1 | 1 Jogador", width/2, height/2+110);
  text("2 | 2 Jogadores", width/2, height/2+150);
  text("3 | Pontua\u00e7\u00f5es", width/2, height/2+190);
  textSize(15);
  text("H | Ajuda e Op\u00e7\u00f5es", width/2, height/2+230);
  textAlign(CENTER);

  //Executa as op\u00e7\u00f5es
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
//Fun\u00e7\u00e3o que executa o jogo no modo Singleplayer
public void startGame() {
  background(0);
  desenharLabirintoSP();

  max_points=initial_points_sp;

  if (justStarted) {
    posicaoComida();
    justStarted = !justStarted;
  }

  caminhoPac();
  desenharPontos();

  desenharPacman(rotatePacmanStop(), rotatePacmanStart());

  //Desenha o ponto especial (s\u00f3 quando foram comidos mais de 1/2 e menos de 3/4 dos pontos iniciais)
  if (drawn_points<(initial_points_sp/2) && drawn_points>(initial_points_sp/4)) {
    drawSpecialPoint();
    //Detecta se o Pacman come o ponto especial
    detectSpecialPoint();
  }

  //Consoante a velocidade, desenha o fantasma correspondente
  if (!ghost_dead) {
    int i=0;
    if (red.vx<0) i=2;
    if (red.vx>0) i=3;
    if (red.vy<0) i=1;
    desenharFantasma(1, i);
    int j=0;
    if (pink.vx<0) j=2;
    if (pink.vx>0) j=3;
    if (pink.vy<0) j=1;
    desenharFantasma(2, j);
    int k=0;
    if (orange.vx<0) k=2;
    if (orange.vx>0) k=3;
    if (orange.vy<0) k=1;
    desenharFantasma(3, k);
    int l=0;
    if (blue.vx<0) l=2;
    if (blue.vx>0) l=3;
    if (blue.vy<0) l=1;
    desenharFantasma(4, l);
  }
  //Se os fantasmas estiverem mortos (ponto especial)
  if (ghost_dead) {
    image(death_ghost, red.px, red.py, 30, 30);
    image(death_ghost, pink.px, pink.py, 30, 30);
    image(death_ghost, orange.px, orange.py, 30, 30);
    image(death_ghost, blue.px, blue.py, 30, 30);
  }

  moveGhost();

  //Define o m\u00f3dulo das velocidades dos fantasmas
  red.set_vx=2;
  red.set_vy=2;
  pink.set_vx=2;
  pink.set_vy=2;
  orange.set_vx=2;
  orange.set_vy=2;
  blue.set_vx=2;
  blue.set_vy=2;

  //Implementa a velocidade
  red.px += red.vx;
  red.py += red.vy;
  pink.px += pink.vx;
  pink.py += pink.vy;
  orange.px += orange.vx;
  orange.py += orange.vy;
  blue.px += blue.vx;
  blue.py += blue.vy;
}
//-----------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que come\u00e7a o jogo no modo Multiplayer
public void startGameMultiplayer() {
  background(0);
  desenharLabirintoMP();

  max_points=initial_points_mp;

  if (justStarted) {
    posicaoComida();
    justStarted = !justStarted;
  }

  caminhoPac();
  desenharPontos();

  desenharPacman(rotatePacmanStop(), rotatePacmanStart());

  //Desenha o ponto especial (s\u00f3 quando foram comidos mais de 1/2 e menos de 3/4 dos pontos iniciais)
  if (drawn_points<(initial_points_mp/2) && drawn_points>(initial_points_mp/4)) {
    drawSpecialPoint();
    //Detecta se o Pacman come o ponto especial
    detectSpecialPoint();
  }

  //Desenha o fantasma inicial
  if (!ghost_dead) desenharFantasma(1, 0);

  //Consoante a tecla pressionada, desenha o fantasma correspondente
  if (keyPressed) {
    if (!ghost_dead) desenharFantasma(1, red_ghost_img);
    if (ghost_dead) image(death_ghost, red.px, red.py, 30, 30);       //Se o fantasma estiver morto (ponto especial)
  } else if (!keyPressed) {
    if (!ghost_dead) desenharFantasma(1, red_ghost_img);
    if (ghost_dead) image(death_ghost, red.px, red.py, 30, 30);
  }
}
//-----------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que termina o jogo mostrando uma mensagem e retornando ao menu
public void endGame(int winner) {
  //Garante que o som de gameover \u00e9 reproduzido mas n\u00e3o entra em loop
  delay(2000);
  sound[1].close();

  //Reinicia o jogo
  setup();

  //UI: Limite
  fill(0, 0);
  stroke(ui);
  strokeWeight(espacamento);
  rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

  //Texto "Game Over"
  PFont f=createFont(font, 50, false);
  textFont(f);
  fill(ui);
  text("Game Over", width/2, (height/4));

  //Indica quem ganhou
  textSize(30);
  if (winner==1) {
    text("Pacman venceu", width/2, (height/4)+50);
    //Calcula a pontua\u00e7\u00e3o
    score=(max_points-drawn_points)*factor;

    //Print Pontua\u00e7\u00e3o para ficheiro
    try {   //Lida com IOExceptions
      //Garante que s\u00f3 \u00e9 executado 1 vez (a fun\u00e7\u00e3o showScores \u00e9 chamada na fun\u00e7\u00e3o draw e portanto chamada v\u00e1rias vezes)
      if (called_saveScores) {
        if (max_points==initial_points_sp) saveScores_File(score, 1);     //Singleplayer
        if (max_points==initial_points_mp) saveScores_File(score, 2);     //Multiplayer
        called_saveScores=false;
      }
    }
    catch (IOException e) {
    };
  } else if (winner==2) {
    text("Pacman foi eliminado", width/2, (height/2));
    //Calcula a pontua\u00e7\u00e3o
    score=(max_points-drawn_points)*factor;

    //Print Pontua\u00e7\u00e3o para ficheiro
    try {
      //Garante que s\u00f3 \u00e9 executado 1 vez (a fun\u00e7\u00e3o showScores \u00e9 chamada na fun\u00e7\u00e3o draw e portanto chamada v\u00e1rias vezes)
      if (called_saveScores) {
        if (max_points==initial_points_sp) saveScores_File(score, 1);     //Singleplayer
        if (max_points==initial_points_mp) saveScores_File(score, 2);     //Multiplayer
        called_saveScores=false;
      }
    }
    catch (IOException e) {
    };
  }

  text("Pontua\u00e7\u00e3o:", (width/2)-50, (height/2)+50);
  textAlign(LEFT);
  text(score, (width/2)+60, (height/2)+50);

  //Mensagem para retornar ao menu
  textSize(15);
  textAlign(CENTER);
  text("Pressione ESC para voltar", width/2, 500);

  //Retorna ao menu;
  gamestate=0;
}
//-----------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que imprime o ecr\u00e3 de ajuda
public void showHelp() {
  //Limite
  fill(0, 0);
  stroke(ui);
  strokeWeight(espacamento);
  rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

  PFont f=createFont(font, 30, false);
  textFont(f);
  fill(0xffFFF308);

  //T\u00edtulo
  textAlign(CENTER);
  text("Ajuda & Op\u00e7\u00f5es", width/2, 60);

  //Subt\u00edtulos
  textAlign(CENTER);
  textSize(22);
  text("Controlos", (width/2), 110);
  text("Op\u00e7\u00f5es", (width/2), 420);

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

  //Op\u00e7\u00f5es
  textAlign(LEFT);
  text("C", (width/7)-50, 460);
  text("|  Definir cor do labirinto aleatoriamente", (width/7)+50, 460);

  textSize(10);
  textAlign(CENTER);
  text("Autores: Maria Lavoura | Pedro Veloso Teixeira", (width/2), 500);

  /* Texto: setas direcionais
   * necess\u00e1rio utilizar outra fonte pois LithosPro-Black n\u00e3o possui estes caracteres) */
  PFont f1=createFont("Arial", 30, false);
  textFont(f1);
  textSize(19);
  textAlign(LEFT);
  text("\u2190 \u2192 \u2191 \u2193", (width/7)-50, 160); // u2... : UNICODE do caracter
}
//-----------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que imprime o ecr\u00e3 das pontua\u00e7\u00f5es no ecr\u00e3
public void showScores () {
  //Limite
  fill(0, 0);
  stroke(ui);
  strokeWeight(espacamento);
  rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

  PFont f=createFont(font, 30, false);
  textFont(f);
  fill(ui);

  //T\u00edtulo
  textAlign(CENTER);
  text("Pontua\u00e7\u00f5es M\u00e1ximas", width/2, 60);

  //Subt\u00edtulos
  textAlign(LEFT);
  textSize(22);
  text("1 Jogador", (width/7), 110);
  text("2 Jogadores", 4*(width/7)+50, 110);

  //Imprimir pontua\u00e7\u00f5es (1 para single player, 2 para multiplayer)
  try {         //Lida com IOExceptions
    scores1=readScores_File(1, num_scores);
    scores2=readScores_File(2, num_scores);
  }
  catch (IOException e) {
  };
  printScores(scores1, (width/7), 160);
  printScores(scores2, 4*(width/7)+50, 160);
}
//-----------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que retoma (soundEnabled=true) ou pausa (soundEnabled=false) a reprodu\u00e7\u00e3o dos sons
public void pauseSounds() {
  if (!soundEnabled) {
    sound[0].mute();
    sound[1].mute();
    sound[2].mute();
  }

  if (soundEnabled) {
    sound[0].unmute();
    sound[1].unmute();
    sound[2].unmute();
  }
}
//Fun\u00e7\u00e3o que executa o som de fim de jogo
public void gameoverSound() {
  sound[0].close();
  sound[2].close();
  noLoop();
  sound[1].play();
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que desenha o Pacman
public void desenharPacman(float start, float stop) {
  fill(232, 239, 40);
  arc(px_pac, py_pac, pRaio, pRaio, stop, start);
}
//-----------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que desenha os fantasmas (i: imagem do fantasma a ser carregada; j: fantasma a ser desenhado)
public void desenharFantasma(int i, int j) {
  imageMode(CENTER);
  switch (i) {
  case 1:
    image(red.images[j], red.px, red.py, 30, 30);
    break;
  case 2:
    image(pink.images[j], pink.px, pink.py, 30, 30);
    break;
  case 3:
    image(orange.images[j], orange.px, orange.py, 30, 30);
    break;
  case 4:
    image(blue.images[j], blue.px, blue.py, 30, 30);
    break;
  }
}
//-----------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que move o fantasma (persegue o Pacman)
public void moveGhost() {

  //red ghost
  // v
  red.vx=0;
  red.vy= red.set_vy;

  if (red.py==centroY(3) && (red.px>centroX(7))) {// <
    red.vx =-red.set_vx ;
    red.vy=0;
  } else if ((red.px==centroX(7)) && (red.py==centroY(3))) {// v
    red.vx=0;
    red.vy= red.set_vy;
  } else if ((red.py==centroY(5))&&(red.px!=centroX(1))) {// <
    red.vx =-red.set_vx ;
    red.vy=0;
  } else if (red.px==centroX(1) && (red.py!=centroY(1))) {// ^
    red.vx =0;
    red.vy=-red.set_vx ;
  } else if ((red.py==centroY(1))&&(red.px!=centroX(13))) {// >
    red.vx = red.set_vy;
    red.vy=0;
  }

  //pink ghost
  // ^
  pink.vx=0;
  pink.vy=-pink.set_vy;

  if (pink.py>centroY(2) && (pink.px==centroX(4))) {// ^
    pink.vx=0;
    pink.vy=-pink.set_vy;
  } else if (pink.py==centroY(2) && (pink.px<centroX(9))) {// >
    pink.vx=pink.set_vx;
    pink.vy=0;
  } else if ((pink.px==centroX(9)) && (pink.py<centroY(4))) {// v
    pink.vx=0;
    pink.vy=pink.set_vy;
  } else if ((pink.py==centroY(4))&&(pink.px<centroX(11))) {// >
    pink.vx=pink.set_vx;
    pink.vy=0;
  } else if ((pink.px==centroX(11)) && (pink.py<centroY(9))) {// v
    pink.vx=0;
    pink.vy=pink.set_vy;
  } else if ((pink.py==centroY(9))&&(pink.px>centroX(6))) {// <
    pink.vx=-pink.set_vx;
    pink.vy=0;
  } else if ((pink.px==centroX(6)) && (pink.py>centroY(7))) {// ^
    pink.vx=0;
    pink.vy=-pink.set_vy;
  } else if ((pink.py==centroY(7))&&(pink.px>centroX(4))) {// <
    pink.vx=-pink.set_vx;
    pink.vy=0;
  }

  //orange ghost
  if ((orange.py==centroY(10))&&(orange.px>centroX(8))) {// <
    orange.vx=-orange.set_vx;
    orange.vy=0;
  } else if (orange.px==centroX(8) && (orange.py>centroY(6))&& (orange.py>centroY(9))) {// ^
    orange.vx=0;
    orange.vy=-orange.set_vy;
  } else if (orange.py==centroY(6) && (orange.px<centroX(14))) {// >
    orange.vx=orange.set_vx;
    orange.vy=0;
  } else if ((orange.px==centroX(14)) && (orange.py<centroY(10))) {// v
    orange.vx=0;
    orange.vy=orange.set_vy;
  } else if ((orange.py==centroY(10))&&(orange.px>centroX(1))) {// <
    orange.vx=-orange.set_vx;
    orange.vy=0;
  } else if (orange.px==centroX(8) && (orange.py>centroY(6))&& (orange.py>centroY(9))) {// ^
    orange.vx=0;
    orange.vy=-orange.set_vy;
  }


  // blue ghost
  if (blue.py>centroY(8) && (blue.px==centroX(1))) {// ^
    blue.vx=0;
    blue.vy=-blue.set_vy;
  } else if (blue.py==centroY(8) && (blue.px<centroX(2))) {// >
    blue.vx=blue.set_vx;
    blue.vy=0;
  } else if (blue.px==centroX(2) && (blue.py>centroY(5))&& (blue.py<centroY(9))) {// ^
    blue.vx=0;
    blue.vy=-blue.set_vy;
  } else if (blue.py==centroY(5) && (blue.px<centroX(6))) {// >
    blue.vx=blue.set_vx;
    blue.vy=0;
  } else if ((blue.px==centroX(6)) && (blue.py<centroY(10))) {// v
    blue.vx=0;
    blue.vy=blue.set_vy;
  } else if ((blue.py==centroY(10))&&(blue.px>centroX(1))) {// <
    blue.vx=-blue.set_vx;
    blue.vy=0;
  }

  //Deteca colis\u00f5es entre os fantasmas e o Pacman
  if (dist(px_pac, py_pac, red.px, red.py)<10)
    detectedColision=1;
  else if (dist(px_pac, py_pac, pink.px, pink.py)<10)
    detectedColision=1;
  else if (dist(px_pac, py_pac, orange.px, orange.py)<10)
    detectedColision=1;
  else if (dist(px_pac, py_pac, blue.px, blue.py)<10)
    detectedColision=1;
  else {
    detectedColision=0;
  }
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Fun\u00e7\u00e3o chamada quando existe input do teclado
public void keyPressed() {

  //Move o Fantasma (WASD)
  if (gamestate==2) {      //Garante que s\u00f3 \u00e9 possivel controlar o fantasma no modo Multijogador
    //Left A Key
    if ( key == 'a' || key == 'A' ) {
      float cx = red.px-50;                //Para a c\u00e9lula ao lado esquerdo da actual
      float cy = red.py;                   //...da mesma linha
      int c = get((int)cx, (int)cy);       //Obt\u00e9m a cor dessa c\u00e9lula

      if (c != corObstaculos) {              //Se essa c\u00e9lula n\u00e3o for obst\u00e1culo
        red.px = red.px - 50;             //Move o fantasma
      }
      red_ghost_img=2;
      //Impede o fantasma de sair da janela
      if (red.px < centroX(1)) {
        red.px = red.px + 50;
      }
    }

    //----------------------------------------------
    //Right D Key
    if ( key == 'd' || key == 'D' ) {
      float cx = red.px+50;
      float cy = red.py;
      int c = get((int)cx, (int)cy);
      red_ghost_img=3;
      if (c != corObstaculos) {
        red.px = red.px + 50;
      }

      if (red.px > centroX(nCol)) {
        red.px = red.px - 50;
      }
    }

    //----------------------------------------------
    //Up W Key
    if ( key == 'w' || key == 'W' ) {
      float cx = red.px;
      float cy = red.py-50;
      int c = get((int)cx, (int)cy);
      red_ghost_img=1;
      if (c != corObstaculos) {
        red.py = red.py - 50;
      }

      if (red.py < centroY(1)) {
        red.py = red.py + 50;
      }
    }

    //----------------------------------------------
    //Down S Key
    if ( key == 's' || key == 'S' ) {
      float cx=red.px;
      float cy=red.py+50;
      int c = get((int)cx, (int)cy);
      red_ghost_img=0;
      if (c != corObstaculos) {
        red.py = red.py + 50;
      }

      if (red.py > centroY(nLin)) {
        red.py = red.py - 50;
      }
    }
  }
  //------------------------------------------------------------------------------------------------------------
  //Move o Pacman (arrow keys)

  //Left Arrow Key
  if ( keyCode == LEFT ) {
    float cx = px_pac-50;              //Para a c\u00e9lula ao lado esquerdo da actual
    float cy = py_pac;                 //...da mesma linha
    int c = get((int)cx, (int)cy);   //Obt\u00e9m a cor dessa c\u00e9lula
    sound[2].play(0);
    if (c != corObstaculos) {          //Se essa c\u00e9lula n\u00e3o for obst\u00e1culo
      px_pac = px_pac - 50;            //Move o Pacman
    }

    if (px_pac < centroX(1)) { //impede pacman sair da janela
      px_pac = px_pac + 50;
    }

    dir=1;
  }
  //----------------------------------------------
  //Right Arrow Key
  if ( keyCode == RIGHT ) {
    float cx = px_pac+50;
    float cy = py_pac;
    int c = get((int)cx, (int)cy);
    sound[2].play(0);
    if (c != corObstaculos) {
      px_pac = px_pac +  50;
    }

    if (px_pac > centroX(nCol)) {
      px_pac = px_pac - 50;
    }

    dir=2;
  }
  //----------------------------------------------
  //Up Arrow Key
  if ( keyCode == UP ) {
    float cx = px_pac;
    float cy = py_pac-50;
    int c = get((int)cx, (int)cy);
    sound[2].play(0);
    if (c != corObstaculos) {
      py_pac = py_pac - 50;
    }

    if (py_pac < centroY(1)) {
      py_pac = py_pac + 50;
    }

    dir=3;

  }
  //----------------------------------------------
  //Down Arrow Key
  if ( keyCode == DOWN ) {
    float cx=px_pac;
    float cy=py_pac+50;
    int c = get((int)cx, (int)cy);
    sound[2].play(0);
    if (c != corObstaculos) {
      py_pac = py_pac + 50;
    }

    if (py_pac > centroY(nLin)) {
      py_pac = py_pac - 50;
    }

    dir=4;
  }
  //------------------------------------------------------------------------------------------------------------
  //Detecta colis\u00f5es
  if ((px_pac==red.px) && (py_pac==red.py) || (px_pac==pink.px) && (px_pac==pink.py) || (px_pac==orange.px) && (px_pac==orange.py) ) {
    detectedColision=1;
  } else {
    detectedColision=0;
  }

  //------------------------------------------------------------------------------------------------------------
  //Intera\u00e7\u00e3o com o utilizador
  //Pausa o jogo
  PFont f=createFont(font, 30, false);
  textFont(f);
  fill(ui);

  if ((key == 'P') || (key == 'p') || (key == ' ')) {
    if (gamestate==1 || gamestate==2) {      //Garante que s\u00f3 entra em pausa se estiver no jogo
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
      old_gamestate=gamestate;  //Permite retornar ao ecr\u00e3 anterior
      gamestate=4;
    } else if (gamestate==4) {
      gamestate=old_gamestate;
    }
  }

  //Reinicia o jogo/retorna ao menu
  if (key == ESC) {
    key=0;
    sound[0].close();		//Garante que quando o jogo sai de um estado Game Over, n\u00e3o s\u00e3o repetidos sons
    sound[1].close();
    sound[2].close();
    loop();             //E que o ecr\u00e3 \u00e9 actualizado
    setup();
  }

  //Aleatoriza a cor dos obst\u00e1culos
  if (key == 'c' || key == 'C') {
    do {
      corObstaculos=color(random(255), random(255), random(40, 255));     //random(40,255) impede um Brightness menor que 40, impedido cores demasiado claras
    } while (corObstaculos==0xff000000);  //Impede que a cor do obst\u00e1culo seja preto
  }

  //Desliga/Liga o Som
  if (key == 'm' || key == 'M') {
    if (soundEnabled) {
      soundEnabled=false;
    } else if (!soundEnabled) {
      soundEnabled=true;
    }
    pauseSounds();
  }
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Fun\u00e7\u00f5es que rodam o Pacman
public float rotatePacmanStop() {
  if (dir==1) {
    return radians(135);
  } else {
    if (dir==2) {
      return radians(315);
    } else {
      if (dir==3) {
        return radians(225);
      } else {
        if (dir==4) {
          return radians(45);
        } else {
          return radians(315);
        }
      }
    }
  }
}

public float rotatePacmanStart() {
  if (dir==1) {
    return radians(-135);
  } else {
    if (dir==2) {
      return radians(45);
    } else {
      if (dir==3) {
        return radians(-45);
      } else {
        if (dir==4) {
          return radians(-225);
        } else {
          return radians(45);
        }
      }
    }
  }
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que preenche o array com "2" nas coordenadas por onde o pac passou, o que vai impedir de serem desenhadas bolas neste sitio
public void caminhoPac() {
  for (int i=1; i<=nCol; i++) {
    for (int j=1; j<=nLin; j++) {
      if ((px_pac==i)&&(py_pac==j)) {
        comida[i][j]=2;
      }
    }
  }
}
//------------------------------------------------------------------------------------
//Fun\u00e7\u00f5es que desenham os labirintos para o modo Singleplayer (SP) e Multi Player (MP)
public void desenharLabirintoSP () {

  //Desenha a fronteira da \u00e1rea de jogo
  fill(0);
  stroke(corObstaculos);
  strokeWeight(espacamento);
  rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

  //Desenha obst\u00e1culos
  desenharObstaculo(1, 6, 1, 2); // A
  desenharObstaculo(2, 2, 2, 3); // B
  desenharObstaculo(2, 9, 3, 1); //C
  desenharObstaculo(5, 6, 1, 1); //D
  desenharObstaculo(6, 3, 1, 1); //E
  desenharObstaculo(9, 8, 1, 1); //F
  desenharObstaculo(10, 2, 3, 1); //G
  desenharObstaculo(10, 5, 1, 1); //H
  desenharObstaculo(12, 7, 2, 3); //I
  desenharObstaculo(14, 4, 1, 2); //J
  desenharObstaculo(7, 6, 1, 2); //k
  desenharObstaculo(8, 4, 1, 2); //l
  desenharObstaculo(12, 4, 1, 2); //m
  desenharObstaculo(3, 6, 1, 2); //n
  //desenharObstaculo(2, 4, 1, nLin-4);
  //desenharObstaculo(5, 4, nCol-4, nLin-4);

  /* Desenha um obst\u00e1culo interno de um labirinto:
   * x: \u00edndice da c\u00e9lula inicial segundo eixo dos X - gama (1..nCol)
   * y: \u00edndice da c\u00e9lula inicial segundo eixo dos Y - gama (1..nLin)
   * numC: n\u00ba de colunas (c\u00e9lulas) segundo eixo dos X (largura do obst\u00e1culo)
   * numL: n\u00ba de linhas (c\u00e9lulas) segundo eixo dos Y (altura do obst\u00e1culo)
   */
}

public void desenharLabirintoMP () {
  //Desenha a fronteira da \u00e1rea de jogo
  fill(0);
  stroke(corObstaculos);
  strokeWeight(espacamento);
  rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

  //Desenha obst\u00e1culos
  desenharObstaculo(2, 2, 1, 1); // A
  desenharObstaculo(2, 2, 1, 1); // B
  desenharObstaculo(2, 9, 1, 1);//C
  desenharObstaculo(4, 4, 2, 1);//D
  desenharObstaculo(4, 7, 2, 1);//E
  desenharObstaculo(6, 2, 4, 1);//F
  desenharObstaculo(7, 5, 2, 2);//G
  desenharObstaculo(6, 9, 4, 1);//H
  desenharObstaculo(10, 4, 2, 1);//I
  desenharObstaculo(10, 7, 2, 1);//J
  desenharObstaculo(13, 2, 1, 1);//K
  desenharObstaculo(13, 9, 1, 1);//L
  desenharObstaculo(13, 5, 1, 2);//M
  desenharObstaculo(13, 7, 2, 1);
  //desenharObstaculo(2, 4, 1, nLin-4);
  //desenharObstaculo(5, 4, nCol-4, nLin-4);
}
//-----------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que desenha obst\u00e1culos
public void desenharObstaculo (int x, int y, int numC, int numL) {
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
//-----------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que desenha pontos
/* Desenhar pontos nas c\u00e9lulas vazias (que n\u00e3o fazem parte de um obst\u00e1culo).
 * Esta fun\u00e7\u00e3o usa a cor de fundo no ecr\u00e3 para determinar se uma c\u00e9lula est\u00e1 vazia ou se faz parte de um obst\u00e1culo.
 */
public int desenharPontos() {
  float cx, cy;

  ellipseMode(CENTER);
  fill(255);
  noStroke();

  drawn_points=0;   //Reset do n\u00famero de pontos desenhados

  // Insere um ponto nas c\u00e9lulas vazias
  for (int i=1; i<=nCol; i++) {
    for (int j=1; j<=nLin; j++) {
      cx = centroX(i);
      cy = centroY(j);
      int c = get((int)cx, (int)cy);
      if ((comida[i-1][j-1] == 1)&&(c != corObstaculos)) { //impedir que as bolas sejam desenhadas nos obstaculos e em sitios onde o pac passou
        fill(255);
        ellipse(cx, cy, pRaio/2, pRaio/2);
        drawn_points++;                                   //Conta quantos pontos s\u00e3o desenhados
      }
    }
  }
  return drawn_points;                                    //Devolve quantos pontos s\u00e3o desenhados
}
//-----------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que inicializa/preenche o array com as coordenadas da comida (ie pontos)
public void arrayComida() {
  for (int i=1; i<=nCol; i++) {
    for (int j=1; j<=nLin; j++) {
      comida[i-1][j-1] = 1;
    }
  }
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que preeche o array com as coordenadas da comida
public void posicaoComida() {

  for (int i=1; i<=nCol; i++) {
    for (int j=1; j<=nLin; j++) {
      if ((get((int)i, (int)j) != corObstaculos)) { //impedir que as bolas sejam desenhadas nos obstaculos e em sitios onde o pac passou
        comida[i-1][j-1]= 1;
      }
    }
  }
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Transformar o \u00edndice de uma c\u00e9lula em coordenada no ecr\u00e3
public float centroX(int col) {
  return margemH + (col - 0.5f) * tamanho;
}

public float centroY(int lin) {
  return margemV + (lin - 0.5f) * tamanho;
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Fun\u00e7\u00e3o que desenha um ponto especial
public void drawSpecialPoint () {
  if (frameCount % 60 == 0 ) {
    blinker = !blinker;
  }

  if (blinker) {
    fill(color_specialpoint);
    ellipse(centroX(px_specialpoint), centroY(py_specialpoint), pRaio/4, pRaio/4);
  }
  drawnSpecialPoint=true;
}

//Fun\u00e7\u00e3o que detecta colis\u00e3o do Pacman com o ponto especial
public void detectSpecialPoint () {
  if (drawnSpecialPoint==true) {
    if ( px_pac==centroX(px_specialpoint) && py_pac==centroY(py_specialpoint) ) {

      //Oculta o ponto especial
      fill(232, 239, 40);
      ellipse(centroX(px_specialpoint), centroY(py_specialpoint), pRaio/4, pRaio/4);

      //Evidencia visualmente que o ponto foi comido
      int radiation=2;
      do{
        noFill();
        stroke(0xffff0000);
        ellipse(centroX(px_specialpoint), centroY(py_specialpoint), pRaio*radiation, pRaio*radiation);
        radiation+=4;
      }while(radiation<60);

      //Mata os fantasmas
      ghost_dead=true;

      //Termina o jogo
      gamestate=5;
      win=1;
    }
  }
}

//-----------------------------------------------------------------------------------------------------------------------------------------
//Fun\u00e7\u00f5es para Pontua\u00e7\u00f5es

//Fun\u00e7\u00e3o que obt\u00e9m pontua\u00e7\u00f5es (do Singleplayer - 1, do Mulitplayer - 2) de um ficheiro
public int[] readScores_File (int n, int j) throws IOException {

  //Decide qual o nome do ficheiro a ler
  String path = "";
  switch (n) {
  case 1:
    path=dataPath("scores_singleplayer.txt");   //Na pasta data do programa, o ficheiro "scores_singleplayer.txt"
    break;
  case 2:
    path=dataPath("scores_multiplayer.txt");
    break;
  }

  //Scanner para ler do ficheiro
  File file = new File (path);
  Scanner in = new Scanner (file);

  //Array tempor\u00e1ria com todos os valores do ficheiro
  int array_temp[] = new int[(int)file.length()];
  int i=0;

  while (in.hasNextInt()) {
    String s = in.nextLine();
    array_temp[i] = Integer.parseInt(s);
    i++;
  }

  in.close();

  //Ordenar os valores (ordem decrescente)
  orderArray(array_temp);

  /* Determina a dimens\u00e3o, dim, da nova array
   * Se o ficheiro tiver array_temp.length valores e se for pedido uma array com j valores
   * Se n<j, dim=n; sen\u00e3o, se n>j, dim=j
   */

  int dim=0;
  if (array_temp.length<j) dim=array_temp.length;
  else dim=j;

  //Criar uma nova array com os dim valores (neste caso pontua\u00e7\u00f5es) pretendidas
  int array[]=new int[dim];
  for (int k=0; k<dim; k++) {
    array[k]=array_temp[k];
  }
  return array;
}

//Fun\u00e7\u00e3o que imprime 1 pontua\u00e7\u00e3o (do Singleplayer - 1, do Multiplayer - 2) num ficheiro
public void saveScores_File (int num, int n) throws IOException {
  //Decide qual o nome do ficheiro a ler
  String path = "";
  switch (n) {
  case 1:
    path=dataPath("scores_singleplayer.txt");
    break;
  case 2:
    path=dataPath("scores_multiplayer.txt");
    break;
  }

  //Scanner para escrever no ficheiro
  File file = new File (path);
  FileWriter tmp = new FileWriter(file, true);
  PrintWriter out = new PrintWriter(tmp);

  //Imprime o n\u00famero no fim do ficheiro
  out.println(num);
  out.close();
}

//Fun\u00e7\u00e3o que ordena por ordem decrescente os valores de uma array.
/* Tamb\u00e9m teria sido poss\u00edvel utilizar a fun\u00e7\u00e3o sort() para ordenar e reverse() para inverter a ordem
 */
public void orderArray (int[] array) {
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

//Fun\u00e7\u00e3o que imprime as pontua\u00e7\u00f5es no ecr\u00e3
public void printScores (int array[], int col, int lin) {
  //col e lin d\u00e3o a posi\u00e7\u00e3o do texto (horizontal e vertical)
  //Formata\u00e7\u00e3o
  PFont f=createFont(font, 30, false);
  textFont(f);
  fill(ui);
  textSize(18);
  textAlign(LEFT);

  for (int i=0; i<array.length; i++) {
    text(array[i], col, lin+i*40);
  }
}
//Par\u00e2metros dos fantasmas
class Ghost {
  float px, py;                       //Posi\u00e7\u00e3o
  float vx, vy;                       //Velocidade
  float set_vx, set_vy;               //M\u00f3dulo da velocidade
  PImage[] images= new PImage[4];     //Imagens
}
  public void settings() {  size(720, 520); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "pacman" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
