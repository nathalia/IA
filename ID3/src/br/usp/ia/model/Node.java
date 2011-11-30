package br.usp.ia.model;

import java.util.ArrayList;
import java.util.List;

public class Node implements Cloneable {
	private String name;
	private List<String> arestas = new ArrayList<String>();
	private List<Node> nodes;
	
	public List<String> getArestas() {
		return arestas;
	}
	public void setArestas(List<String> arestas) {
		this.arestas = arestas;
	}
	public Node() {
		this.nodes = new ArrayList<Node>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
}
