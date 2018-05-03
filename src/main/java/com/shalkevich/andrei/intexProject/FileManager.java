package com.shalkevich.andrei.intexProject;

import java.io.File;

import com.shalkevich.andrei.intexProject.exceptions.MyFileNotFoundException;


/**
 * FileManager class for creating and deleting files
 * @author Andrei Shalkevich
 *
 */
public class FileManager {
	
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
		if (file.exists())
			file.delete();
	}

}
