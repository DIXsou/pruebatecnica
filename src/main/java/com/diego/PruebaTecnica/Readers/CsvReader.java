package com.diego.PruebaTecnica.Readers;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opencsv.CSVReader;

public class CsvReader {
	private static final Logger LOG = LogManager.getLogger(CsvReader.class);
	private char separator;
	private char quote;
	private boolean hasQuote;
	
	public CsvReader() {
		this.separator = ';';
		this.quote = '"';
		this.hasQuote = true;
	}
	
	public CsvReader(char separator, char quote) {
		this.separator = separator;
		this.quote = quote;
		this.hasQuote = true;
	}
	
	public CsvReader(char separator) {
		this.separator = separator;
		this.hasQuote = false;
	}	

	public ArrayList<String[]> read(String pathFile) throws Exception {
		CSVReader reader = null;
		ArrayList<String[]> csvAl = new ArrayList<>();
	      try {
	    	 if (this.hasQuote) {
		         reader = new CSVReader(new FileReader(pathFile),this.separator,this.quote);
	    	 } else {
		         reader = new CSVReader(new FileReader(pathFile),this.separator);
			}
	         String[] nextLine = null;
	         while ((nextLine = reader.readNext()) != null) {
	            csvAl.add(nextLine);
	         }
	      } catch (IOException e) {
	    	  LOG.error("Error with rear the follow file: " + pathFile , e.fillInStackTrace());
	    	  csvAl = null;
	      } finally {
	         if (null != reader) {
				reader.close();
	         } 
	      }
	      return csvAl;
	}
	public char getQuote() {
		return quote;
	}
	public void setQuote(char quote) {
		this.quote = quote;
	}
	public char getSeparator() {
		return separator;
	}
	public void setSeparator(char separator) {
		this.separator = separator;
	}
	
}
