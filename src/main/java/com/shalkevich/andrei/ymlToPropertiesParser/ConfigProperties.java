package com.shalkevich.andrei.intexProject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.shalkevich.andrei.intexProject.exceptions.MyFileNotFoundException;
import com.shalkevich.andrei.intexProject.exceptions.MyPropertyNotFoundException;

/**
 * Class for working with properties configuration files 
 * @author Andrei Shalkevich
 */
public class ConfigProperties {
	
	/**
	 *Field for title properties file on CLASSPATH
	 */
	private String properties_file;
	
	InputStream inputStream;
	
	/**
	 * Properties file field
	 */
	private Properties PROPERTIES;
	
	/** 
	 * Constructor
	 */
	public ConfigProperties(String properties_file){
		
		this.properties_file = properties_file;
		
	}
    
	/**
	 * Initialization method for properties loading 
	 * from app.properties configuration file
	 */
	public void propertiesInit() throws MyFileNotFoundException {

		ClassLoader cl = Thread.currentThread().getContextClassLoader();

		inputStream = cl.getResourceAsStream(properties_file);

		if (inputStream == null)
			throw new MyFileNotFoundException(String.format("There is no such %s file on CLASSPATH.", properties_file));

		try {
			PROPERTIES = new Properties();
			PROPERTIES.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
    
    /**
     * Method for getting a value by key from configuration properties file
     * @param key - key from properties file
     * @return return value from properties file
     */
    public String getSomeProperty(String pathKey) throws MyPropertyNotFoundException{
        String value = PROPERTIES.getProperty(pathKey);
        
        if(value != null)
        	return value;
        else
        	throw new MyPropertyNotFoundException(String.format("There is no %s key in your .properties file. Please put correct key name.", pathKey));
    }

}
