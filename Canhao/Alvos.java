package Canhao;

import java.util.Random;

public class Alvos {
	
	double x, y, dX, dY, direcao, veloc;
	int altura, largura, diametro;
	int tipo;
	Random rand;
	static int[] DIAMETROS = {256};
	static int[] ALTURAS = {256};
	static int[] LARGURAS = {256};
	
	Alvos(int tipo){
		x = new Random().nextInt(Canhao.LARGURA - LARGURAS[tipo]) + LARGURAS[tipo];
		y = 10;
		altura = ALTURAS[tipo];
		largura = ALTURAS[tipo];
		diametro = DIAMETROS[tipo];
		direcao = new Random().nextInt(90) + 45;
		veloc = Canhao.pixel;
	}

}
