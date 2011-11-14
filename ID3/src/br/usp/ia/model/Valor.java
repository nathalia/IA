package br.usp.ia.model;

public class Valor {
	private String nome;
	private int posisivo;
	private int negativo;
	
	public Valor(String nome, int posisivo, int negativo) {
		super();
		this.nome = nome;
		this.posisivo = posisivo;
		this.negativo = negativo;
	}

	public String getNome() {
		return nome;
	}

	public int getPosisivo() {
		return posisivo;
	}

	public int getNegativo() {
		return negativo;
	}
}
