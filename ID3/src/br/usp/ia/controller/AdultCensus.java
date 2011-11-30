package br.usp.ia.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.usp.ia.model.Attribute;
import br.usp.ia.model.Entry;
import br.usp.ia.model.Node;
import br.usp.ia.model.Value;

public class AdultCensus {
	static int countNodes = 0;
	static List<Attribute> usedAttributes = new ArrayList<Attribute>();
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
		root = buildTree("",learningSet, attributesValues, 0);
		Entry e = new Entry();
		e = FileReader.testTree("discretized12Test.data");
		System.out.println(ID3Inference.analysis(root, e));
		System.out.println(countNodes);

	}

	public static Node buildTree(String pre, ArrayList<Entry> learningSet, ArrayList<Attribute> attributesValues,
			int hierarchy){
		Value rootValues = ID3Utils.countLabelsAC(learningSet);

		double initial = ID3Utils.entropy(rootValues.getNegative(), rootValues.getPositive());
		Node root = new Node();
		countNodes++;
		Value v = ID3Utils.countLabelsAC(learningSet);
		if(v.getNegative()==0){
			root.setArestas(new ArrayList<String>());
			root.setNodes(new ArrayList<Node>());
			root.setName(">50K");
			System.out.println(pre+"decide "+ root.getName());
			countNodes--;
		}else if (v.getPositive()==0){
			root.setArestas(new ArrayList<String>());
			root.setNodes(new ArrayList<Node>());
			root.setName("<=50K");
			System.out.println(pre+"decide "+ root.getName());
			countNodes--;
		}else if((attributesValues.size()==1)) {
			Value vF = ID3Utils.countLabelsAC(learningSet);
			if(vF.getNegative()<vF.getPositive()){
				Node decision = new Node();
				decision.setName(">50K");
				System.out.println(pre+"decide "+ decision.getName());
				countNodes--;
				root.getNodes().add(decision);
				return root;
			}else{
				Node decision = new Node();
				decision.setName("<=50K");
				System.out.println(pre+"decide "+ decision.getName());
				countNodes--;
				root.getNodes().add(decision);
				return root;
			}
		}

		else{

			double[] gains = new double[learningSet.get(0).getAttributes().size()-1];
			if(attributesValues.size()==0) return null;
			int j = 0;
			double max = 0.0;

			for(int k = 0; k<gains.length; k++){
					gains[k] = ID3Utils.countAttributeAC(initial, learningSet, attributesValues.get(k), k);
//					if(gains[k]>=max && !ID3Utils.verifyExistence(usedAttributes, hierarchy, attributesValues.get(k))){

				if(gains[k]>max){	
						max = gains[k];
						j = k;
					}
			}
			
			Attribute use = new Attribute((attributesValues.get(j).getName()));
			use.setPossibleValues((attributesValues.get(j).getPossibleValues()));
			use.setHierarchy(hierarchy);
			usedAttributes.add(use);
			
			root.setName(attributesValues.get(j).getName());
			ArrayList<Entry> newSet = null;
			ArrayList<Attribute> newAttributesValues = null;
			int n = 0;
			for (String possibleValue : attributesValues.get(j).getPossibleValues()) {
				Value vP = ID3Utils.countLabelsAC(learningSet);
				root.getArestas().add(possibleValue);
				newSet = ID3Utils.partialSet(learningSet, j, possibleValue);
				newAttributesValues = ID3Utils.partialAttributes(attributesValues, j);
				if(newSet.size()==0){
					if(vP.getNegative()<vP.getPositive()){
						Node decision = new Node();
						decision.setName(">50K");
						System.out.println(pre+"decide "+ decision.getName());
						countNodes--;
						root.getNodes().add(decision);
					}else{
						Node decision = new Node();
						decision.setName("<=50K");
						System.out.println(pre+"decide "+ decision.getName());
						countNodes--;
						root.getNodes().add(decision);
					}
					break;
				}
				else{
					System.out.println(pre+root.getName() + " atraves de " + root.getArestas().get(n));
					root.getNodes().add(buildTree(pre+" ",newSet, newAttributesValues, hierarchy+1));
				}
				n++;
			}
		}
		return root;
	}}

