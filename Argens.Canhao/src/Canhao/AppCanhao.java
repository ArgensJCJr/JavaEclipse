package Canhao;

import javax.swing.JFrame;

public class AppCanhao extends JFrame {

	public static void main(String[] args) {
		new AppCanhao();
		
	}
	
	AppCanhao() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(new Canhao());
		pack();
		setVisible(true);
		setLocationRelativeTo(null);

	}

}
