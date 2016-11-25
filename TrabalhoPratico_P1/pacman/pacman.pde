/* Maria João Lavoura
 * Pedro Teixeira
 *
 * Programação I | Trabalho Prático
 * Turma P8
 */

//-----------------------------------------------------------------------------------------------------------------------------------------
// Parâmetros do labirinto
int nCol, nLin;          // nº de linas e de colunas
int tamanho = 50;        // tamanho (largura e altura) das células do labirinto  
int espacamento = 2;     // espaço livre emtre células
float margemV, margemH;  // margem livre na vertical e na horizontal para assegurar que as células são quadrangulares
color corObstaculos =  color(100, 0, 128);      // cor de fundo dos obstáculos

//Posição e tamanho do Pacman
float px_pac, py_pac, pRaio;

//Velocidade na horizontal e vertical do Pacman
float vx_pac, vy_pac; 

//Posições dos fantasmas
float px_ghost1, py_ghost1, px_ghost2, py_ghost2, px_ghost3, py_ghost3, px_ghost4, py_ghost4;

//Velocidade na horizontal e na vertical dos fantasmas
float vx_ghost, vy_ghost; 

//-----------------------------------------------------------------------------------------------------------------------------------------
void setup() {
	// Definir o tamanho da janela; notar que size() não aceita variáveis.
	size(720, 520);
	background(0);

	nCol = (int)width/tamanho;
	nLin = (int)height/tamanho;

	// Assegurar que nº de linhas e nº de colunas é maior ou igual a 3
	assert nCol >= 5 && nLin >= 5;

	// Determinar margens para limitar a área útil do jogo 
	margemV = (width - nCol * tamanho) / 2.0;
	margemH = (height - nLin * tamanho) / 2.0;

	// Inicializar o Pacman
	px_pac = centroX(1);
	py_pac = centroY(1);
	pRaio = (tamanho - espacamento) / 2;

	// Inicializar os Fantasmas, ie a sua posição inicial
	px_ghost1 = centroX(6);
	py_ghost1 = centroY(10);
	px_ghost2 = centroX(6);
	py_ghost2 = centroY(10);
	px_ghost3 = centroX(10);
	py_ghost3 = centroY(1);
	px_ghost4 = centroX(10);
	py_ghost4 = centroY(1);

	//specifies speeds in X and Y directions
	vx_pac = 0;
	vy_pac = 0;

	vx_ghost=0;
	vy_ghost=0;
}

//-----------------------------------------------------------------------------------------------------------------------------------------
void draw() {
	background(0);

	desenharLabirinto();
	desenharPontos();
	desenharPacman(1);
	desenharFantasmas(); 

	if (px_pac > centroX(nCol))
		vx_pac = -vx_pac;
	if (px_pac < centroX(1))
		vx_pac = -vx_pac;
	px_pac += vx_pac;
	py_pac += vy_pac;

	//Move aleatoriamente os fantasmas
	if (px_ghost1 > centroX(nCol))
		vx_ghost = -vx_ghost;
	if (px_pac < centroX(1))
		vx_ghost = -vx_ghost;
	px_ghost1 += vx_ghost;
	py_ghost1 += vy_ghost;
}

//-----------------------------------------------------------------------------------------------------------------------------------------
//Função que desenha o Pacman
void desenharPacman(int key) {
	fill(232, 239, 40);
	//ellipseMode(CENTER);
	//noStroke();

	//Consoante a tecla pressionada, desenha o Pacman correspondente
	switch (key) {
	//1: LEFT; 2: RIGHT; 3: UP; 4: DOWN
	case 1: 
		arc(px_pac, py_pac, pRaio, pRaio, -PI*3/4.0, PI*3/4.0, PIE); 
		break;
	case 2: 
		arc(px_pac, py_pac, pRaio, pRaio, PI/4.0, PI*7/4.0, PIE); 
		break; 
	case 3: 
		arc(px_pac, py_pac, pRaio, pRaio, -PI/4.0, PI*5/4.0, PIE); 
		break;
	case 4: 
		arc(px_pac, py_pac, pRaio, pRaio, -PI*5/4.0, PI/4.0, PIE); 
		break;
	}
	//fill(0);
	//arc(px_pac, py_pac, px_pac+pRaio/1.828427125, py_pac-pRaio/1.828427125, px_pac+pRaio/1.828427125, py_pac+pRaio/1.828427125);
}

