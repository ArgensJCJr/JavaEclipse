package Canhao;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Canhao extends JPanel{
	static final int LARGURA = 500;
	static final int ALTURA = 700;

	Canhao(){
		this.setPreferredSize(new Dimension(LARGURA, ALTURA));
		this.setBackground(new Color(0x7f2f00));
	}
}
