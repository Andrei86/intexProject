package com.shalkevich.andrei.intexProject;

import java.util.List;
import java.util.stream.Stream;

public class YmlToPropertiesParser implements Parser<String>{
	
	public YmlToPropertiesParser(StringBuilder builder, List<String> list) {
		super();
		this.builder = builder;
		this.list = list;
	}

	private StringBuilder builder;
	private List<String> list;
	
	

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



	@Override
	public Stream<String> parse(Stream<String> inputStream) {
		
		inputStream.map(s -> s.trim()).forEach(x -> {
			if (x.contains(":")) {

				if (!x.contains(" "))
					builder.append(x.replace(":", "."));// правильный builder в 1 раз
				else {
					String str = x.replace("   ", "");
					
					list.add(builder.toString() + str);

				}
			}
		});
	
		return list.stream();
	}

}
