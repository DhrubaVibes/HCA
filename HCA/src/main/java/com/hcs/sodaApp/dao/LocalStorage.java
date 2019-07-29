package com.hcs.sodaApp.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.hcs.sodaApp.model.Soda;

public class LocalStorage {

	private FileCrud fileCrud;

	public LocalStorage() {
		fileCrud = new FileCrud();
	}

	public Soda get(String brand) {

		Soda[] sodas = fileCrud.read();
		
		for(Soda soda : sodas) {
			if(soda.getBrand().equals(brand)) {
				soda.setQuantity(soda.getQuantity() - 1);
			}
		}
	
		Soda soda = Arrays.asList(sodas).stream().filter(p -> p.getBrand().equals(brand)).findFirst().get();
		fileCrud.updateFile(sodas);
		fileCrud.updateInvoice(soda);

		return soda;
	}
	

	public Map<String, Long> findCount() {
		Soda[] sodas = fileCrud.read();

		Map<String, Long> cm = Arrays.asList(sodas).stream()
				.collect(Collectors.groupingBy(Soda::getBrand, Collectors.counting()));
		return cm;
	}

	public Set<Soda> findAllBrands() {

		Soda[] sodas = fileCrud.read();
		Set<Soda> temp = Arrays.asList(sodas).stream().collect(Collectors.toSet());
		return new TreeSet<Soda>(temp);

	}

	/*
	 * private helper class to deal with the file contents.
	 * 
	 */
	private class FileCrud {

		private final String FILENAME = "src/main/resources/local-db.txt";
		private final String INVOICE = "src/main/resources/invoice.txt";
		private final String TAB = "	";
		private final String NEWLINE = "\n";

		public FileCrud() {

		}

		public Soda[] read() {
			ObjectMapper mapper = new ObjectMapper();

			File file = new File(FILENAME);
			
			Soda[] sodas = null;
			try {
				sodas = mapper.readValue(file, Soda[].class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return sodas;
		}

		private String format(String value, int width) {	
			StringBuilder sb = new StringBuilder(value);			
			if(value.length() < width) {

				for(int i = value.length(); i <= width; i++) {
					sb.append(" ");
					
				}
			}
			return sb.toString();
		}
		
		public void updateInvoice(Soda soda) {
			File f = new File(INVOICE);
	        try {
	            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
	            String line = new Date() + TAB + format(soda.getType(),9) + TAB + format(soda.getBrand(),9) + TAB + format(String.valueOf(soda.getPrice()), 5)  + TAB + format(String.valueOf(1), 5) + NEWLINE;
	            bw.append(line);
	            bw.close();
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
			
		}

		public void updateFile(Soda[] sodas) {
			ObjectMapper mapper = new ObjectMapper();
			File file = new File(FILENAME);
			try {
				mapper.writeValue(file, sodas);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
}
