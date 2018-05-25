package com.shalkevich.andrei.intexProject.Parser.utils;

import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.shalkevich.andrei.intexProject.Parser.config.ParseConfig;
import com.shalkevich.andrei.intexProject.Parser.exception.IncorrectFileFormatException;
import com.shalkevich.andrei.intexProject.Parser.exception.IncorrectModeException;
import com.shalkevich.andrei.intexProject.Parser.exception.NoFilesToParseException;
import com.shalkevich.andrei.intexProject.Parser.service.ParseService;
import com.shalkevich.andrei.intexProject.Parser.exception.EmptyPropertyException;
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
    try {
      ApplicationContext context = new AnnotationConfigApplicationContext(ParseConfig.class);
      ParseService parseService = context.getBean(ParseService.class);
      parseService.validating();
      parseService.writing(parseService.parsing());
      parseService.deleting();
    } catch (IOException | EmptyPropertyException | IncorrectFileFormatException 
        | NoFilesToParseException | IncorrectModeException e) {
      log.error(e.getClass().getSimpleName() + ": " + e.getMessage());
    }
  }
}
