package com.shalkevich.andrei.intexProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.shalkevich.andrei.intexProject.exceptions.MyFileNotFoundException;
import com.shalkevich.andrei.intexProject.exceptions.MyPropertyNotFoundException;


/**
 * Class app entry point
 * @author Andrei Shalkevich
 */
public class App {

	public static void main(String[] args) throws IOException {
		
		StringBuilder myBuilder = new StringBuilder();
		List<String> myList = new ArrayList<String>();
		YmlToPropertiesParser parser;
		FileManager myFileManager;
		String pathValue = null;
		File inputFile = null;
		File outputFile;
		Stream<String> inputStream = null;
		Stream<String> outputStream;
		
		ConfigProperties configProperties = new ConfigProperties("app.properties");
		
		try{
		configProperties.propertiesInit();
		
		pathValue = configProperties.getSomeProperty("path");
		
		myFileManager = new FileManager(pathValue);
		
		inputFile = myFileManager.createInputFile(pathValue);
		inputStream = Files.lines((inputFile).toPath());
		
		parser = new YmlToPropertiesParser(myBuilder, myList);
		
		outputStream = parser.parse(inputStream);
		
		outputFile = myFileManager.createOutputFile("output");
		
		Files.write(outputFile.toPath(), (Iterable<String>)outputStream::iterator);
		
		myFileManager.deleteFile(inputFile);
		
		System.out.print("---   DONE!   ---");
		
		}catch(MyFileNotFoundException ex){
		
			System.out.println(ex.getMessage());
			
		}catch(MyPropertyNotFoundException ex){
		
			System.out.println(ex.getMessage());
		}

	}

}
