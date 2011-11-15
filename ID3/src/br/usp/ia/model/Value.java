package br.usp.ia.model;

public class Value {
	private String name;
	private int positive;
	private int negative;
	
	public Value(String nome, int positive, int negative) {
		super();
		this.name = nome;
		this.positive = positive;
		this.negative = negative;
	}

	public String getName() {
		return name;
	}

	public int getPositive() {
		return positive;
	}

	public int getNegative() {
		return negative;
	}
}
