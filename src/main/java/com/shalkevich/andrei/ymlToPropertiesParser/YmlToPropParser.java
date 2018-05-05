package com.shalkevich.andrei.ymlToPropertiesParser;

import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Parsing .yml to .properties class
 * @author Andrei Shalkevich
 *
 */
public class YmlToPropParser implements Parser<String>{
	
	final static Logger logger = LogManager.getLogger(YmlToPropParser.class);
	
	/**
	 * String builder field
	 */
	private StringBuilder builder;
	
	/**
	 * List field
	 */
	private List<String> list;
	
	/**
	 * Constructor - new object creating with 2 values
	 * @param builder - output string builder
	 * @param list - list for output strings
	 */
	public YmlToPropParser(StringBuilder builder, List<String> list) {
		super();
		this.builder = builder;
		this.list = list;
	}
	
	
	public StringBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(StringBuilder builder) {
		this.builder = builder;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
	 /**
     * Method for parsing row by row  input .yml file
     * @param inputStream - string stream from input .yml file
     * @return return string stream for output .properties file
     */
	@Override
	public Stream<String> parse(Stream<String> inputStream) {
		
		logger.trace("Inside parse() method");
		
		inputStream.map(s->s.trim()).forEach(x->{
			if (x.contains(":")) {

				if (!x.contains(" "))
					builder.append(x.replace(":", "."));
				else {
					String str = x.replace("   ", "");
					
					list.add(builder.toString() + str);

				}
			}
		});
	
		return list.stream();
	}
}
