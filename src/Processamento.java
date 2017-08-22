package eightqueens;

import java.util.ArrayList;
import java.util.Random;

public class Processamento {

	int nGeracoes;
	int nIndIniciais;
	Geracao geracaoAtual;
	Geracao proximaGeracao;
	Individuo respostaFinal;
	
	public Processamento(int nGeracoes, int nIndIniciais) {
		this.nGeracoes = nGeracoes;
		this.nIndIniciais = nIndIniciais;
		geracaoAtual = new Geracao();
		proximaGeracao = new Geracao();
		geracaoAtual.inicializaIndividuos(nIndIniciais);
		calculaResultado();
	}
	
	public void calculaResultado() {
		boolean resposta = false;
		int geracoes = 1;int soma = 0;
		while (!resposta && geracoes <= nGeracoes) {
			soma = 0;
			System.out.println("Comencando geracao "+geracoes);
			//CALCULA OS CONFLITOS DE CADA INDIVIDUO
			for (int i = 0; i < geracaoAtual.listaIndividuos.size(); i++) {
				verificaConflito(geracaoAtual.listaIndividuos.get(i));
				soma+=geracaoAtual.listaIndividuos.get(i).getnConflitos();
				if (geracaoAtual.listaIndividuos.get(i).getnConflitos() == 0) {
					// ENCONTROU RESULTADO
					resposta = true;
					respostaFinal = geracaoAtual.listaIndividuos.get(i);
					break;
				}
			}
			// ORDENA OS INDIVIDUOS PELO NÚMERO DE CONFLITOS
			calculaMelhoresInd(geracaoAtual.listaIndividuos);
			// ADICIONA METADE DOS INDIVÍDUOS NA PRÓXIMA GERACAO
			int media = (int) geracaoAtual.listaIndividuos.size()/2;
			if (media % 2 != 0) { media++; }
			for (int i = 0; i < (int) media; i++) {
				proximaGeracao.listaIndividuos.add(geracaoAtual.listaIndividuos.get(i));
			}
			cut_and_crossfill();
			transfereGeracoes();
			System.out.println("Concluiu geração. Soma: "+soma);
			geracoes++;
		}
		if (resposta) {
			String res = "Solução encontrada ("+(geracoes-1)+"ª geração)\n\n";
			for (int i = 0; i < respostaFinal.valores.length; i++) {
				res+= "("+i+","+respostaFinal.valores[i]+")";
			}
			Tela.lblResultado.setText(res);
		} else {
			Tela.lblResultado.setText("Finalizou gerações. \n\n Não encontrou resposta");
		}
	}
	
	
	public void cut_and_crossfill() {
		ArrayList<Individuo> filhos = new ArrayList<Individuo>();
		Random random = new Random();
		Integer pontoDeCorte = 0;
		for (int i = 0; i < proximaGeracao.listaIndividuos.size(); i+=2) {
			ArrayList<Integer> novoParte1Pai = new ArrayList<Integer>();
			ArrayList<Integer> novoParte2Pai = new ArrayList<Integer>();
			ArrayList<Integer> novoParte1Mae = new ArrayList<Integer>();
			ArrayList<Integer> novoParte2Mae = new ArrayList<Integer>();
			pontoDeCorte = random.nextInt(7)+1;
			for (int j = 0; j < 8; j++) {
				if (j < pontoDeCorte) {
					novoParte1Pai.add(proximaGeracao.listaIndividuos.get(i).valores[j]);			
				} else {
					novoParte2Pai.add(proximaGeracao.listaIndividuos.get(i).valores[j]);
				}
			}
			for (int j = 0; j < 8; j++) {
				if (j < pontoDeCorte) {
					novoParte1Mae.add(proximaGeracao.listaIndividuos.get(i+1).valores[j]);			
				} else {
					novoParte2Mae.add(proximaGeracao.listaIndividuos.get(i+1).valores[j]);
				}
			}
			
			ArrayList<Integer> filho = new ArrayList<Integer>();
			for (int j = 0; j < novoParte1Mae.size(); j++) {
				filho.add(novoParte1Mae.get(j));
			}
			for (int j = 0; j < novoParte2Mae.size(); j++) {
				filho.add(novoParte2Mae.get(j));
			}
			filhos.add(new Individuo(filho));
			filho.clear();
			for (int j = 0; j < novoParte1Pai.size(); j++) {
				filho.add(novoParte1Pai.get(j));
			}
			for (int j = 0; j < novoParte2Pai.size(); j++) {
				filho.add(novoParte2Pai.get(j));
			}
			filhos.add(new Individuo(filho));
		}
		for (int i = 0; i < filhos.size(); i++) {
			proximaGeracao.listaIndividuos.add(filhos.get(i));
		}
		
	}
	
	public void calculaMelhoresInd(ArrayList<Individuo> individuos) {
		Individuo aux;
		for (int i = 0; i < individuos.size(); i++) { 
			for (int j = 0; j < individuos.size(); j++) {
				if (individuos.get(i).getnConflitos() < individuos.get(j).getnConflitos()) { 
					aux = individuos.get(i); 
					individuos.set(i, individuos.get(j));
					individuos.set(j, aux);
				} 
			}
		}
	}
		
	
	public void verificaConflito(Individuo individuo) {
		int nConflitos = 0;
		for (int i = 0; i < 8; i++) {
			nConflitos += verificaDiagonal(i, individuo.valores[i], individuo.valores);
		}
		individuo.setnConflitos(nConflitos);
	}
	
	public int verificaDiagonal(int x, int y, int[] individuo) {
		int i = x,j = y;
		int conflitos = 0;
			//OBJETOS ACIMA A ESQUERDA
			i--;j--;
			while(validaIndice(i, j)) {
				if (individuo[i] == j) {
					conflitos++;
				}
				i--;j--;
			}
			//OBJETOS ABAIXO A DIREITA
			i = x;j = y;
			i++;j++;
			while(validaIndice(i, j)) {
				if (individuo[i] == j) {
					conflitos++;
				}
				i++;j++;
			} 
			//OBJETOS ACIMA A DIREITA
			i--;j++;
			while(validaIndice(i, j)) {
				if (individuo[i] == j) {
					conflitos++;
				}
				i--;j++;
			}
			//OBJETOS ABAIXO A ESQUERDA
			i = x;j = y;
			i++;j--;
			while(validaIndice(i, j)) {
				if (individuo[i] == j) {
					conflitos++;
				}
				i++;j--;
			}
		return conflitos;
	}
	
	public void transfereGeracoes() {
		geracaoAtual.listaIndividuos.clear();
		for (int i = 0; i < proximaGeracao.listaIndividuos.size(); i++) {
			geracaoAtual.listaIndividuos.add(proximaGeracao.listaIndividuos.get(i));
		}
		proximaGeracao.listaIndividuos.clear();
	}
	
	public boolean validaIndice(int i, int j) {
		return ((i>=0)&&(i<=7)&&(j>=0)&&(j<=7));
	}
}
