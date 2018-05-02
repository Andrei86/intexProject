package com.shalkevich.andrei.intexProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


/**
 * Class app entry point
 * @author Andrei Shalkevich
 */
public class App {

	public static void main(String[] args) throws FileNotFoundException {

		Stream<String> inputYmlStream = null;

		final String FILE_OUTPUT_NAME = "output.properties";

		ConfigProperties configProperties = new ConfigProperties();

		configProperties.propertiesInit();

		String pathToIntputFile = configProperties.getSomeProperty("path");

		String[] folder = pathToIntputFile.split("/");

		String pathToOutputFile = pathToIntputFile.replace(folder[folder.length - 1],
				FILE_OUTPUT_NAME);

		File fileForInputStream = new File(pathToIntputFile);

		if (!fileForInputStream.exists())
			throw new FileNotFoundException("File .yml not found");
		else {	
		
			try {
				inputYmlStream = Files.lines(fileForInputStream.toPath());
			} catch (IOException ex) {
				System.out.println("Other IOException in searching input.yml");
			}
		}
		StringBuilder myBuilder = new StringBuilder();

		List<String> myList = new ArrayList<String>();

		Parser<String> myParser = new YmlToPropertiesParser(myBuilder, myList);

		Stream<String> outPropStream = myParser.parse(inputYmlStream);

		try {
			Files.write(Paths.get(pathToOutputFile), (Iterable<String>) outPropStream::iterator);
		} catch (IOException ex) {
			System.out.println("Other IOException in creating of output.properties");
		}
		
		new File(pathToIntputFile).delete();
	}

}
