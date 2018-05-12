package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.IOException;
import java.util.List;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.NotDirectoryException;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertiesFileNotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Class for performance parsing operation
 * 
 * @author Andrei Shalkevich
 */
@Log4j2
@NoArgsConstructor
public class ParseApplication {
  /**
   * Method for getting initialized ConfigProperties object
   * 
   * @return configPropertiesObject - initialized configuration 
   * properties object with properties from app.properties file
   */
  public ConfigProperties getInitializedConfigPropertiesObject()
      throws PropertiesFileNotFoundException {
    ConfigProperties configPropertiesObject = new ConfigProperties();
    configPropertiesObject.initializePropertiesObject();
    return configPropertiesObject;
  }

  /**
   * Method for getting files for parsing with certain extension
   * 
   * @param configPropertiesObject - initialized configuration properties object
   * @return filesForParsing - list with files for parsing with certain extension
   * @throws IOException
   * @throws NotDirectoryException
   */
  public List<String> getFilesForParsing(ConfigProperties configPropertiesObject)
      throws IOException, NotDirectoryException {
    FileSearcher fileSearcher = new FileSearcher(configPropertiesObject);
    List<String> allFoundFiles =
        fileSearcher.getAllFoundFiles(configPropertiesObject.getProperties().getProperty("path"));
    List<String> filesForParsing = fileSearcher.searchFilesForParsing(allFoundFiles);
    return filesForParsing;
  }

  /**
   * Parsing providing method
   */
  public static void main(String[] args) {
    ParseApplication parseApplicationObject = new ParseApplication();
    try {
      ConfigProperties configPropertiesObject =
          parseApplicationObject.getInitializedConfigPropertiesObject();
      List<String> filesForParsing =
          parseApplicationObject.getFilesForParsing(configPropertiesObject);
      Boolean isSeparateSaveMode =
          Boolean.valueOf(configPropertiesObject.getProperties().getProperty("isSeparateSaveMode"));
      ParserImplementator parserImplementator = new ParserImplementator(configPropertiesObject);
      if (!isSeparateSaveMode) {
        parserImplementator.writeToSingleOutputFileWhileParsing(filesForParsing);
      } else {
        parserImplementator.writeToSeparateOutputFileWhileParsing(filesForParsing);
      }
    } catch (PropertiesFileNotFoundException | NotDirectoryException | IOException e) {
      log.error(e.getClass().getSimpleName() + ": " + e.getMessage());
    }
  }
}
