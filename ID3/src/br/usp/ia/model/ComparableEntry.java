package br.usp.ia.model;

import java.util.ArrayList;

public class ComparableEntry implements Comparable {
	private double value;
	private String label;


	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}

	public int compareTo(Object o) {
		ComparableEntry comp = (ComparableEntry) o;

		if(comp.getValue()>this.value)
			return 1;
		else if(comp.getValue()==this.value)
			return 0;
		else
			return -1;
	}
}