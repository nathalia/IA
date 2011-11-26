package br.usp.ia.controller;

import java.util.ArrayList;

import br.usp.ia.model.Entry;
import br.usp.ia.model.Node;
import br.usp.ia.model.ValuedAttribute;

public class ID3Inference {

	public static int analysis(Node root, Entry e){
		ArrayList<ValuedAttribute> attributes = e.getAttributes();
		if((root.getName().equals(e.getAttributes().get(e.getAttributes().size()-1).getValue())))
			return 1;
		for (ValuedAttribute valuedAttribute : attributes) {
			int i =0;
			for (String aresta : root.getArestas()) {
				if(root.getName().equals(valuedAttribute.getName()) &&
						valuedAttribute.getValue().equals(aresta)){
					System.out.println(root.getNodes().get(i).getName());
					e.removeAttribute(i);
					return analysis(root.getNodes().get(i), e);
				}
				i++;
			}
		}
		
		if(root.getNodes().get(root.getNodes().size()-1).getName().equals(e.getAttributes().get(e.getAttributes().size()-1).getValue()))
			return 1;
		return 0;
	}

}
