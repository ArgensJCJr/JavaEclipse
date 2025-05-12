package Canhao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Canhao.Jogador.Tiro;

public class Canhao extends JPanel implements ActionListener{

	public static int LARGURA = 800;
	public static int ALTURA = 1000;
	public boolean moveDir = false, moveEsq = false;
	public static int pixel = 8;

	Jogador jogador = new Jogador();
	Timer timer;
	
	public double[] vetor (double direcao, double veloc)  {
		double x = (Math.cos(Math.toRadians(direcao)) * veloc);
		double y = (Math.sin(Math.toRadians(direcao)) * veloc);		
		double[] vetor = { x, y};
		return vetor;
	}	
	
	Canhao(){
		this.setPreferredSize(new Dimension(LARGURA, ALTURA));
		this.setBackground(new Color(0x3f7f2f));
		this.setDoubleBuffered(true);	
		
		setFocusable(true);
		
		Alvos alvo1 = new Alvos(0);
		System.out.println(alvo1.x);
		System.out.println(alvo1.y);
		System.out.println(alvo1.direcao);
		System.out.println(alvo1.veloc);
		double xy[] = vetor(alvo1.direcao, alvo1.veloc);
		alvo1.dX = xy[0];
		alvo1.dY = xy[1];
		System.out.println(alvo1.dX);
		System.out.println(alvo1.dY);
		
		
		timer = new Timer(10, this);
		timer.start();
				
	}
	private void move() {
		//canhao
		int xAnterior = jogador.x;
		if(moveDir) {
			jogador.x += jogador.dX;
		}
		if(moveEsq) {
			jogador.x -= jogador.dX;
		}
		if(jogador.x < jogador.largura/2 || jogador.x > LARGURA - jogador.largura/2) {
			jogador.x = xAnterior;
		}
		
		//tiros
		for (int i = 0; i < jogador.tiros.size(); i++) {
			Tiro t  = jogador.tiros.get(i);
			t.y -= t.dY;
			if (t.y == 0) {
				jogador.tiros.remove(i);
			}
			
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		g.setColor(Color.orange);
		for (Tiro t : jogador.tiros) {
			g.fillOval(t.x-pixel, t.y, pixel, pixel*3);
		}
		
		g.drawImage(jogador.imagem, jogador.x - jogador.largura/2, jogador.y - jogador.altura, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		repaint();
	}
	
}
