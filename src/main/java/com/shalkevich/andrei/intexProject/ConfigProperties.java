package com.shalkevich.andrei.intexProject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {
	
	final String PATH_TO_PROPERTIES = "app.properties";
	InputStream inputStream;
	Properties PROPERTIES;
    
    public void propertiesInit() {
 
        try {
            
            ClassLoader cl = Thread.currentThread().getContextClassLoader();

            inputStream = cl.getResourceAsStream(PATH_TO_PROPERTIES);
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
 
    public String getSomeProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

}
