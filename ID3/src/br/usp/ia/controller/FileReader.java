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
import br.usp.ia.model.Entry;

public class FileReader {

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
				
				while((lin = brInput.readLine()) != null){
					Entry e = new Entry();
					String outlook = lin.substring(0, lin.indexOf(',')).toLowerCase();
					lin = lin.substring(lin.indexOf(',')+1, lin.length());
					String temperature = lin.substring(0, lin.indexOf(',')).toLowerCase();
					lin = lin.substring(lin.indexOf(',')+1, lin.length()).toLowerCase();
					String humidity =  lin.substring(0, lin.indexOf(','));
					lin = lin.substring(lin.indexOf(',')+1, lin.length()).toLowerCase();
					String wind =  lin.substring(0, lin.indexOf(','));
					lin = lin.substring(lin.indexOf(',')+1, lin.length()).toLowerCase();
					String playTennis =  lin.substring(0, lin.length()).toLowerCase();
				
					ArrayList<Attribute> attributes = new ArrayList<Attribute>();
					attributes.add(new Attribute("outlook", outlook));
					attributes.add(new Attribute("temperature", temperature));
					attributes.add(new Attribute("humidity", humidity));
					attributes.add(new Attribute("wind", wind));
					attributes.add(new Attribute("playTennis", playTennis));
					e.setAttributes(attributes);
					entries.add(e);
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

}
