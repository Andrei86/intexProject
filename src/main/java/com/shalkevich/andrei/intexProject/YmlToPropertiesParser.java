package com.shalkevich.andrei.intexProject;

import java.util.List;
import java.util.stream.Stream;


/**
 * Parsing .yml to .properties class
 * @author Andrei Shalkevich
 *
 */
public class YmlToPropertiesParser implements Parser<String>{
	
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
	public YmlToPropertiesParser(StringBuilder builder, List<String> list) {
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
		
		inputStream.map(s -> s.trim()).forEach(x -> {
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
