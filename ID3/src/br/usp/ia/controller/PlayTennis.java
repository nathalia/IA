package br.usp.ia.controller;

import java.util.ArrayList;
import java.util.List;

import br.usp.ia.model.Attribute;
import br.usp.ia.model.Entry;
import br.usp.ia.model.Node;
import br.usp.ia.model.Value;
import br.usp.ia.model.ValuedAttribute;

public class PlayTennis {
	static int countNodes = 0;
	public static void main(String[] args) {

		ArrayList<Entry> learningSet = FileReader.readFile("playtennis.data");
		ArrayList<Attribute> attributesValues = FileReader.getAttributesValues();

		//particionar
		//List<List<Entry>> listLearningSet = ID3Utils.foldCrossValidation(learningSet); 

		//contruir árvore com r-1 folds
		Node root = new Node();
		root = buildTree("",learningSet, attributesValues);			
		Entry e = new Entry();


		e = FileReader.testTree("playtennisTest.data");
		System.out.println(ID3Inference.analysis(root, e));
		System.out.println(countNodes);
	}


	public static Node buildTree(String pre, ArrayList<Entry> learningSet, ArrayList<Attribute> attributesValues){
		Value rootValues = ID3Utils.countLabels(learningSet);

		double initial = ID3Utils.entropy(rootValues.getNegative(), rootValues.getPositive());
		Node root = new Node();
		countNodes++;
		Value v = ID3Utils.countLabels(learningSet);
		if(v.getNegative()==0){
			root.setArestas(new ArrayList<String>());
			root.setNodes(new ArrayList<Node>());
			root.setName("yes");
			System.out.println(pre+"decide "+ root.getName());
			countNodes--;
		}else if (v.getPositive()==0){
			root.setArestas(new ArrayList<String>());
			root.setNodes(new ArrayList<Node>());
			root.setName("no");
			System.out.println(pre+"decide "+ root.getName());
			countNodes--;
		}else if((attributesValues.size()==1)) {
			Value vF = ID3Utils.countLabels(learningSet);
			if(vF.getNegative()<vF.getPositive()){
				Node decision = new Node();
				decision.setName("yes");
				System.out.println(pre+"decide "+ decision.getName());
				countNodes--;
				root.getNodes().add(decision);
				return root;
			}else{
				Node decision = new Node();
				decision.setName("no");
				System.out.println(pre+"decide "+ decision.getName());
				countNodes--;
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
			ArrayList<Entry> newSet = null;
			ArrayList<Attribute> newAttributesValues = null;
			int n = 0;
			for (String possibleValue : attributesValues.get(j).getPossibleValues()) {
				Value vP = ID3Utils.countLabels(learningSet);
				root.getArestas().add(possibleValue);
				newSet = ID3Utils.partialSet(learningSet, j, possibleValue);
				newAttributesValues = ID3Utils.partialAttributes(attributesValues, j);
				if(newSet.size()==0){
					if(vP.getNegative()<vP.getPositive()){
						Node decision = new Node();
						decision.setName("yes");
						System.out.println(pre+"decide "+ decision.getName());
						countNodes--;
						root.getNodes().add(decision);
					}else{
						Node decision = new Node();
						decision.setName("no");
						System.out.println(pre+"decide "+ decision.getName());
						countNodes--;
						root.getNodes().add(decision);
					}
					break;
				}
				else{
					System.out.println(pre+root.getName() + " atraves de " + root.getArestas().get(n));
					root.getNodes().add(buildTree(pre+" ",newSet, newAttributesValues));
				}
			n++;
			}
		}
		return root;
	}
}


