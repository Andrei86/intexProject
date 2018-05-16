package com.shalkevich.andrei.intexProject.Parser.service;

import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.shalkevich.andrei.intexProject.Parser.config.ParseConfig;
import com.shalkevich.andrei.intexProject.Parser.model.NotDirectoryException;
import com.shalkevich.andrei.intexProject.Parser.model.PropertiesFileNotFoundException;
import lombok.extern.log4j.Log4j2;

/**
 * Class for parse process running
 * 
 * @author Andrei Shalkevich
 */
@Log4j2
public class ParseRunner {

  /**
   * Method for context creating, beans loading, getting files for parsing and parsing process
   */
  public static void main(String[] args) {
    @SuppressWarnings("resource")
    ApplicationContext context = new AnnotationConfigApplicationContext(ParseConfig.class);
    ParseApplication parseApp = context.getBean(ParseApplication.class);
    try {
      parseApp.getFilesForParsing(parseApp.getFileSearcherObject());
      parseApp.parsingProcess(parseApp.getParserImplementatorObject());
    } catch (NotDirectoryException | PropertiesFileNotFoundException | IOException e) {
      log.error(e.getClass().getSimpleName() + ": " + e.getMessage());
    }
  }
}
