package br.usp.ia.controller;

import java.util.ArrayList;

import br.usp.ia.model.Attribute;
import br.usp.ia.model.Entry;
import br.usp.ia.model.Node;
import br.usp.ia.model.Value;
import br.usp.ia.model.ValuedAttribute;

public class PlayTennis {

	public static void main(String[] args) {

		ArrayList<Entry> learningSet = FileReader.readFile("playtennis.data");
		ArrayList<Attribute> attributesValues = FileReader.getAttributesValues();
		
		Node root = new Node();
		buildTree(learningSet, attributesValues, root, "", "");
		Entry e = new Entry();
		ArrayList<ValuedAttribute> attribs = new ArrayList<ValuedAttribute>();
		ValuedAttribute at1 = new ValuedAttribute("outlook", "sunny");
		ValuedAttribute at2 = new ValuedAttribute("humidity", "high");
		ValuedAttribute at3 = new ValuedAttribute("temperature", "hot");
		ValuedAttribute at4 = new ValuedAttribute("wind", "strong");
		ValuedAttribute at5 = new ValuedAttribute("decision", "no");
		attribs.add(at1);
		attribs.add(at2);
		attribs.add(at3);
		attribs.add(at4);
		attribs.add(at5);


		e.setAttributes(attribs);
		System.out.println(ID3Inference.analysis(root.getNodes().get(0), e));
	}

	public static void buildTree(ArrayList<Entry> learningSet, ArrayList<Attribute> attributesValues,
			Node pai, String nomePai, String aresta){
		if(learningSet.size() == 0 || attributesValues.size()==0) return;
		Value rootValues = ID3Utils.countLabels(learningSet);
		double initial = ID3Utils.entropy(rootValues.getNegative(), rootValues.getPositive());

		double[] gains = new double[learningSet.get(0).getAttributes().size()-1];

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
			j = learningSet.get(0).getAttributes().size()-1;
		}

		if(j<0)return;
		System.out.println(pai.getName() + "-[ "+ aresta + " ]-" +attributesValues.get(j).getName());
		Node node = new Node();
		if(difzero!=0){
		pai.getArestas().add(aresta);
		pai.setName(nomePai);
		node.setName(attributesValues.get(j).getName());
		pai.getNodes().add(node);
		}
		for (String possibleValue : attributesValues.get(j).getPossibleValues()) {
			if(difzero!=0){
				ArrayList<Entry> newSet = ID3Utils.partialSet(learningSet, j, possibleValue);
				ArrayList<Attribute> newAttributesValues = ID3Utils.partialAttributes(attributesValues, j);
				buildTree(newSet, newAttributesValues, node, node.getName(),
						possibleValue);
			}else{
				Value v = ID3Utils.countLabels(learningSet);
				if(v.getNegative()<v.getPositive()){
					pai.getArestas().add(aresta);
					Node decision = new Node();
					decision.setName("yes");
					pai.getNodes().add(decision);
					System.out.println(attributesValues.get(j).getName() + "->> "+ "yes" );
				}else{
					pai.getArestas().add(aresta);
					Node decision = new Node();
					decision.setName("no");
					pai.getNodes().add(decision);
					System.out.println(attributesValues.get(j).getName() + "->> "+ "no" );
				}
				break;
			}
		}
	}


}


