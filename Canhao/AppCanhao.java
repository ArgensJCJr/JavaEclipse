package Canhao;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class AppCanhao extends JFrame implements KeyListener{

	public static void main(String[] args) {	
		new AppCanhao();
	}
	
	Canhao canhao;
	
	AppCanhao(){	
		setTitle("Tiro de Canhao");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
//		setLayout(null);
		canhao = new Canhao();
		add(canhao);
		pack();
		setLocationRelativeTo(null);
		addKeyListener(this);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D:
			canhao.moveDir = true;
			break;
		case KeyEvent.VK_A:
			canhao.moveEsq = true;
			break;
		case KeyEvent.VK_SPACE:
			canhao.jogador.geraTiro();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D:
			canhao.moveDir = false;
			break;
		case KeyEvent.VK_A:
			canhao.moveEsq = false;
			break;		
	}
	}
	}


