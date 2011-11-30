package br.usp.ia.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import br.usp.ia.controller.ID3Utils;
import br.usp.ia.model.Entry;
import br.usp.ia.model.ValuedAttribute;
import junit.framework.TestCase;

public class TestParticao extends TestCase {
	public void testaFoldValidation(){
		List<Entry> learningSet = new ArrayList<Entry>();
		
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
		learningSet.add(e);
		learningSet.add(e);
		learningSet.add(e);
		learningSet.add(e);
		learningSet.add(e);
		learningSet.add(e);
		learningSet.add(e);
		learningSet.add(e);
		learningSet.add(e);
		learningSet.add(e);
		learningSet.add(e);
		learningSet.add(e);
		learningSet.add(e);
		learningSet.add(e);
		
		List<List<Entry>> lista = ID3Utils.foldCrossValidation(learningSet);
		
		Assert.assertTrue(lista.size()==10);
	}
}
