package com.shalkevich.andrei.intexProject.utils.Parser;

import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertiesFileNotFoundException;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertyNotFoundException;
import lombok.Data;

@Data
public class ParseConfigProperties extends ConfigProperties{
	
	private final String OUTPUT_FILE_EXTENTION_PROPERTY_NAME = "outputFileExtention";
	private final String INPUT_FILE_EXTENTION_PROPERTY_NAME = "inputFileExtention";
	private final String DELIMITER_PROPERY_NAME = "delimiter";
	private final String IS_SEPARATE_SAVE_MODE = "isSeparateSaveMode";
	private final String DELIMITER_NAME = "delimiter";
	private final String OUTPUT_FILE_NAME = "outputFileName";
	private final String PATH_TO_OUTPUT_FILE = "pathToOutputFile";
	
	private String outputFileExtention;
	private String inputFileExtention;
	private String delimiter;
	private String isSeparateSaveMode;
	private String outputFileName;
	//private String pathToOutputFile;

	public ParseConfigProperties(String parsePropertiesFileName) {
		super(parsePropertiesFileName);
	}
	
	public void setParseOptions() throws PropertiesFileNotFoundException, PropertyNotFoundException {
		
		propertiesInitializer();
		outputFileExtention = getCurrentProperty(OUTPUT_FILE_EXTENTION_PROPERTY_NAME);
		inputFileExtention = getCurrentProperty(INPUT_FILE_EXTENTION_PROPERTY_NAME);
		delimiter = getCurrentProperty(DELIMITER_NAME);
		isSeparateSaveMode = getCurrentProperty(IS_SEPARATE_SAVE_MODE);
		outputFileName = getCurrentProperty(OUTPUT_FILE_NAME);
		//pathToOutputFile = getCurrentProperty(PATH_TO_OUTPUT_FILE);
	}
}
