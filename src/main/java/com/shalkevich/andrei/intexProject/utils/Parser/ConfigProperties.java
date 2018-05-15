package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertiesFileNotFoundException;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Class for initializing Properties object from .properties configuration file
 * 
 * @author Andrei Shalkevich
 */
@Log4j2
@Getter
public class ConfigProperties {
  private String PROPERTIES_FILE_NAME = "app.properties";
  private Properties properties;

  /**
   * Method for initializing of Properties object with data from app.properties file
   */
  public void initializePropertiesObject() throws PropertiesFileNotFoundException {
    try {
      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      InputStream inputStream = cl.getResourceAsStream(PROPERTIES_FILE_NAME);
      if (inputStream == null) {
        throw new PropertiesFileNotFoundException(
            "There is no such app.properties file on CLASSPATH.");
      }
      this.properties = new Properties();
      properties.load(inputStream);
    } catch (IOException | SecurityException ex) {
      log.error(ex.getClass().getSimpleName()
          + " exception while calling initializePropertiesObject() method of class ConfigProperties.");
    }
  }
}
