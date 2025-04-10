package Angulo;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Angulo extends JPanel implements ActionListener {
	int LARGURA = 1400;
	int ALTURA = 1000;
	static int MARGEM = 30;
	
	static int CONTADORMAX = 30;
	static int INTERVALOMAX = 20;
	
	int tamanhoMax = 65;
	int tamanhoMin = 35;
	int vidaMax = 600;
	double direcaoPadrao = 90;
	int intensidadePadrao = 15;
	double vetorPolar[] = {direcaoPadrao, intensidadePadrao};
	double vetorCartesiana[] = {0,0};
	
	int explosaoMax = 150;
	int varVida = 4;
	static Color corExplosaoInicial = new Color(0xff0000);
	static Color corExplosaoFinal = new Color(0xffff5f);
	float varCor[] = {0,0,0};
	
	ArrayList<Bola> Bolas = new ArrayList();
	ArrayList<Explosao> Explosoes = new ArrayList<>();
	Random rand = new Random();
	Timer timer;
	
	int intervalo = INTERVALOMAX;
	boolean novoVetor = true;
	
	public void vetorVeloc(double direcao, double intensidade) {
		double radAng = Math.toRadians(direcao);
		vetorCartesiana[0] = intensidade * Math.sin(radAng);
		vetorCartesiana[1] = intensidade * Math.cos(radAng);	
		novoVetor = false;
	}
	
	Angulo(){
		this.setPreferredSize(new Dimension(LARGURA, ALTURA));
		this.setBackground(new Color(0x3f3f6f));
		geraBola();
		calculaVarCor();
		timer = new Timer(30, this);
		timer.start();
	}
	
	private void calculaVarCor() {
		float corIni[] = corExplosaoInicial.getColorComponents(null);
		float corFim[] = corExplosaoFinal.getColorComponents(null);
		int razao = explosaoMax / varVida;
		for(int i = 0; i<3;i++) {
			varCor [i] = (corFim[i] - corIni[i]) / razao;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		LARGURA = this.getWidth();
		ALTURA = this.getHeight();
		
		for (Bola bolinha:Bolas) {
			float cores[] = bolinha.cor.getComponents(null);
			g.setColor(new Color(cores[0], cores[1], cores[2], ((float)bolinha.vida/vidaMax)));
			g.fillOval((int)bolinha.x, (int)bolinha.y, bolinha.tamanho, bolinha.tamanho);
		}
		if (Explosoes.size()>0) {
			for(Explosao explo:Explosoes) {
				float cores[] = explo.cor.getColorComponents(null);
				g.setColor(new Color(cores[0], cores[1], cores[2], ((float)explo.vida/explosaoMax)));
				g.fillOval((int)explo.x, (int)explo.y, explo.tamanho, explo.tamanho);
			}
		}
		
	}
	
	public void geraBola() {
		Bola bolinha = new Bola();
		Bolas.add(bolinha);
		intervalo = INTERVALOMAX;
	}
	
	public class Bola {
		double x, y;
		double velocX, velocY;
		double direcao;
		double intensidade;
		int tamanho;
		int vida = vidaMax;
		Color cor;
		
		Bola(){
			this.x = 0;
			this.y = rand.nextInt(ALTURA - tamanhoMax - 2*MARGEM) + MARGEM;
			this.direcao = rand.nextInt((int)direcaoPadrao) + direcaoPadrao/2;
			this.intensidade = rand.nextInt(intensidadePadrao) + intensidadePadrao/2;
			vetorVeloc(this.direcao, this.intensidade);
			this.velocX = vetorCartesiana[0];
			this.velocY = vetorCartesiana[1];
			this.tamanho = rand.nextInt(tamanhoMax - tamanhoMin) + tamanhoMin;	
			int corBasica = 150;
			int corVariante = 255-corBasica;
			this.cor = new Color(rand.nextInt(corBasica)+corVariante,rand.nextInt(corBasica)+corVariante,rand.nextInt(corBasica)+corVariante);
		}
	}
	
	public class Explosao {
		int x, y;
		int tamanho = 1;
		int vida = explosaoMax;
		Color cor = corExplosaoInicial;
		
		Explosao(int x, int y){
			this.x = x;
			this.y = y;	
		}
	}
	
	public void move() {
		for (Bola bolinha:Bolas) {
			bolinha.x += bolinha.velocX;
			bolinha.y += bolinha.velocY;
			bolinha.vida -= 1;
		}
		checaColisao();
	}
	
	public void checaColisao() {
		for (Bola bolinha:Bolas) {
			if((bolinha.y + bolinha.tamanho) > ALTURA || bolinha.y < 0){
				bolinha.direcao = 180 - bolinha.direcao;
				novoVetor = true;
			}
			if(bolinha.x < 0 || (bolinha.x + bolinha.tamanho) > LARGURA ){
				bolinha.direcao = 360 - bolinha.direcao;
				novoVetor = true;
			}
			if (novoVetor) {
				vetorVeloc(bolinha.direcao, bolinha.intensidade);	
				bolinha.velocX = vetorCartesiana[0];
				bolinha.velocY = vetorCartesiana[1];
			}
		}
		for (int i=0; i< Bolas.size(); i++) {
			if(Bolas.get(i).vida < 10) {
				Bolas.remove(i);
				geraBola();
			}
		}
		if(Bolas.size()>1) {
			for (int i=1; i< Bolas.size(); i++) {
				int tamA = Bolas.get(i).tamanho/2;
				double xA = Bolas.get(i).x + tamA;
				double yA = Bolas.get(i).y + tamA;
				for (int j = 0; j < i; j++) {
					int tamB = Bolas.get(j).tamanho/2;
					double xB = Bolas.get(j).x + tamB;
					double yB = Bolas.get(j).y + tamB;
					double distancia = Math.sqrt(Math.pow((xA - xB), 2) + Math.pow((yA - yB), 2));
					if ((tamA + tamB - distancia) > 1) {
						Bolas.remove(i);
						Bolas.remove(j);
						geraExplosao(xA, yA);
						geraExplosao(xB, yB);
						break;
					}
				}
			}
		}
		
	}

	private void geraExplosao(double x, double y) {
		Explosao explosao = new Explosao((int)x, (int)y);
		Explosoes.add(explosao);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int contador = Bolas.size();
		if (contador < CONTADORMAX) {
			intervalo --;
			if (intervalo == 0) {
				geraBola();
			}
		}
		move();
		explodindo();
		repaint();		
	}

	private void explodindo() {
		if (Explosoes.size()>0) {
			for(int i = 0; i < Explosoes.size(); i++) {
				Explosao explo = Explosoes.get(i);
				explo.x -= 1;
				explo.y -= 1;
				explo.tamanho += 2;
				explo.vida -= varVida;
				
				float cores[] = explo.cor.getColorComponents(null);
				explo.cor = new Color(cores[0]+varCor[0], cores[1]+varCor[1], cores[2]+varCor[2]);
				
				if(explo.tamanho>tamanhoMax) {
					Explosoes.remove(i);
				}
			}
		}
		
	}
	
}
