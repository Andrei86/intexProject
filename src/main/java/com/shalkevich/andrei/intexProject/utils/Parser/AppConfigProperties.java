package com.shalkevich.andrei.intexProject.utils.Parser;

import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertiesFileNotFoundException;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertyNotFoundException;

import lombok.Data;

@Data
public class AppConfigProperties extends ConfigProperties{
	
	private final String APP_FILE_PROPERTY_NAME = "path";
	private String pathToInputFile;
	
	public AppConfigProperties(String appPropertiesFileName) {
		super(appPropertiesFileName);		
	}
	
	public void setPathForInputFilesForParsing() throws PropertiesFileNotFoundException, PropertyNotFoundException {
		
		propertiesInitializer();
		pathToInputFile = getCurrentProperty(APP_FILE_PROPERTY_NAME);
	}
}
