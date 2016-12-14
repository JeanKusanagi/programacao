/* Maria Lavoura N. Mec. 84681
 * Pedro Teixeira N. Mec. 84715
 *
 * Programação I | Trabalho Prático
 * Turma P8
 */

//-----------------------------------------------------------------------------------------------------------------------------------------
//Importação de bibliotecas utilizadas
import java.io.*;
import java.util.Scanner;
import ddf.minim.*;                             //Som

//Parâmetros do labirinto
int nCol, nLin;                                 //Nº de linhas e de colunas
int tamanho = 50;                               //Tamanho (largura e altura) das células do labirinto
int espacamento = 2;                            //Espaço livre entre células
float margemV, margemH;                         //Margem livre na vertical e na horizontal para assegurar que as células são quadrangulares
color corObstaculos;                            //Cor de fundo dos obstáculos (original RGB: 100,0,128)
color cor_default = color(145, 168, 208);
color ui=#FFF308;                               //Cor do texto e dos limites dos menus
String font="LithosPro-Black.otf";              //Fonte da UI

//Parâmetros do Pacman
float px_pac, py_pac, pRaio;                    //Posição
float vx_pac, vy_pac;                           //Velocidade
int dir;                                        //Direcção e sentido do Pacman (modo multijogador)
                                                //Evita que o Pacman volte à sua rotação original quando o fantasma move-se

//Inicialização dos fantasmas
Ghost red=new Ghost();
Ghost pink=new Ghost();
Ghost orange=new Ghost();
Ghost blue=new Ghost();
int red_ghost_img;                              //No modo Singleplayer, determina qual a imagem a desenhar consoante a tecla pressionada
PImage death_ghost;                             //Imagem do fantasma morto

//Estado do jogo (0=Menu, 1=Single Player, 2=Multiplayer, 3=Pontuações, 4=Ajuda, 5=Gameover)
int gamestate, old_gamestate;

//Estado do jogo (novo jogo)
boolean justStarted = true;

//Detectada colisão? (1=Sim, 2=Não)
int detectedColision;

//Vencedor (1=Pacman, 2=Fantasma)
int win;

//Som
Minim minim;
AudioPlayer sound[]=new AudioPlayer[3];       //Array com as músicas utilizadas
boolean soundEnabled=true;                    //Som activo/desactivado

//Array bolas=comida
float comida[][];

//Pontuações (1=Single Player, 2=Multiplayer)
int max_points;
int initial_points_sp=120;                    //Número de pontos do labirinto do modo Single Player
int initial_points_mp=116;                    //Número de pontos do labirinto do modo Multiplayer
int drawn_points;                             //Número de pontos desenhados (gameover quando drawn_points=0)
int score;                                    //Pontuação do jogo (pontuação=[pontuação máxima-drawn_points]*factor)
int factor=100;                               //Quanto vale cada ponto em termos de pontuação
int scores1[]=new int[10];                    //Array de inteiros com as pontuações para o modo Single Player
int scores2[]=new int[10];                    //Array de inteiros com as pontuações para o modo Multi Player
int num_scores=8;                             //Número de pontuações máximas a apresentar
boolean called_saveScores=true;

//Ponto especial
boolean blinker;                              //Permite tornar o ponto intermitente
int px_specialpoint=14;                        //Posição do ponto
int py_specialpoint=6;
color color_specialpoint=#990000;             //Cor do ponto
boolean drawnSpecialPoint;                    //O ponto foi desenhado?
boolean ghost_dead;                           //O ponto foi comido -- os fantasmas foram mortos?

