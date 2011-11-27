package br.usp.ia.controller;

import java.util.ArrayList;
import java.util.List;

import br.usp.ia.model.Attribute;
import br.usp.ia.model.Entry;
import br.usp.ia.model.Node;
import br.usp.ia.model.Value;
import br.usp.ia.model.ValuedAttribute;

public class PlayTennis {
	public static void main(String[] args) {

		ArrayList<Entry> learningSet = FileReader.readFile("playtennis.data");
		ArrayList<Attribute> attributesValues = FileReader.getAttributesValues();

		//particionar
		//List<List<Entry>> listLearningSet = ID3Utils.foldCrossValidation(learningSet); 

		//contruir árvore com r-1 folds
		Node root = new Node();
		root = buildTree(learningSet, attributesValues);			
		Entry e = new Entry();


		e = FileReader.testTree("playtennisTest.data");
		for (ValuedAttribute attribute : e.getAttributes()) {
			System.out.println(attribute.getName() + " e " + attribute.getValue());
		}
		System.out.println(ID3Inference.analysis(root, e));
	}


	public static Node buildTree(ArrayList<Entry> learningSet, ArrayList<Attribute> attributesValues){
		Value rootValues = ID3Utils.countLabels(learningSet);

		double initial = ID3Utils.entropy(rootValues.getNegative(), rootValues.getPositive());
		Node root = new Node();
		Value v = ID3Utils.countLabels(learningSet);
		if(v.getNegative()==0){
			root.setArestas(new ArrayList<String>());
			root.setNodes(new ArrayList<Node>());
			root.setName("yes");
		}else if (v.getPositive()==0){
			root.setArestas(new ArrayList<String>());
			root.setNodes(new ArrayList<Node>());
			root.setName("no");
		}else if((attributesValues.size()==1)) {
			Value vF = ID3Utils.countLabels(learningSet);
			if(vF.getNegative()<vF.getPositive()){
				Node decision = new Node();
				decision.setName("yes");
				root.getNodes().add(decision);
				return root;
			}else{
				Node decision = new Node();
				decision.setName("no");
				root.getNodes().add(decision);
				return root;
			}
		}

		else{

			double[] gains = new double[learningSet.get(0).getAttributes().size()-1];
			if(attributesValues.size()==0) return null;
			int i = 0;
			int j = 0;
			double max = 0.0;
			for(int k = 0; k<gains.length-1; k++){
				gains[k] = ID3Utils.countAttribute(initial, learningSet, attributesValues.get(i), i);
				if(gains[k]>=max){
					max = gains[k];
					j = k;
				}
				i++;	
			}
			root.setName(attributesValues.get(j).getName());

			for (String possibleValue : attributesValues.get(j).getPossibleValues()) {
				Value vP = ID3Utils.countLabels(learningSet);
				root.getArestas().add(possibleValue);
				ArrayList<Entry> newSet = ID3Utils.partialSet(learningSet, j, possibleValue);
				ArrayList<Attribute> newAttributesValues = ID3Utils.partialAttributes(attributesValues, j);

				if(newSet.size()==0){
					if(vP.getNegative()<vP.getPositive()){
						Node decision = new Node();
						decision.setName("yes");
						root.getNodes().add(decision);
					}else{
						Node decision = new Node();
						decision.setName("no");
						root.getNodes().add(decision);
					}
				}
				else{
					root.getNodes().add(buildTree(newSet, newAttributesValues));
				}
			}
		}
		return root;
	}
}


