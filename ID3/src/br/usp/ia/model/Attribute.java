package br.usp.ia.model;

import java.util.ArrayList;
import java.util.List;

public class Attribute {
	private String nome;
	private List<Valor> valores;
	
	public Attribute(String nome) {
		super();
		this.nome = nome;
		this.valores = new ArrayList<Valor>();
	}

	public String getNome() {
		return nome;
	}

	public List<Valor> getValores() {
		return valores;
	}
}
