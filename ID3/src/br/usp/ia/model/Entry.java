package br.usp.ia.model;

import java.util.ArrayList;

public class Entry {
	private ArrayList<ValuedAttribute> attributes;

	public ArrayList<ValuedAttribute> getAttributes() {
		return attributes;
	}
	
	public void setAttributes(ArrayList<ValuedAttribute> attributes) {
		this.attributes = attributes;
	}
	
	public void removeAttribute(int i){
		this.attributes.remove(i);
	}
}