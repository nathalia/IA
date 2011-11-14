package br.usp.ia.test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.usp.ia.controller.ID3Utils;
import br.usp.ia.model.Valor;

public class TestGain {
	private ID3Utils cc = new ID3Utils();
	
	@Test
	public void testGanhoAparencia(){
		List<Valor> valores = new ArrayList<Valor>();
		//Aparência
		Valor valor1 = new Valor("Ensolarado", 2, 3);
		Valor valor2 = new Valor("Nublado", 4, 0);
		Valor valor3 = new Valor("Chuva", 3, 2);
		
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
		List<Valor> valores = new ArrayList<Valor>();
		//Aparência
		Valor valor1 = new Valor("Quente", 2, 2);
		Valor valor2 = new Valor("Moderada", 4, 2);
		Valor valor3 = new Valor("Fria", 3, 1);
		
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
		List<Valor> valores = new ArrayList<Valor>();
		//Aparência
		Valor valor1 = new Valor("Alta", 4, 3);
		Valor valor2 = new Valor("Normal", 6, 1);
		
		valores.add(valor1);
		valores.add(valor2);
		
		double result = cc.entropy(9, 5);
		
		double resultado = cc.gain(result , valores, 14);
		DecimalFormat df = new DecimalFormat("0.000");
		Assert.assertEquals(df.format(resultado), "0,152");
	}
}
