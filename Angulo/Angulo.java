package Angulo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Angulo extends JPanel implements ActionListener {
	static int LARGURA = 800;
	static int ALTURA = 600;
	int tamanhoMax = 55;
	double direcao = 90;
	
	Bola bolinha1;
	Random rand = new Random();
	Timer timer;
	
	Angulo(){
		this.setPreferredSize(new Dimension(LARGURA, ALTURA));
		this.setBackground(new Color(0x351010));
		geraBola();
		timer = new Timer(1000, this);
		timer.start();
		}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(bolinha1.x, bolinha1.y, bolinha1.tamanho, bolinha1.tamanho);
		
		g.setColor(Color.white);
		int x = (1 + bolinha1.x/2) * bolinha1.tamanho;
		g.drawLine(bolinha1.x + bolinha1.tamanho/2, bolinha1.y + bolinha1.tamanho/2, 0, 0);
	}
	
	public void geraBola() {
		bolinha1 = new Bola();
	}
	

	public class Bola {
		int x, y;
		double velocX, velocY;
		double direcao;
		int tamanho;
		
		Bola(){
			this.x = 0;
			this.y = rand.nextInt(ALTURA-tamanhoMax);
			this.velocX = rand.nextInt(2)+1;
			this.velocY = rand.nextInt(7)-3;
			this.tamanho = tamanhoMax;
	
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		geraBola();
		repaint();		
	}
	
}
