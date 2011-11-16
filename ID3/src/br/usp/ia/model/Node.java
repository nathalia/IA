package br.usp.ia.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private String name;
	private List<Node> nodes;
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
