package br.usp.ia.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private String nome;
	private List<Node> nodes;
	public Node(String nome, List<Node> nodes) {
		super();
		this.nome = nome;
		this.nodes = new ArrayList<Node>();
	}
	public String getNome() {
		return nome;
	}
	public List<Node> getNodes() {
		return nodes;
	}
}
