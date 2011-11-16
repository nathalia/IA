package br.usp.ia.model;

import java.util.ArrayList;

public class Attribute {
	private String name;
	private ArrayList<String> possibleValues = new ArrayList<String>();
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

	
}