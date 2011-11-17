package br.usp.ia.controller;

import java.util.ArrayList;

import br.usp.ia.model.Attribute;
import br.usp.ia.model.ComparableEntry;
import br.usp.ia.model.Entry;
import br.usp.ia.model.Value;

public class AdultCensus {

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
		buildTree(learningSet, attributesValues, "","raiz");

	}
	
	public static void buildTree(ArrayList<Entry> learningSet, ArrayList<Attribute> attributesValues,
			String pai, String aresta){
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
		System.out.println(pai + "-[ "+ aresta + " ]-" +attributesValues.get(j).getName());
		
		for (String possibleValue : attributesValues.get(j).getPossibleValues()) {
			if(difzero!=0){
			ArrayList<Entry> newSet = ID3Utils.partialSet(learningSet, j, possibleValue);
			ArrayList<Attribute> newAttributesValues = ID3Utils.partialAttributes(attributesValues, j);
			buildTree(newSet, newAttributesValues, attributesValues.get(j).getName(), 
					possibleValue);
			}else{
				Value v = ID3Utils.countLabelsAC(learningSet);
				if(v.getNegative()<v.getPositive()){
					System.out.println(attributesValues.get(j).getName() + "->> "+ ">50K" );
				}else{
					System.out.println(attributesValues.get(j).getName() + "->> "+ "<=50K" );
				}
				break;
			}
		}
	}
}

