package br.usp.ia.test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.usp.ia.controller.ID3Utils;
import br.usp.ia.model.Value;

public class TestGain {
	private ID3Utils cc = new ID3Utils();
	
	@Test
	public void testGanhoAparencia(){
		List<Value> valores = new ArrayList<Value>();
		//Aparência
		Value valor1 = new Value("Ensolarado", 2, 3);
		Value valor2 = new Value("Nublado", 4, 0);
		Value valor3 = new Value("Chuva", 3, 2);
		
		valores.add(valor1);
		valores.add(valor2);
		valores.add(valor3);
		
		double result = cc.entropy(9, 5);
		
		double resultado = cc.gain(result , valores, 14);
		DecimalFormat df = new DecimalFormat("0.000");
		Assert.assertEquals(df.format(resultado), "0,247");
	}
	
	@Test
	public void testGanhoTemperatura(){
		List<Value> valores = new ArrayList<Value>();
		//Aparência
		Value valor1 = new Value("Quente", 2, 2);
		Value valor2 = new Value("Moderada", 4, 2);
		Value valor3 = new Value("Fria", 3, 1);
		
		valores.add(valor1);
		valores.add(valor2);
		valores.add(valor3);
		
		double result = cc.entropy(9, 5);
		
		double resultado = cc.gain(result , valores, 14);
		DecimalFormat df = new DecimalFormat("0.000");
		Assert.assertEquals(df.format(resultado), "0,029");
	}
	
	@Test
	public void testGanhoUmidade(){
		List<Value> valores = new ArrayList<Value>();
		//Aparência
		Value valor1 = new Value("Alta", 4, 3);
		Value valor2 = new Value("Normal", 6, 1);
		
		valores.add(valor1);
		valores.add(valor2);
		
		double result = cc.entropy(9, 5);
		
		double resultado = cc.gain(result , valores, 14);
		DecimalFormat df = new DecimalFormat("0.000");
		Assert.assertEquals(df.format(resultado), "0,152");
	}
}
