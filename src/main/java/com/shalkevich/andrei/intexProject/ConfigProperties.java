package com.shalkevich.andrei.intexProject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class for working with properties configuration files 
 * @author Andrei Shalkevich
 */
public class ConfigProperties {
	
	/**Field for title properties file on CLASSPATH*/
	final String PROPERTIES_FILE = "app.propertie";
	
	/**Field for Inputstream*/
	InputStream inputStream;
	
	/**Properties file field*/
	Properties PROPERTIES;
    
	/**Initialization method for properties loading 
	 * from app.properties configuration file*/
    public void propertiesInit(){
 
		try {

			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			inputStream = cl.getResourceAsStream(PROPERTIES_FILE);
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
    public String getSomeProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

}
