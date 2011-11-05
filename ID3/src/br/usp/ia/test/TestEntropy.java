package br.usp.ia.test;

import java.text.DecimalFormat;

import junit.framework.Assert;

import org.junit.Test;

import br.usp.ia.controller.CalculusController;

public class TestEntropy {
	private CalculusController cc = new CalculusController();
	
	@Test
	public void testNoveCinco(){
		double result = cc.entropy(9, 5);
		DecimalFormat df = new DecimalFormat("0.000");  
		Assert.assertEquals(df.format(result), "0,940");
	}
	@Test
	public void testeDoisTres(){
		double result = cc.entropy(2, 3);
		DecimalFormat df = new DecimalFormat("0.000");  
		Assert.assertEquals(df.format(result), "0,971");
	}
	
}
