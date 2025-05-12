package Canhao;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Jogador{		
	int dX ;
	int x, y;
	double conteira ;
	int altura ;
	int largura ;
	Image imagem ;
	int maxTiros = 5;
	
	ArrayList<Tiro> tiros;
	

	Jogador(){
		dX = Canhao.pixel;
		x = Canhao.LARGURA/2;
		y = Canhao.ALTURA;
		conteira = 180;
		altura = 32;
		largura = 64;
		imagem = new ImageIcon(getClass().getResource("/Canhao32.png")).getImage();
		tiros = new ArrayList<>();
	}
	
	public void geraTiro() {
		if(tiros.size() < maxTiros) {
			tiros.add(new Tiro(this.x, this.y));
		}
	}

	public class Tiro {
		int x, y, dX, dY;
		Tiro(int x, int y){
			this.x = x+Canhao.pixel/2;
			this.y = y - altura;
			this.dY = Canhao.pixel;
		}
		
	}

}
