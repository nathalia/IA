package br.usp.ia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.management.AttributeValueExp;

import br.usp.ia.model.Attribute;
import br.usp.ia.model.ValuedAttribute;
import br.usp.ia.model.Entry;
import br.usp.ia.model.Value;

public class ID3Utils {

	public static double entropy(float positive, float negative){
		if (positive == 0.0 || negative == 0.0){
			return 0.0;
		}
		float proportion1 = positive/(positive+negative);
		float proportion2 = negative/(positive+negative);
		return -proportion1*(Math.log(proportion1)/Math.log(2))-proportion2*(Math.log(proportion2)/Math.log(2));
	}

	public static double gain(double rootEntropy, List<Value> entropies, int total){
		double gain = rootEntropy;
		double entropy;
		float prop; 
		float tot = total;
		for (Value value : entropies) {
			entropy = entropy(value.getPositive(), value.getNegative());
			prop = (value.getPositive()+value.getNegative())/tot;
			gain += -(prop*entropy);
		}

		return gain;
	}

	//conta raiz
	public static Value countLabels(ArrayList<Entry> set){
		int positive = 0;
		int negative = 0;
		for (Entry entry : set) {
			ArrayList<ValuedAttribute> attributes = entry.getAttributes();
			int size = attributes.size()-1;

			if(attributes.get(size).getValue().equals("no"))
				negative++;
			else if(attributes.get(size).getValue().equals("yes"))
				positive++;	
		}
		return new Value("labels", positive, negative);		
	}

	public static ArrayList<Entry> removeEntries(int index, ArrayList<Entry> set){
		ArrayList<Entry> newSet = new ArrayList<Entry>();

		for (Entry entry : set) {
			Entry e = new Entry();
			e.setAttributes((ArrayList<ValuedAttribute>) entry.getAttributes().clone());
			newSet.add(e);
		}

		for (Entry entry : newSet) {
			ArrayList<ValuedAttribute> attributes = entry.getAttributes();
			attributes.remove(index);
		}
		return newSet;
	}

	public static double countAttribute(double initial, ArrayList<Entry> learningSet,
			Attribute attributePossibleValues, int attrIndex){
		ArrayList<Value> entropies = new ArrayList<Value>();
		int size = learningSet.size();

		int[] positive;
		int[] negative;		
		List<String> possibleValues = attributePossibleValues.getPossibleValues();
		int qtd = possibleValues.size();
		positive = new int[qtd];
		negative = new int[qtd];
		int i = 0;
		for (String possibleValue : possibleValues) {
			for (Entry entry : learningSet) {
				ArrayList<ValuedAttribute> attributes = entry.getAttributes();
				if(attributes.get(attrIndex).getValue().equals(possibleValue) && 
						attributes.get(attributes.size()-1).getValue().equals("yes"))
					positive[i]++;
				else if(attributes.get(attrIndex).getValue().equals(possibleValue) && 
						attributes.get(attributes.size()-1).getValue().equals("no"))
					negative[i]++;
			}

			entropies.add(new Value(possibleValue, negative[i], positive[i]));
			i++;
		}
		return ID3Utils.gain(initial, entropies, size);
	}

	public static ArrayList<Entry> partialSet(ArrayList<Entry> learningSet, int attrIndex, 
			String possibleValue){
		ArrayList<Entry> newSet = new ArrayList<Entry>();
		for (Entry entry : learningSet) {
			ArrayList<ValuedAttribute> attributes = (ArrayList<ValuedAttribute>) entry.getAttributes().clone();
			ValuedAttribute valuedAttribute = attributes.get(attrIndex);
			if(valuedAttribute.getValue().equals(possibleValue)){
				attributes.remove(attrIndex);
				Entry e = new Entry();
				e.setAttributes(attributes);
				newSet.add(e);
			}
		}
		return newSet;
	}
	
	public static ArrayList<Attribute> partialAttributes(ArrayList<Attribute> attributesValues, 
			int attrIndex){
		ArrayList<Attribute> newAttributesValues = new ArrayList<Attribute>();
		ArrayList<Attribute> attributesValuesAux = (ArrayList<Attribute>) attributesValues.clone();
				attributesValuesAux.remove(attrIndex);
		for (Attribute attribute : attributesValuesAux) {
			Attribute newAttribute = new Attribute(attribute.getName());
			newAttribute.setPossibleValues((ArrayList<String>) 
					attribute.getPossibleValues().clone());
			newAttributesValues.add(newAttribute);
		}
		return newAttributesValues;
	}
	
}