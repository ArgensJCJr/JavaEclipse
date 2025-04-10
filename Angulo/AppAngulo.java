package Angulo;

import javax.swing.JFrame;

public class AppAngulo {
		
	public static void main(String[] args) {
		
		JFrame Fundo = new JFrame("Meteoros");
		Angulo painel = new Angulo();
		Fundo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Fundo.setFocusable(true);
		Fundo.setVisible(true);
		Fundo.add(painel);
		Fundo.pack();
		Fundo.requestFocus();
		Fundo.setLocationRelativeTo(null);
	}

}
