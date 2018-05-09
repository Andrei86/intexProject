package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertiesFileNotFoundException;
import lombok.Data;

/**
 * Class for working with properties configuration files to retrieve properties key-values
 * @author Andrei Shalkevich
 */
@Data
public class ConfigProperties {
	
	private final static Logger logger = LogManager.getLogger(ConfigProperties.class.getName());

	/**
	 * Field for properties file name on CLASSPATH
	 */
	private final String PROPERTIES_FILE = "app.properties";
	private Properties properties = null;

	public ConfigProperties() throws PropertiesFileNotFoundException {

		logger.info("Inside ConfigProperties class constructor.");

		InputStream inputStream = null;
		try {
			this.properties = new Properties();
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			inputStream = cl.getResourceAsStream(PROPERTIES_FILE);
			if (inputStream == null)
				throw new PropertiesFileNotFoundException(
						String.format("There is no such %s file on CLASSPATH.", PROPERTIES_FILE));

			properties.load(inputStream);
		} catch (IOException e) {
			logger.error(e.getClass().getSimpleName() + " while calling ConfigProperties constructor.");
		}
	}
    
    /**
	 *Method to retreive set of keys from .properties file
	 */
    public Set<Object> getAllKeys(){
    	logger.info("Inside getAllKeys() method of ConfigProperties class.");
        Set<Object> keys = properties.keySet();
        return keys;
    }
    
    /**
	 *Method to retrieve properties as key-value map from .properties file
	 */
    public Map<String, String> getPropertiesKeysValues(Set<Object> keys){
    	
    	logger.info("Inside getPropertiesKeysValues method of ConfigProperties class.");
    	Stream<Object> keyStream = keys.stream();
    	Map<String, String> propertiesKeyValueMap = new HashMap<>();
    	keyStream.forEach((s)->{
    		String propertyKey = String.valueOf(s);
    		String propertyValue = properties.getProperty(propertyKey);
    		propertiesKeyValueMap.put(propertyKey, propertyValue);
    	});
        return propertiesKeyValueMap;
    }
}
