package br.usp.ia.model;


public class ValuedAttribute {
	private String name;
	private String value;
	
	public ValuedAttribute(String name, String strValue) {
		this.setName(name);
		value = strValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
