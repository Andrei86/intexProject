package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.File;
import java.util.List;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.NotDirectoryException;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertiesFileNotFoundException;
import lombok.extern.log4j.Log4j2;

/**
 * Class for performance parsing operation
 * 
 * @author Andrei Shalkevich
 */
@Log4j2
public class ParseApplication {

  public static void main(String[] args) {
    try {
      ConfigProperties configPropertiesObject = new ConfigProperties();
      configPropertiesObject.initializePropertiesObject();
      FileSearcher fileSearcher = new FileSearcher(configPropertiesObject);
      fileSearcher.search(new File(configPropertiesObject.getProperties().getProperty("path")));
      List<String> filesForParsing = fileSearcher.getFoundedFiles();
      Boolean isSeparateSaveMode =
          Boolean.valueOf(configPropertiesObject.getProperties().getProperty("isSeparateSaveMode"));
      ParserImplementator parserImplementator = new ParserImplementator(configPropertiesObject);
      if (!isSeparateSaveMode) {
        parserImplementator.writeToSingleOutputFileWhileParsing(filesForParsing);
      } else {
        parserImplementator.writeToSeparateOutputFileWhileParsing(filesForParsing);
      }
    } catch (PropertiesFileNotFoundException ex) {
      log.error(ex.getClass().getSimpleName() + ": " + ex.getMessage());
    } catch (NotDirectoryException ex) {
      log.error(ex.getClass().getSimpleName() + ": " + ex.getMessage());
    }
  }
}
