package br.usp.ia.model;

import java.util.ArrayList;

public class Attribute implements Cloneable {
	private String name;
	private ArrayList<String> possibleValues = new ArrayList<String>();
	private int hierarchy = -1;
	
	public Attribute(String name){
		this.setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getPossibleValues() {
		return possibleValues;
	}
	public void setPossibleValues(ArrayList<String> possibleValues) {
		this.possibleValues = possibleValues;
	}
	public void add(String possibleValue){
		possibleValues.add(possibleValue);
	}
	public int getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(int hierarchy) {
		this.hierarchy = hierarchy;
	}
	
}