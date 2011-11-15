package br.usp.ia.controller;

import java.util.ArrayList;

import br.usp.ia.model.Attribute;
import br.usp.ia.model.Entry;
import br.usp.ia.model.Value;

public class Main {

	public static void main(String[] args) {

		ArrayList<Entry> LearningSet = FileReader.readFile();

		ArrayList<Value> entropies = new ArrayList<Value>();
		Value sunny;
		Value rain;
		Value overcast;

		int positive1 = 0;
		int negative1 = 0;		

		int positive2 = 0;
		int negative2 = 0;

		int positive3 = 0;
		int negative3 = 0;
		
		//Outlook
		for (Entry entry : LearningSet) {

			ArrayList<Attribute> attributes = entry.getAttributes();

			//Sunny
			if(attributes.get(0).getValue()==Attribute.getSunny() && 
					attributes.get(4).getValue()==Attribute.getPlayNo())
				negative1++;
			else if(attributes.get(0).getValue()==Attribute.getSunny() && 
					attributes.get(4).getValue()==Attribute.getPlayYes())
				positive1++;

			//Rain
			if(attributes.get(0).getValue()==Attribute.getRain() && 
					attributes.get(4).getValue()==Attribute.getPlayNo())
				negative2++;
			else if(attributes.get(0).getValue()==Attribute.getRain() && 
					attributes.get(4).getValue()==Attribute.getPlayYes())
				positive2++;

			//Overcast
			if(attributes.get(0).getValue()==Attribute.getOvercast() && 
					attributes.get(4).getValue()==Attribute.getPlayNo())
				negative3++;
			else if(attributes.get(0).getValue()==Attribute.getOvercast() && 
					attributes.get(4).getValue()==Attribute.getPlayYes())
				positive3++;

		}


		sunny = new Value("sunny", negative1, positive1);
		entropies.add(sunny);

		rain = new Value("rain", negative2, positive2);
		entropies.add(rain);

		overcast = new Value("overcast", negative3, positive3);
		entropies.add(overcast);

		Double gainOverlook = ID3Utils.gain(1, entropies, 14);
		System.out.println(gainOverlook);
		
		
		entropies.clear();
		Value cool;
		Value mild;
		Value hot;

		positive1 = 0;
		negative1 = 0;		

		positive2 = 0;
		negative2 = 0;

		positive3 = 0;
		negative3 = 0;
		
		//Temperature
		for (Entry entry : LearningSet) {

			ArrayList<Attribute> attributes = entry.getAttributes();

			//Cool
			if(attributes.get(1).getValue()==Attribute.getCool() && 
					attributes.get(4).getValue()==Attribute.getPlayNo())
				negative1++;
			else if(attributes.get(1).getValue()==Attribute.getCool() && 
					attributes.get(4).getValue()==Attribute.getPlayYes())
				positive1++;

			//Mild
			if(attributes.get(1).getValue()==Attribute.getMild() && 
					attributes.get(4).getValue()==Attribute.getPlayNo())
				negative2++;
			else if(attributes.get(1).getValue()==Attribute.getMild() && 
					attributes.get(4).getValue()==Attribute.getPlayYes())
				positive2++;

			//Hot
			if(attributes.get(1).getValue()==Attribute.getHot() && 
					attributes.get(4).getValue()==Attribute.getPlayNo())
				negative3++;
			else if(attributes.get(1).getValue()==Attribute.getHot() && 
					attributes.get(4).getValue()==Attribute.getPlayYes())
				positive3++;

		}


		cool = new Value("cool", negative1, positive1);
		entropies.add(cool);

		mild = new Value("mild", negative2, positive2);
		entropies.add(mild);

		hot = new Value("hot", negative3, positive3);
		entropies.add(hot);

		Double gainTemperature = ID3Utils.gain(1, entropies, 14);
		System.out.println(gainTemperature);
		
		entropies.clear();
		Value normal;
		Value high;

		positive1 = 0;
		negative1 = 0;		

		positive2 = 0;
		negative2 = 0;

		//Humidity
		for (Entry entry : LearningSet) {

			ArrayList<Attribute> attributes = entry.getAttributes();

			//Normal
			if(attributes.get(2).getValue()==Attribute.getNormal() && 
					attributes.get(4).getValue()==Attribute.getPlayNo())
				negative1++;
			else if(attributes.get(2).getValue()==Attribute.getNormal() && 
					attributes.get(4).getValue()==Attribute.getPlayYes())
				positive1++;

			//High
			if(attributes.get(2).getValue()==Attribute.getHigh() && 
					attributes.get(4).getValue()==Attribute.getPlayNo())
				negative2++;
			else if(attributes.get(2).getValue()==Attribute.getHigh() && 
					attributes.get(4).getValue()==Attribute.getPlayYes())
				positive2++;


		}


		normal = new Value("normal", negative1, positive1);
		entropies.add(normal);

		high = new Value("high", negative2, positive2);
		entropies.add(high);


		Double gainHumidity = ID3Utils.gain(1, entropies, 14);
		System.out.println(gainHumidity);
		
		entropies.clear();
		Value weak;
		Value strong;

		positive1 = 0;
		negative1 = 0;		

		positive2 = 0;
		negative2 = 0;

		//Wind
		for (Entry entry : LearningSet) {

			ArrayList<Attribute> attributes = entry.getAttributes();

			//Weak
			if(attributes.get(3).getValue()==Attribute.getWeak() && 
					attributes.get(4).getValue()==Attribute.getPlayNo())
				negative1++;
			else if(attributes.get(3).getValue()==Attribute.getWeak() && 
					attributes.get(4).getValue()==Attribute.getPlayYes())
				positive1++;

			//Strong
			if(attributes.get(3).getValue()==Attribute.getStrong() && 
					attributes.get(4).getValue()==Attribute.getPlayNo())
				negative2++;
			else if(attributes.get(3).getValue()==Attribute.getStrong() && 
					attributes.get(4).getValue()==Attribute.getPlayYes())
				positive2++;


		}


		weak = new Value("weak", negative1, positive1);
		entropies.add(weak);

		strong = new Value("strong", negative2, positive2);
		entropies.add(strong);


		Double gainWind = ID3Utils.gain(1, entropies, 14);
		System.out.println(gainWind);
		
		
	}

}