//-----------------------------------------------------------------------------------------------------------------------------------------
//Função que começa o jogo. Executada quando o programa inicia ou reinicia (através da tecla ESC)
void setup() {
  println("Trabalho Pratico de Programacao I : Pacman Multijogador");
  println("DETI - UA");
  println("Maria Lavoura, N. Mec. 84681");
  println("Pedro Teixeira, N. Mec. 84715");
  println("Jogo Iniciado");
  println(" ");

  //Tamanho, título e ícone da janela
  size(720, 520);
  background(0);
  surface.setTitle("Pacman Multijogador | Maria Lavoura | Pedro Teixeira");
  PImage icon = loadImage(dataPath("icon.png"));
  surface.setIcon(icon);

  //Número de linhas e de colunas
  nCol = (int)width/tamanho;
  nLin = (int)height/tamanho;

  //Assegurar que nº de linhas e nº de colunas é maior ou igual a 5
  assert nCol >= 5 && nLin >= 5;

  //Determinar margens para limitar a área útil do jogo
  margemV = (width - nCol * tamanho) / 2.0;
  margemH = (height - nLin * tamanho) / 2.0;

  //Inicialização da cor dos obstáculos
  corObstaculos=cor_default;

  //Inicalização da posição inicial do Pacman
  px_pac = centroX(2);
  py_pac = centroY(1);
  pRaio = (tamanho - espacamento) / 2;

  //Inicalização dos parâmetros dos fantasmas
  red.px = centroX(13);      //Posições iniciais
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

  //Inicialização das imagens
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

  //Inicializar os sons  (1: Som de Início, 2: Som de Fim de Jogo, 3: Som Comer Ponto)
  minim = new Minim(this);
  sound[0]=minim.loadFile("start.mp3");
  sound[1]=minim.loadFile("gameover.mp3");
  sound[2]=minim.loadFile("eatpoint.mp3");

  //Som de Início
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
//Função que executa os diferentes modos do jogo (0: Menu, 1: Jogo Single Player, 2: Jogo Multiplayer, 3: Pontuações, 4: Ajuda, 5: Gameover)
void draw() {
  switch (gamestate) {

  case 0:   //Mostra as opções
    background(0);      //Limpa o ecrã
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
    gameoverSound();
    endGame(win);
    break;
  }

  if (gamestate==1) {
    //Sinaliza que o Pacman esteve numa célula
    comida[((int)px_pac - 35)/50][((int)py_pac - 35)/50] = 2.0;
    startGame();

    //Detecta quando o Pacman ganha (não são desenhados mais pontos)
    if (drawn_points==0) {
      gamestate=5;
      win=1;
    }
  }

  if (gamestate==2) {
    comida[((int)px_pac - 35)/50][((int)py_pac - 35)/50] = 2.0;
    startGameMultiplayer();

    if (drawn_points==0) {
      gamestate=5;
      win=1;
    }
  }

  //Detecta quando o Pacman perde (colisão entre um fantasma e o Pacman)
  if (detectedColision==1) {
    gamestate=5;
    win=2;
  }
}
//-----------------------------------------------------------------------------------
//Função que imprime o menu inicial
void showMenu() {
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
  text("3 | Pontuações", width/2, height/2+190);
  textSize(15);
  text("H | Ajuda e Opções", width/2, height/2+230);
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
//Função que executa o jogo no modo Singleplayer
void startGame() {
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

  //Desenha o ponto especial (só quando foram comidos mais de 1/2 e menos de 3/4 dos pontos iniciais)
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

  //Define o módulo das velocidades dos fantasmas
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
//Função que começa o jogo no modo Multiplayer
void startGameMultiplayer() {
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

  //Desenha o ponto especial (só quando foram comidos mais de 1/2 e menos de 3/4 dos pontos iniciais)
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
//Função que termina o jogo mostrando uma mensagem e retornando ao menu
void endGame(int winner) {
  //Garante que o som de gameover é reproduzido mas não entra em loop
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
    //Calcula a pontuação
    score=(max_points-drawn_points)*factor;

    //Print Pontuação para ficheiro
    try {   //Lida com IOExceptions
      //Garante que só é executado 1 vez (a função showScores é chamada na função draw e portanto chamada várias vezes)
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
    //Calcula a pontuação
    score=(max_points-drawn_points)*factor;

    //Print Pontuação para ficheiro
    try {
      //Garante que só é executado 1 vez (a função showScores é chamada na função draw e portanto chamada várias vezes)
      if (called_saveScores) {
        if (max_points==initial_points_sp) saveScores_File(score, 1);     //Singleplayer
        if (max_points==initial_points_mp) saveScores_File(score, 2);     //Multiplayer
        called_saveScores=false;
      }
    }
    catch (IOException e) {
    };
  }

  text("Pontuação:", (width/2)-50, (height/2)+50);
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
//Função que imprime o ecrã de ajuda
void showHelp() {
  //Limite
  fill(0, 0);
  stroke(ui);
  strokeWeight(espacamento);
  rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

  PFont f=createFont(font, 30, false);
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
  text("Autores: Maria Lavoura | Pedro Veloso Teixeira", (width/2), 500);

  /* Texto: setas direcionais
   * necessário utilizar outra fonte pois LithosPro-Black não possui estes caracteres) */
  PFont f1=createFont("Arial", 30, false);
  textFont(f1);
  textSize(19);
  textAlign(LEFT);
  text("\u2190 \u2192 \u2191 \u2193", (width/7)-50, 160); // u2... : UNICODE do caracter
}
//-----------------------------------------------------------------------------------
//Função que imprime o ecrã das pontuações no ecrã
void showScores () {
  //Limite
  fill(0, 0);
  stroke(ui);
  strokeWeight(espacamento);
  rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

  PFont f=createFont(font, 30, false);
  textFont(f);
  fill(ui);

  //Título
  textAlign(CENTER);
  text("Pontuações Máximas", width/2, 60);

  //Subtítulos
  textAlign(LEFT);
  textSize(22);
  text("1 Jogador", (width/7), 110);
  text("2 Jogadores", 4*(width/7)+50, 110);

  //Imprimir pontuações (1 para single player, 2 para multiplayer)
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
//Função que retoma (soundEnabled=true) ou pausa (soundEnabled=false) a reprodução dos sons
void pauseSounds() {
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
//Função que executa o som de fim de jogo
void gameoverSound() {
  sound[0].close();
  sound[2].close();
  noLoop();
  sound[1].play();
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Função que desenha o Pacman
void desenharPacman(float start, float stop) {
  fill(232, 239, 40);
  arc(px_pac, py_pac, pRaio, pRaio, stop, start);
}
//-----------------------------------------------------------------------------------
//Função que desenha os fantasmas (i: imagem do fantasma a ser carregada; j: fantasma a ser desenhado)
void desenharFantasma(int i, int j) {
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
//Função que move o fantasma (persegue o Pacman)
void moveGhost() {

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

  //Deteca colisões entre os fantasmas e o Pacman
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
//Função chamada quando existe input do teclado
void keyPressed() {

  //Move o Fantasma (WASD)
  if (gamestate==2) {      //Garante que só é possivel controlar o fantasma no modo Multijogador
    //Left A Key
    if ( key == 'a' || key == 'A' ) {
      float cx = red.px-50;                //Para a célula ao lado esquerdo da actual
      float cy = red.py;                   //...da mesma linha
      color c = get((int)cx, (int)cy);       //Obtém a cor dessa célula

      if (c != corObstaculos) {              //Se essa célula não for obstáculo
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
      color c = get((int)cx, (int)cy);
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
      color c = get((int)cx, (int)cy);
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
      color c = get((int)cx, (int)cy);
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
    float cx = px_pac-50;              //Para a célula ao lado esquerdo da actual
    float cy = py_pac;                 //...da mesma linha
    color c = get((int)cx, (int)cy);   //Obtém a cor dessa célula
    sound[2].play(0);
    if (c != corObstaculos) {          //Se essa célula não for obstáculo
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
    color c = get((int)cx, (int)cy);
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
    color c = get((int)cx, (int)cy);
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
    color c = get((int)cx, (int)cy);
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
  //Detecta colisões
  if ((px_pac==red.px) && (py_pac==red.py) || (px_pac==pink.px) && (px_pac==pink.py) || (px_pac==orange.px) && (px_pac==orange.py) ) {
    detectedColision=1;
  } else {
    detectedColision=0;
  }

  //------------------------------------------------------------------------------------------------------------
  //Interação com o utilizador
  //Pausa o jogo
  PFont f=createFont(font, 30, false);
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

  //Reinicia o jogo/retorna ao menu
  if (key == ESC) {
    key=0;
    sound[0].close();		//Garante que quando o jogo sai de um estado Game Over, não são repetidos sons
    sound[1].close();
    sound[2].close();
    loop();             //E que o ecrã é actualizado
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
    if (soundEnabled) {
      soundEnabled=false;
    } else if (!soundEnabled) {
      soundEnabled=true;
    }
    pauseSounds();
  }
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Funções que rodam o Pacman
float rotatePacmanStop() {
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

float rotatePacmanStart() {
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
//Função que preenche o array com "2" nas coordenadas por onde o pac passou, o que vai impedir de serem desenhadas bolas neste sitio
void caminhoPac() {
  for (int i=1; i<=nCol; i++) {
    for (int j=1; j<=nLin; j++) {
      if ((px_pac==i)&&(py_pac==j)) {
        comida[i][j]=2;
      }
    }
  }
}
//------------------------------------------------------------------------------------
//Funções que desenham os labirintos para o modo Singleplayer (SP) e Multi Player (MP)
void desenharLabirintoSP () {

  //Desenha a fronteira da área de jogo
  fill(0);
  stroke(corObstaculos);
  strokeWeight(espacamento);
  rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

  //Desenha obstáculos
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

  /* Desenha um obstáculo interno de um labirinto:
   * x: índice da célula inicial segundo eixo dos X - gama (1..nCol)
   * y: índice da célula inicial segundo eixo dos Y - gama (1..nLin)
   * numC: nº de colunas (células) segundo eixo dos X (largura do obstáculo)
   * numL: nº de linhas (células) segundo eixo dos Y (altura do obstáculo)
   */
}

void desenharLabirintoMP () {
  //Desenha a fronteira da área de jogo
  fill(0);
  stroke(corObstaculos);
  strokeWeight(espacamento);
  rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

  //Desenha obstáculos
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
//Função que desenha obstáculos
void desenharObstaculo (int x, int y, int numC, int numL) {
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
//Função que desenha pontos
/* Desenhar pontos nas células vazias (que não fazem parte de um obstáculo).
 * Esta função usa a cor de fundo no ecrã para determinar se uma célula está vazia ou se faz parte de um obstáculo.
 */
int desenharPontos() {
  float cx, cy;

  ellipseMode(CENTER);
  fill(255);
  noStroke();

  drawn_points=0;   //Reset do número de pontos desenhados

  // Insere um ponto nas células vazias
  for (int i=1; i<=nCol; i++) {
    for (int j=1; j<=nLin; j++) {
      cx = centroX(i);
      cy = centroY(j);
      color c = get((int)cx, (int)cy);
      if ((comida[i-1][j-1] == 1)&&(c != corObstaculos)) { //impedir que as bolas sejam desenhadas nos obstaculos e em sitios onde o pac passou
        fill(255);
        ellipse(cx, cy, pRaio/2, pRaio/2);
        drawn_points++;                                   //Conta quantos pontos são desenhados
      }
    }
  }
  return drawn_points;                                    //Devolve quantos pontos são desenhados
}
//-----------------------------------------------------------------------------------
//Função que inicializa/preenche o array com as coordenadas da comida (ie pontos)
void arrayComida() {
  for (int i=1; i<=nCol; i++) {
    for (int j=1; j<=nLin; j++) {
      comida[i-1][j-1] = 1;
    }
  }
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Função que preeche o array com as coordenadas da comida
void posicaoComida() {

  for (int i=1; i<=nCol; i++) {
    for (int j=1; j<=nLin; j++) {
      if ((get((int)i, (int)j) != corObstaculos)) { //impedir que as bolas sejam desenhadas nos obstaculos e em sitios onde o pac passou
        comida[i-1][j-1]= 1;
      }
    }
  }
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Transformar o índice de uma célula em coordenada no ecrã
float centroX(int col) {
  return margemH + (col - 0.5) * tamanho;
}

float centroY(int lin) {
  return margemV + (lin - 0.5) * tamanho;
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Função que desenha um ponto especial
void drawSpecialPoint () {
  if (frameCount % 60 == 0 ) {
    blinker = !blinker;
  }

  if (blinker) {
    fill(color_specialpoint);
    ellipse(centroX(px_specialpoint), centroY(py_specialpoint), pRaio/4, pRaio/4);
  }
  drawnSpecialPoint=true;
}

//Função que detecta colisão do Pacman com o ponto especial
void detectSpecialPoint () {
  if (drawnSpecialPoint==true) {
    if ( px_pac==centroX(px_specialpoint) && py_pac==centroY(py_specialpoint) ) {

      //Oculta o ponto especial
      fill(232, 239, 40);
      ellipse(centroX(px_specialpoint), centroY(py_specialpoint), pRaio/4, pRaio/4);

      //Evidencia visualmente que o ponto foi comido
      int radiation=2;
      do{
        noFill();
        stroke(#ff0000);
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
//Funções para Pontuações

//Função que obtém pontuações (do Singleplayer - 1, do Mulitplayer - 2) de um ficheiro
int[] readScores_File (int n, int j) throws IOException {

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

  //Array temporária com todos os valores do ficheiro
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

  /* Determina a dimensão, dim, da nova array
   * Se o ficheiro tiver array_temp.length valores e se for pedido uma array com j valores
   * Se n<j, dim=n; senão, se n>j, dim=j
   */

  int dim=0;
  if (array_temp.length<j) dim=array_temp.length;
  else dim=j;

  //Criar uma nova array com os dim valores (neste caso pontuações) pretendidas
  int array[]=new int[dim];
  for (int k=0; k<dim; k++) {
    array[k]=array_temp[k];
  }
  return array;
}

//Função que imprime 1 pontuação (do Singleplayer - 1, do Multiplayer - 2) num ficheiro
void saveScores_File (int num, int n) throws IOException {
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

  //Imprime o número no fim do ficheiro
  out.println(num);
  out.close();
}

//Função que ordena por ordem decrescente os valores de uma array.
/* Também teria sido possível utilizar a função sort() para ordenar e reverse() para inverter a ordem
 */
void orderArray (int[] array) {
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
  PFont f=createFont(font, 30, false);
  textFont(f);
  fill(ui);
  textSize(18);
  textAlign(LEFT);

  for (int i=0; i<array.length; i++) {
    text(array[i], col, lin+i*40);
  }
}