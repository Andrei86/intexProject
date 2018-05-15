package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.shalkevich.andrei.intexProject.configs.ParseAppConfig;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.NotDirectoryException;
import lombok.extern.log4j.Log4j2;

/**
 * Class for parse process running
 * 
 * @author Andrei Shalkevich
 */
@Log4j2
public class ParseRunner {

  /**
   * Method for context creating, beans loading, getting files for parsing and
   * parsing process
   */
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(ParseAppConfig.class);
    ParseApplication parseApp = context.getBean(ParseApplication.class);
    try {
      parseApp.getFilesForParsing(parseApp.getFileSearcherObject());
      parseApp.parsingProcess(parseApp.getParserImplementatorObject());

    } catch (NotDirectoryException | IOException e) {
      log.error(e.getClass().getSimpleName() + ": " + e.getMessage());
    }
  }
}
