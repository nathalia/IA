package br.usp.ia.test;

import java.text.DecimalFormat;

import junit.framework.Assert;

import org.junit.Test;

import br.usp.ia.controller.ID3Utils;

public class TestEntropy {
	private ID3Utils cc = new ID3Utils();
	
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
	
	@Test
	public void testeQuatroZero(){
		double result = cc.entropy(4, 0);
		DecimalFormat df = new DecimalFormat("0.000");  
		Assert.assertEquals(df.format(result), "0,000");
	}
	
	@Test
	public void testeTresDois(){
		double result = cc.entropy(3, 2);
		DecimalFormat df = new DecimalFormat("0.000");  
		Assert.assertEquals(df.format(result), "0,971");
	}	
	
	@Test
	public void testeTresUm(){
		double result = cc.entropy(3, 1);
		DecimalFormat df = new DecimalFormat("0.000");  
		Assert.assertEquals(df.format(result), "0,811");
	}	
}
