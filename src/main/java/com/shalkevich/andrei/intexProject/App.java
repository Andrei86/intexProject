package com.shalkevich.andrei.intexProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

public class App {

	public static void main(String[] args) throws IOException {
		
		
		final String FILE_OUTPUT_NAME = "output.properties";
		
		String pathToIntputFile = ConfigProperties.getSomeProperty("path");
		// Потом удалить inputFile
		
		String[] folder = pathToIntputFile.split("/");
		
		String pathToOutputFile = pathToIntputFile.replace(folder[folder.length -1], FILE_OUTPUT_NAME);
		
		Stream<String> fileStream = Files.lines(new File(pathToIntputFile).toPath());
		
		StringBuilder myBuilder = new StringBuilder();
		
		List<String> myList = new ArrayList<String>();
		
		Parser<String> myParser = new YmlToPropertiesParser(myBuilder, myList);
		
		Stream<String> outStream = myParser.parse(fileStream);
		
		Files.write(Paths.get(pathToOutputFile), (Iterable<String>)outStream::iterator);
		
		new File(pathToIntputFile).delete();
	}

}
