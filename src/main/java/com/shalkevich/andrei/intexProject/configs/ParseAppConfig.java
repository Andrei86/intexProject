package com.shalkevich.andrei.intexProject.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.shalkevich.andrei.intexProject.utils.Parser.ConfigProperties;
import com.shalkevich.andrei.intexProject.utils.Parser.FileSearcher;
import com.shalkevich.andrei.intexProject.utils.Parser.ParseApplication;
import com.shalkevich.andrei.intexProject.utils.Parser.ParserImplementator;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertiesFileNotFoundException;
import lombok.extern.log4j.Log4j2;

/**
 * Class for parse application configuration
 * 
 * @author Andrei Shalkevich
 */
@Log4j2
@Configuration
public class ParseAppConfig {
  
  /**
   * ParseApplication Bean instantiating method
   * 
   * @return parseApplicationObject for parsing process providing
   */
  @Bean
  public ParseApplication getParseApplicationObject() {
    ParseApplication parseApplicationObject = null;
    try {
      parseApplicationObject = new ParseApplication(getInitializedConfigPropertiesObject(),
          getFileSearcherObject(getInitializedConfigPropertiesObject()),
          getParserImplementatorObject(getInitializedConfigPropertiesObject()));
    } catch (PropertiesFileNotFoundException e) {
      log.error(e.getClass().getSimpleName() + ": " + e.getMessage());
    }
    return parseApplicationObject;
  }
  
  /**
   * ConfigProperties Bean instantiating method
   * 
   * @return configPropertiesObject for loading properties from app.properties file
   */
  @Bean
  public ConfigProperties getInitializedConfigPropertiesObject()
      throws PropertiesFileNotFoundException {
    ConfigProperties configPropertiesObject = new ConfigProperties();
    configPropertiesObject.initializePropertiesObject();
    return configPropertiesObject;
  }

  /**
   * FileSearcher Bean instantiating method
   * 
   * @return fileSearcherObject for searching files for parsing
   */
  @Bean
  public FileSearcher getFileSearcherObject(ConfigProperties configPropertiesObject) {
    return new FileSearcher(configPropertiesObject);
  }

  /**
   * ParserImplementator Bean instantiating method
   * 
   * @return parserImplementatorObject for parsing and writing parsed strings to file
   */
  @Bean
  public ParserImplementator getParserImplementatorObject(ConfigProperties configPropertiesObject) {
    ParserImplementator parserImplementatorObject = new ParserImplementator(configPropertiesObject);
    return parserImplementatorObject;
  }
}
