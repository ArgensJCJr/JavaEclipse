package ChoqueBolas;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Choque extends JFrame{
	
	JFrame fundo = new JFrame();
	JPanel painel = new JPanel();
	
	Choque(){
		fundo.setSize(800, 600);
		fundo.setVisible(true);
		fundo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fundo.setLocationRelativeTo(null);
		painel.setBackground(new Color(0x205580));
		fundo.add(painel);
	}

}
