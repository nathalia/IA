package br.usp.ia.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import br.usp.ia.model.Attribute;
import br.usp.ia.model.ValuedAttribute;
import br.usp.ia.model.Entry;

public class FileReader {
	private static ArrayList<Attribute> attributesValues = new ArrayList<Attribute>();
	public static ArrayList<Attribute> getAttributesValues() {
		return attributesValues;
	}
	public static ArrayList<Entry> readFile() {
		File file = new File("playtennis.txt");
		ArrayList<Entry> entries = null;
		
		if(file.exists()){
			try {
				entries = new ArrayList<Entry>();
				String lin;
				InputStream input;	
				input = new BufferedInputStream(new FileInputStream(file));
				Reader readerInput = new InputStreamReader(input);
				BufferedReader brInput = new BufferedReader(readerInput);
				
				Attribute attrOverlook = new Attribute("overlook");
				Attribute attrTemperature = new Attribute("temperature");
				Attribute attrHumidity = new Attribute("humidity");
				Attribute attrWind = new Attribute("wind");
				Attribute attrPlayTennis = new Attribute("decision");
				
				
				while((lin = brInput.readLine()) != null){
					Entry e = new Entry();
					String overlook = lin.substring(0, lin.indexOf(',')).toLowerCase();
					lin = lin.substring(lin.indexOf(',')+1, lin.length());
					String temperature = lin.substring(0, lin.indexOf(',')).toLowerCase();
					lin = lin.substring(lin.indexOf(',')+1, lin.length()).toLowerCase();
					String humidity =  lin.substring(0, lin.indexOf(','));
					lin = lin.substring(lin.indexOf(',')+1, lin.length()).toLowerCase();
					String wind =  lin.substring(0, lin.indexOf(','));
					lin = lin.substring(lin.indexOf(',')+1, lin.length()).toLowerCase();
					String playTennis =  lin.substring(0, lin.length()).toLowerCase();
					
					if(!attrOverlook.getPossibleValues().contains(overlook))
						attrOverlook.add(overlook);

					if(!attrTemperature.getPossibleValues().contains(temperature))
						attrTemperature.add(temperature);

					if(!attrHumidity.getPossibleValues().contains(humidity))
						attrHumidity.add(humidity);

					if(!attrWind.getPossibleValues().contains(wind))
						attrWind.add(wind);

					if(!attrPlayTennis.getPossibleValues().contains(playTennis))
						attrPlayTennis.add(playTennis);
					
					ArrayList<ValuedAttribute> attributes = new ArrayList<ValuedAttribute>();
					
					attributes.add(new ValuedAttribute("overlook", overlook));
					attributes.add(new ValuedAttribute("temperature", temperature));
					attributes.add(new ValuedAttribute("humidity", humidity));
					attributes.add(new ValuedAttribute("wind", wind));
					attributes.add(new ValuedAttribute("playTennis", playTennis));
					e.setAttributes(attributes);
					entries.add(e);
				}
				//captura os possiveis valores dos atributos
				attributesValues = new ArrayList<Attribute>();
				attributesValues.add(attrOverlook);
				attributesValues.add(attrTemperature);
				attributesValues.add(attrHumidity);
				attributesValues.add(attrWind);
				attributesValues.add(attrPlayTennis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
		}else{
			System.out.println("File not found.");
		}
		
		return entries;
	}

}
