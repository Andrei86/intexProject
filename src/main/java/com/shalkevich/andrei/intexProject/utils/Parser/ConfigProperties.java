package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertiesFileNotFoundException;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertyNotFoundException;

import lombok.Data;

/**
 * Class for working with properties configuration files 
 * @author Andrei Shalkevich
 */
@Data
public class ConfigProperties {
	
	private final static Logger logger = LogManager.getLogger(ConfigProperties.class);
	
	/**
	 *Field for properties file name on CLASSPATH
	 */
	private String propertiesFile;
	private InputStream inputStream;
	private Properties properties;
	
	public ConfigProperties(String propertiesFile) {

		this.propertiesFile = propertiesFile;
	}
    
	/**
	 * Initialization method for properties loading 
	 * from .properties configuration file
	 */
	public void propertiesInitializer() throws PropertiesFileNotFoundException {

		logger.info("Inside propertiesInitializer() method");

		ClassLoader cl = Thread.currentThread().getContextClassLoader();

		inputStream = cl.getResourceAsStream(propertiesFile);

		if (inputStream == null)
			throw new PropertiesFileNotFoundException(
					String.format("There is no such %s file on CLASSPATH.", propertiesFile));

		try {
			properties = new Properties();
			properties.load(inputStream);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
    
    /**
     * Method for property getting from configuration properties file
     * @param key - key for properties file
     * @return return value from properties file
     */
    public String getCurrentProperty(String propertyName) throws PropertyNotFoundException{
    	
    	logger.info(String.format("Inside getSomeProperty method with key parameter %s", propertyName));
    	
        String propertyValue = properties.getProperty(propertyName);
        
        if(propertyValue != null)
        	return propertyValue;
        else
        	throw new PropertyNotFoundException(String.format("There is no %s property in your .properties file. Please put correct property name.", propertyName));
    }
}
