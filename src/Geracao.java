package eightqueens;

import java.util.ArrayList;

public class Geracao {

	ArrayList<Individuo> listaIndividuos = new ArrayList<Individuo>();

	public Geracao(ArrayList<Individuo> listaInidividuos) {
		this.listaIndividuos = listaInidividuos;
	}
	
	public Geracao() {}
	
	public void inicializaIndividuos(int qt) {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for (int i = 0; i < 8; i++) {
			numeros.add(i);
		}
		for (int i = 0; i < qt; i++) {
			listaIndividuos.add(new Individuo(numeros));
		}
	}
}
