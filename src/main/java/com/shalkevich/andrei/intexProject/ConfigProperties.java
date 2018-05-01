package com.shalkevich.andrei.intexProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
	
	protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;
 
    static {
        try {
            fileInputStream = new FileInputStream("src/main/resources/app.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
 
    public static String getSomeProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

}
