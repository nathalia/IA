package br.usp.ia.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private String nome;
	private List<Node> nodes;
	private double entropy;
	public Node() {
		this.nodes = new ArrayList<Node>();
		setEntropy(0);
	}
	public String getNome() {
		return nome;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public double getEntropy() {
		return entropy;
	}
	public void setEntropy(double entropy) {
		this.entropy = entropy;
	}
}
