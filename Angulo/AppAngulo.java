package Angulo;

import javax.swing.JFrame;

public class AppAngulo {
	
	static int www;
	
	public static void main(String[] args) {
		
		JFrame Fundo = new JFrame("Meteroros");
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
