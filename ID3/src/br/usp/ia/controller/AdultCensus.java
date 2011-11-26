package br.usp.ia.controller;

import java.util.ArrayList;

import br.usp.ia.model.Attribute;
import br.usp.ia.model.ComparableEntry;
import br.usp.ia.model.Entry;
import br.usp.ia.model.Node;
import br.usp.ia.model.Value;
import br.usp.ia.model.ValuedAttribute;

public class AdultCensus {
	static int countNodes = 0;
	public static void main(String[] args) {

//		String nonMissing = FileReader.removeMissingValues("adult.data");
//		String nextFile;
//		ArrayList<ComparableEntry> initialSet = FileReader.comparableFile(nonMissing, 0);
//		double bestMean0 = ID3Utils.discretizer(initialSet);
//		nextFile = FileReader.discretize(nonMissing, 0, bestMean0);
//		System.out.println(1);
//		initialSet = FileReader.comparableFile(nonMissing, 2);
//		double bestMean2 = ID3Utils.discretizer(initialSet);
//		nextFile = FileReader.discretize(nextFile, 2, bestMean2);
//		System.out.println(2);
//		initialSet = FileReader.comparableFile(nextFile, 4);
//		double bestMean4 = ID3Utils.discretizer(initialSet);
//		nextFile = FileReader.discretize(nextFile, 4, bestMean4);
//		System.out.println(3);
//		initialSet = FileReader.comparableFile(nonMissing, 10);
//		double bestMean10 = ID3Utils.discretizer(initialSet);
//		nextFile = FileReader.discretize(nextFile, 10, bestMean10);
//		System.out.println(4);
//		initialSet = FileReader.comparableFile(nonMissing, 11);
//		double bestMean11 = ID3Utils.discretizer(initialSet);
//		nextFile = FileReader.discretize(nextFile, 11, bestMean11);
//		System.out.println(5);
//		initialSet = FileReader.comparableFile(nonMissing, 12);
//		double bestMean12 = ID3Utils.discretizer(initialSet);
//		nextFile = FileReader.discretize(nextFile, 12, bestMean12);
//System.out.println(6);
		ArrayList<Entry> learningSet = FileReader.readFile("discretized12.data");
		ArrayList<Attribute> attributesValues = FileReader.getAttributesValues();		
		Node root = new Node();
		buildTree(learningSet, attributesValues, root, "", "");
		Entry e = new Entry();
		ArrayList<ValuedAttribute> attribs = new ArrayList<ValuedAttribute>();
		ValuedAttribute at1 = new ValuedAttribute("education-num", "high4");
		ValuedAttribute at2 = new ValuedAttribute("marital-status", "Never-married");
		ValuedAttribute at3 = new ValuedAttribute("fnlwgt", "low2");
		ValuedAttribute at4 = new ValuedAttribute("education", "Bachelors");
		ValuedAttribute at5 = new ValuedAttribute("race", "White");
		ValuedAttribute at6 = new ValuedAttribute("relationship", "Not-in-family");
		ValuedAttribute at7 = new ValuedAttribute("occupation", "Adm-clerical");
		ValuedAttribute at8 = new ValuedAttribute("sex", "Male");
		ValuedAttribute at9 = new ValuedAttribute("capital-gain", "low10");
		ValuedAttribute at10 = new ValuedAttribute("capital-loss", "low11");
		ValuedAttribute at11 = new ValuedAttribute("hours-per-week", "high12");
		ValuedAttribute at12 = new ValuedAttribute("workclass", "State-gov");
		ValuedAttribute at13 = new ValuedAttribute("decision", "<=50K");
		attribs.add(at1);
		attribs.add(at2);
		attribs.add(at3);
		attribs.add(at4);
		attribs.add(at5);
		attribs.add(at6);
		attribs.add(at7);
		attribs.add(at8);
		attribs.add(at9);
		attribs.add(at10);
		attribs.add(at11);
		attribs.add(at12);
		attribs.add(at13);

		e.setAttributes(attribs);
		System.out.println(ID3Inference.analysis(root.getNodes().get(0), e));
		System.out.println(countNodes);

	}
	
	public static void buildTree(ArrayList<Entry> learningSet, ArrayList<Attribute> attributesValues,
			Node pai, String nomePai, String aresta){
		if(learningSet.size() == 0 || attributesValues.size()==0) return;
		Value rootValues = ID3Utils.countLabelsAC(learningSet);
		double initial = ID3Utils.entropy(rootValues.getNegative(), rootValues.getPositive());

		double[] gains = new double[learningSet.get(0).getAttributes().size()-1];

		int i = 0;
		int j = -1;
		double max = 0.0;
		for(int k = 0; k<gains.length; k++){
			gains[k] = ID3Utils.countAttributeAC(initial, learningSet, attributesValues.get(i), i);
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
		countNodes++;
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
				Value v = ID3Utils.countLabelsAC(learningSet);
				if(v.getNegative()<v.getPositive()){
					pai.getArestas().add(aresta);
					Node decision = new Node();
					decision.setName(">50K");
					pai.getNodes().add(decision);
					System.out.println(attributesValues.get(j).getName() + "->> "+ ">50K" );
				}else{
					pai.getArestas().add(aresta);
					Node decision = new Node();
					decision.setName("<=50K");
					pai.getNodes().add(decision);
					System.out.println(attributesValues.get(j).getName() + "->> "+ "<=50K" );
				}
				break;
			}
		}
	}

}

