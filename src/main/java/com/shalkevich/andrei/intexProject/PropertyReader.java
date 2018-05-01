package com.shalkevich.andrei.intexProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

public class PropertyReader {
	
	/*public FileInputStream fis;
	Properties property = new Properties();
	
	public String read() throws IOException {
		
		fis = new FileInputStream("src/main/resources/app.properties");
		
		property.load(fis);
		
		return property.getProperty("path");
	}
	
	public static void main(String args[]) {
		
		StringBuilder builder = new StringBuilder();
		
		List<String> strings = new ArrayList<>();
		
		Map<String, String> map = new HashMap<String, String>();

		PropertyReader reader = new PropertyReader();
		try {
			String pathToYml = reader.read(); // read path for yml

			Stream<String> fileStream = Files.lines(Paths.get(pathToYml));

			fileStream.map(s -> s.trim()).forEach(x -> {
				if (x.contains(":")) {

					if (!x.contains(" "))
						builder.append(x.replace(":", "."));// правильный builder в 1 раз
					else {
						String str = x.replace("   ", "");
						
						strings.add(builder.toString() + str);

					}
				}
			});
		
			strings.forEach((x) -> System.out.println(x));
			//fileStream.close();

		} catch (IOException ex) {
			System.out.println(ex.toString());
		}
	}*/

}
