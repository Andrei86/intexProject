package com.shalkevich.andrei.ymlToPropertiesParser;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shalkevich.andrei.ymlToPropertiesParser.exceptions.MyFileNotFoundException;


/**
 * FileManager class for creating and deleting files
 * @author Andrei Shalkevich
 *
 */
public class FileManager {
	
	final static Logger logger = LogManager.getLogger(FileManager.class);
	
	/**
	 *Field for path key to input .yml file
	 */
	private String pathToInputFile;
	
	/**
	 * Constructor - new object creating with 1 value
	 * @param pathToInputFile - string path key to input .yml file
	 */
	public FileManager(String pathToInputFile){
	this.pathToInputFile = pathToInputFile;
	}
	
	/**
     * Method for creating input file for parsing from parameter
     * @param pathToInputFile - string path key to input .yml file
     */
	public File createInputFile(String pathToInputFile) throws MyFileNotFoundException {
	
	logger.trace(String.format("Inside createInputFile() method with parameter pathToInputFile = %s", pathToInputFile));
		
	File inputFile = new File(pathToInputFile);
	
	if(inputFile.exists())
		return inputFile;
	else
		throw new MyFileNotFoundException("Input .yml file is not found or the path " +
			"to your file is incorrect. Please, check your .properties file.");
	}
	
	/**
     * Method for creating output file from parameter
     * @param outputFileName - string path key to output .yml file name
     */
	public File createOutputFile(String outputFileName) {
		
		logger.trace(String.format("Inside createOutputFile() method with parameter outputFileName = %s", outputFileName));
		
		String[] folder = pathToInputFile.split("/");

		String pathToOutputFile = pathToInputFile.replace(folder[folder.length - 1], outputFileName) + ".properties";

		File outputFile = new File(pathToOutputFile);

		return outputFile;

	}

	/**
     * Method for deleting input file after parsing
     * @param file - file for deleting
     */
	public void deleteFile(File file) {
		
		logger.trace("Inside deleteFile() method");
		
		if (file.exists())
			file.delete();
	}

}
