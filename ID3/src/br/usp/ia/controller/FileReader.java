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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import br.usp.ia.model.Attribute;
import br.usp.ia.model.ComparableEntry;
import br.usp.ia.model.ValuedAttribute;
import br.usp.ia.model.Entry;

public class FileReader {
	private static ArrayList<Attribute> attributesValues = new ArrayList<Attribute>();
	public static ArrayList<Attribute> getAttributesValues() {
		return attributesValues;
	}

	public static ArrayList<Entry> readFile(String filename){
		File file = new File(filename);
		ArrayList<Entry> entries = null;
		int countMissing = 0;
		if(file.exists()){
			try {
				entries = new ArrayList<Entry>();
				String lin;
				InputStream input;	
				input = new BufferedInputStream(new FileInputStream(file));
				Reader readerInput = new InputStreamReader(input);
				BufferedReader brInput = new BufferedReader(readerInput);

				lin = brInput.readLine();
				String[] attrNames = lin.split(",");
				ArrayList<Attribute> attributes = new ArrayList<Attribute>();

				for(int i = 0; i<attrNames.length; i++)
					attributes.add(new Attribute(attrNames[i]));


				while((lin = brInput.readLine()) != null){
					Entry e = new Entry();
					String[] values = lin.split(",");
					boolean skip = false;

					for(int i = 0; i< values.length; i++){
						values[i] = values[i].trim();
						if(values[i].equals("?")){
							countMissing++;
							skip = true;
							break;
						}
						if(!attributes.get(i).getPossibleValues().contains(values[i]))
							attributes.get(i).add(values[i]);
					}
					if(!skip){
						ArrayList<ValuedAttribute> valuedAttributes = new ArrayList<ValuedAttribute>();

						for(int i = 0; i<values.length; i++)
							valuedAttributes.add(new ValuedAttribute(attrNames[i], values[i]));
						e.setAttributes(valuedAttributes);
						entries.add(e);
					}
				}

				attributesValues = new ArrayList<Attribute>();
				for(int i = 0; i< attributes.size(); i++){
					attributesValues.add(attributes.get(i));
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
		}else{
			System.out.println("File not found.");
		}

		System.out.println(countMissing);
		return entries;
	}

	public static ArrayList<ComparableEntry> readTestFile(String filename){
		File file = new File(filename);
		ArrayList<ComparableEntry> entries = null;
		int countMissing = 0;
		if(file.exists()){
			try {
				entries = new ArrayList<ComparableEntry>();
				String lin;
				InputStream input;	
				input = new BufferedInputStream(new FileInputStream(file));
				Reader readerInput = new InputStreamReader(input);
				BufferedReader brInput = new BufferedReader(readerInput);

				lin = brInput.readLine();
				String[] attrNames = lin.split(",");
				ArrayList<Attribute> attributes = new ArrayList<Attribute>();

				for(int i = 0; i<attrNames.length; i++)
					attributes.add(new Attribute(attrNames[i]));


				while((lin = brInput.readLine()) != null){
					ComparableEntry e = new ComparableEntry();
					String[] values = lin.split(",");
					boolean skip = false;
					
					for(int i = 0; i< values.length; i++){
						if(i==0 || i == values.length-1){
						values[i] = values[i].trim();
						if(values[i].equals("?")){
							countMissing++;
							skip = true;
							break;
						}
						if(!attributes.get(i).getPossibleValues().contains(values[i]))
							attributes.get(i).add(values[i]);
					}
					}
					if(!skip){
						ArrayList<ValuedAttribute> valuedAttributes = new ArrayList<ValuedAttribute>();

						for(int i = 0; i<values.length; i++)
							valuedAttributes.add(new ValuedAttribute(attrNames[i], values[i]));
						e.setValue(Double.parseDouble(valuedAttributes.get(0).getValue()));
						e.setLabel(valuedAttributes.get(14).getValue());
						entries.add(e);
					}
				}

				attributesValues = new ArrayList<Attribute>();
				for(int i = 0; i< attributes.size(); i++){
					attributesValues.add(attributes.get(i));
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
		}else{
			System.out.println("File not found.");
		}

		System.out.println(countMissing);
		return entries;
	}


}
