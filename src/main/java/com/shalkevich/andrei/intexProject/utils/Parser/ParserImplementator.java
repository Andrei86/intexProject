package com.shalkevich.andrei.intexProject.utils.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import lombok.Data;

/**
 * Parsing class
 * @author Andrei Shalkevich
 *
 */
@Data
public class ParserImplementator implements IParser<String>{
	
	private final static Logger logger = LogManager.getLogger(ParserImplementator.class);
	
	/**
	 * Field for building property's key-value string
	 */
	private StringBuilder propertyBuilder;
	
	/**
	 * List field for strings after file parsing
	 */
	private List<String> parsedStringslist;
	
	/**
	 * Delimiter field for parse() method
	 */
	private String delimiter;
	
	/**
	 * Constructor
	 * @param builder - output string builder
	 * @param list - list for output strings
	 */
	public ParserImplementator(String delimiter) {
		super();
		this.delimiter = delimiter;
	}
	
	 /**
     * Method for input file parsing string by string: deletes
     * spaces, replaces property key delimiters with dots, replaces
     * property key-value delimiter with colon
     * @param inputStream - string stream from input file
     * @return return strings stream for output file
     */
	@Override
	public Stream<String> parse(Stream<String> inputStream) {
		
		logger.info("Inside parse() method");
		
		inputStream.map(s->s.trim()).forEach(x->{
			if (x.contains(delimiter)) {

				if (!x.contains(" "))
					
					propertyBuilder.append(x.replace(delimiter, "."));
				else {
					String[] propertyKeyValuePair = x.split(delimiter + " ");
					String propertyKeyValue = propertyKeyValuePair[0] + ":" + (propertyKeyValuePair[1].trim());
					parsedStringslist.add(propertyBuilder.toString() + propertyKeyValue);
				}
			}
		});
		return parsedStringslist.stream();
	}
}
