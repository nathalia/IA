package br.usp.ia.controller;

public class CalculusController {
	
	public double entropy(float positive, float negative){
		float proporcao1 = positive/(positive+negative);
		float proporcao2 = negative/(positive+negative);
		return -proporcao1*(Math.log(proporcao1)/Math.log(2))-proporcao2*(Math.log(proporcao2)/Math.log(2));
	}
	
}
