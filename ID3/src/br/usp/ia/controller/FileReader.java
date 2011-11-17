package br.usp.ia.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import br.usp.ia.model.Attribute;
import br.usp.ia.model.ComparableEntry;
import br.usp.ia.model.Entry;
import br.usp.ia.model.ValuedAttribute;

public class FileReader {
	private static ArrayList<Attribute> attributesValues = new ArrayList<Attribute>();
	public static ArrayList<Attribute> getAttributesValues() {
		return attributesValues;
	}

	public static ArrayList<Entry> readFile(String filename){
		File file = new File(filename);
		ArrayList<Entry> entries = null;
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

					for(int i = 0; i< values.length; i++){
						values[i] = values[i].trim();
						if(!attributes.get(i).getPossibleValues().contains(values[i]))
							attributes.get(i).add(values[i]);
					}
						ArrayList<ValuedAttribute> valuedAttributes = new ArrayList<ValuedAttribute>();

						for(int i = 0; i<values.length; i++)
							valuedAttributes.add(new ValuedAttribute(attrNames[i], values[i]));
						e.setAttributes(valuedAttributes);
						entries.add(e);
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

		return entries;
	}

	public static ArrayList<ComparableEntry> comparableFile(String filename, int comp){
		File file = new File(filename);
		ArrayList<ComparableEntry> entries = null;
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
					
					for(int i = 0; i< values.length; i++){
						values[i] = values[i].trim();
						if(!attributes.get(i).getPossibleValues().contains(values[i]))
							attributes.get(i).add(values[i]);
					}
						ArrayList<ValuedAttribute> valuedAttributes = new ArrayList<ValuedAttribute>();

						for(int i = 0; i<values.length; i++)
							valuedAttributes.add(new ValuedAttribute(attrNames[i], values[i]));
						e.setValue(Double.parseDouble(valuedAttributes.get(comp).getValue()));
						e.setLabel(valuedAttributes.get(attributes.size()-1).getValue());
						entries.add(e);
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

		return entries;
	}

	
	public static String removeMissingValues(String filename){
		File file = new File(filename);
		String outputName = "NonMissing"+filename;
		
		int countMissing = 0;

		if(file.exists()){
			try {
				String lin;
				
				InputStream input;	
				input = new BufferedInputStream(new FileInputStream(file));
				Reader readerInput = new InputStreamReader(input);
				BufferedReader brInput = new BufferedReader(readerInput);
				
				PrintWriter printWriter = new PrintWriter(new FileWriter(outputName));
				
				lin = brInput.readLine();
				printWriter.println(lin);
				
				while((lin = brInput.readLine()) != null){
					if(!lin.contains("?")){
						printWriter.println(lin);
					}
					else
						countMissing++;
				}
				printWriter.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
		}else{
			System.out.println("File not found.");
		}

		System.out.println(countMissing);
		return outputName;
	}

	public static String discretize(String filename, int comp, double mean){
		File file = new File(filename);
		String outputName = "discretized" + comp +".data";
		
		if(file.exists()){
			try {
				String lin;
				InputStream input;	
				input = new BufferedInputStream(new FileInputStream(file));
				Reader readerInput = new InputStreamReader(input);
				BufferedReader brInput = new BufferedReader(readerInput);
				
				PrintWriter printWriter = new PrintWriter(new FileWriter(outputName));

				lin = brInput.readLine();
				printWriter.println(lin);

				while((lin = brInput.readLine()) != null){
					ComparableEntry e = new ComparableEntry();
					String[] values = lin.split(",");

					for(int i = 0; i< values.length; i++)
						values[i] = values[i].trim();
					if(Integer.parseInt(values[comp])>=mean){
						values[comp] = "high";
					}else
						values[comp] = "low";
					lin = "";
					for(int i = 0; i<values.length; i++){
						lin = lin+values[i]+",";
					}
						printWriter.println(lin);
				}
				printWriter.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
		}else{
			System.out.println("File not found.");
		}
		return outputName;
	}
}
