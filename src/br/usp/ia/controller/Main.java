package br.usp.ia.controller;

import java.util.ArrayList;

import br.usp.ia.model.Attribute;
import br.usp.ia.model.Entry;
import br.usp.ia.model.Node;
import br.usp.ia.model.Value;
import br.usp.ia.model.ValuedAttribute;

public class Main {

	public static void main(String[] args) {

		ArrayList<Entry> learningSet = FileReader.readFile();
		ArrayList<Attribute> attributesValues = FileReader.getAttributesValues();
		Value rootValues = ID3Utils.countLabels(learningSet);
		double initial = ID3Utils.entropy(rootValues.getNegative(), rootValues.getPositive());

		double[] gains = new double[learningSet.get(0).getAttributes().size()-1];

		buildTree(learningSet, attributesValues, "","raiz");


	}

	public static void buildTree(ArrayList<Entry> learningSet, ArrayList<Attribute> attributesValues,
			String pai, String aresta){
		if(learningSet.size() == 0 || attributesValues.size()==0) return;
		Value rootValues = ID3Utils.countLabels(learningSet);
		double initial = ID3Utils.entropy(rootValues.getNegative(), rootValues.getPositive());

		double[] gains = new double[learningSet.get(0).getAttributes().size()-1];
		if(gains.length==0) return;
		int i = 0;
		int j = -1;
		double max = 0.0;
		for(int k = 0; k<gains.length; k++){
			gains[k] = ID3Utils.countAttribute(initial, learningSet, attributesValues.get(i), i);
			if(gains[k]>=max){
				max = gains[k];
				j++;
			}
			i++;	
		}

		int difzero=0;
		for(int k = 0; k<gains.length; k++){
			if(gains[k]!=0.0)
				difzero++;
		}

		if(difzero==0){
			gains = new double[learningSet.get(0).getAttributes().size()];
			if(gains.length==0) return;
			i = 0;
			j = -1;
			max = 0.0;
			for(int k = 0; k<gains.length; k++){
				gains[k] = ID3Utils.countAttribute(initial, learningSet, attributesValues.get(i), i);
				if(gains[k]>=max){
					max = gains[k];
					j++;
				}
				i++;	
			}		
		}
		if(aresta.equals("no") || aresta.equals("yes")){
			System.out.println(pai + "->>"+ aresta );
			return;
		}
		else
			System.out.println(pai + "-[ "+ aresta + " ]-" +attributesValues.get(j).getName());
		
		int k = 0;

		for (String possibleValue : attributesValues.get(j).getPossibleValues()) {
			ArrayList<Entry> newSet = ID3Utils.partialSet(learningSet, j, possibleValue);
			ArrayList<Attribute> newAttributesValues = ID3Utils.partialAttributes(attributesValues, j);
			buildTree(newSet, newAttributesValues, attributesValues.get(j).getName(), 
					possibleValue);
		}
	}
}


