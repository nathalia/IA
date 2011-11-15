package br.usp.ia.controller;

import java.util.List;

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
	
}
