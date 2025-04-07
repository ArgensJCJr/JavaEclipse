package Angulo;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class Angulo extends JPanel implements ActionListener {
	static int LARGURA = 1200;
	static int ALTURA = 800;
	static int MARGEM = 150;
	
	int tamanhoMax = 35;
	double direcaoPadrao = 90;
	int intensidadePadrao = 7;
	double vetorPolar[] = {direcaoPadrao, intensidadePadrao};
	double vetorCartesiana[] = {0,0};
	
	Bola bolinha1;
	Random rand = new Random();
	Timer timer;
	
	int tempContador = 1;
	boolean novoVetor = true;
	
	public void vetorVeloc(double direcao, double intensidade) {
		double radAng = Math.toRadians(direcao);
		vetorCartesiana[0] = intensidade * Math.sin(radAng);
		vetorCartesiana[1] = intensidade * Math.cos(radAng);	
		novoVetor = false;
	}
	
	Angulo(){
		this.setPreferredSize(new Dimension(LARGURA, ALTURA));
		this.setBackground(new Color(0x351010));
		geraBola();
		timer = new Timer(10, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		
		float cores[] = bolinha1.cor.getComponents(null);
		g.setColor(new Color(cores[0], cores[1], cores[2], ((float)bolinha1.vida/255)));
//		g.setColor(new Color(255, 100, 0, 255));
		g.fillOval((int)bolinha1.x, (int)bolinha1.y, bolinha1.tamanho, bolinha1.tamanho);
	}
	
	public void geraBola() {
		bolinha1 = new Bola();
	}
	
	public class Bola {
		double x, y;
		double velocX, velocY;
		double direcao;
		double intensidade;
		int tamanho;
		int vida = 255;
		Color cor;
		
		Bola(){
			this.x = 0;
			this.y = rand.nextInt(ALTURA - tamanhoMax - 2*MARGEM) + MARGEM;
			this.direcao = rand.nextInt(90) + 45;
			this.intensidade = rand.nextInt(intensidadePadrao) + intensidadePadrao/2;
			vetorVeloc(this.direcao, this.intensidade);
			this.velocX = vetorCartesiana[0];
			this.velocY = vetorCartesiana[1];
			this.tamanho = tamanhoMax;	
			this.cor = new Color(125, 30, 200);
		}
	}
	
	public void move() {
		bolinha1.x += bolinha1.velocX;
		bolinha1.y += bolinha1.velocY;
		bolinha1.vida -= 1;
		checaColisao();
	}
	
	public void checaColisao() {
		if((bolinha1.y + bolinha1.tamanho) > ALTURA || bolinha1.y < 0){
			bolinha1.direcao = 180 - bolinha1.direcao;
			novoVetor = true;
		}
		if(bolinha1.x < 0 || (bolinha1.x + bolinha1.tamanho) > LARGURA ){
			bolinha1.direcao = 360 - bolinha1.direcao;
			novoVetor = true;
		}
		if (novoVetor) {
			vetorVeloc(bolinha1.direcao, bolinha1.intensidade);	
			bolinha1.velocX = vetorCartesiana[0];
			bolinha1.velocY = vetorCartesiana[1];
		}
		if(bolinha1.vida < 10) {
			tempContador = 0;
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(tempContador == 0) {
			geraBola();
			tempContador ++;
		}
		move();
		repaint();		
	}
	
}