//-----------------------------------------------------------------------------------------------------------------------------------------
//Função que desenha os fantasmas
void desenharFantasmas() {
	fill(255, 66, 3);
	ellipse(px_ghost1, py_ghost1, pRaio, pRaio);
	ellipse(px_ghost2, py_ghost2, pRaio, pRaio);
	ellipse(px_ghost3, py_ghost3, pRaio, pRaio);
	ellipse(px_ghost4, py_ghost4, pRaio, pRaio);
}

//-----------------------------------------------------------------------------------------------------------------------------------------
//Função que move aleatoriamente os fantasmas
void moverFantasmas () {
}

//-----------------------------------------------------------------------------------------------------------------------------------------
//Função que move o Pacman através de input do utilizador (arrow keys) 
void keyPressed() {

	//Left Arrow Key
	if ( keyCode == LEFT ) {
		float cx = px_pac-50;              //Para a célula ao lado esquerdo da actual
		float cy = py_pac;                 //...da mesma linha
		color c = get((int)cx, (int)cy);   //Obtém a cor dessa célula
		if (c != corObstaculos) {          //Se essa célula não for obstáculo
			px_pac = px_pac - 50;            //Move o Pacman
			desenharPacman(1);
		}
		//  vx=abs(vx);
		//  vy=0;
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
			desenharPacman(2);
		}
		// vx=-abs(vx);
		// vy=0;
		if (px_pac > centroX(nCol)) { //impede pacman sair da janela
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
			desenharPacman(3);
		}
		//vy=-abs(vx);
		//vx=0;
		if (py_pac < centroY(1)) { //impede pacman sair da janela
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
			desenharPacman(4);
		}
		//vy=abs(vx);
		// vx=0;
		if (py_pac > centroY(nLin)) { //impede pacman sair da janela
			py_pac = py_pac - 50;
		}
	}

	//Pausa o jogo
	if ((key == 'P') || (key == 'p')) {
		if (looping) noLoop(); 
		else loop();
	}
}
//-----------------------------------------------------------------------------------------------------------------------------------------
//Função que desenha o labirinto
void desenharLabirinto () {

	// desenha a fronteira da área de jogo
	fill(0);
	stroke(80, 60, 200);
	strokeWeight(espacamento);
	rect(margemH, margemV, width - 2*margemH, height - 2*margemV);

	// Desenha obstáculos
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

//-----------------------------------------------------------------------------------------------------------------------------------------
//Função que desenha obstáculos
void desenharObstaculo(int x, int y, int numC, int numL) {
	float x0, y0, larg, comp;

	x0 = margemH + (x-1) * tamanho;
	y0 = margemV + (y-1) * tamanho;
	larg = numC * tamanho;
	comp = numL * tamanho;

	fill(corObstaculos);
	stroke(0);
	strokeWeight(espacamento/2);
	rect(x0, y0, larg, comp);
}

/*
Desenhar pontos nas células vazias (que não fazem parte de um obstáculo). 
 Esta função usa a cor de fundo no ecrã para determinar se uma célula está vazia ou se faz parte de um obstáculo.
 */

//-----------------------------------------------------------------------------------------------------------------------------------------
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
// transformar o índice de uma célula em coordenada no ecrã
float centroX(int col) {
	return margemH + (col - 0.5) * tamanho;
}

// transformar o índice de uma célula em coordenada no ecrã
float centroY(int lin) {
	return margemV + (lin - 0.5) * tamanho;
}