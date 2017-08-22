package eightqueens;

import java.util.ArrayList;
import java.util.Collections;

public class Individuo {

	int[] valores = new int[8];
	private int nConflitos;

	public Individuo(ArrayList<Integer> numeros) {
		//System.out.println("Criando individuo");
		int[] lista = new int[8];
		Collections.shuffle(numeros);
		for (int i = 0; i < 8; i++) {
			lista[i] = numeros.get(i);
			//System.out.println("pos: "+i+" - value: "+numeros.get(i));
		}
		this.valores = lista;
	}

	public int getnConflitos() {
		return nConflitos;
	}

	public void setnConflitos(int nConflitos) {
		this.nConflitos = nConflitos;
	}
}
