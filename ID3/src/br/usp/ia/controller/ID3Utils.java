package br.usp.ia.controller;

import java.util.List;

import br.usp.ia.model.Valor;

public class ID3Utils {
	
	public static double entropy(float positive, float negative){
		if (positive == 0.0 || negative == 0.0){
			return 0.0;
		}
		float proporcao1 = positive/(positive+negative);
		float proporcao2 = negative/(positive+negative);
		return -proporcao1*(Math.log(proporcao1)/Math.log(2))-proporcao2*(Math.log(proporcao2)/Math.log(2));
	}
	
	public static double gain(double entropiaRaiz, List<Valor> entropias, int total){
		double ganho = entropiaRaiz;
		double entropia;
		float prop; 
		float tot = total;
		for (Valor valor : entropias) {
			entropia = entropy(valor.getPosisivo(), valor.getNegativo());
			prop = (valor.getPosisivo()+valor.getNegativo())/tot;
			ganho += -(prop*entropia);
		}
		
		return ganho;
	}
	
}
