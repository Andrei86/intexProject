package com.shalkevich.andrei.intexProject.Parser.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.shalkevich.andrei.intexProject.Parser.exception.IncorrectFileFormatException;
import com.shalkevich.andrei.intexProject.Parser.exception.IncorrectModeException;
import com.shalkevich.andrei.intexProject.Parser.exception.NoFilesToParseException;
import com.shalkevich.andrei.intexProject.Parser.exception.EmptyPropertyException;
import com.shalkevich.andrei.intexProject.Parser.service.fileGuider.FileGuider;
import com.shalkevich.andrei.intexProject.Parser.service.parser.Parser;
import com.shalkevich.andrei.intexProject.Parser.service.validator.Validator;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Class for parsing operation serving
 * 
 * @author Andrei Shalkevich
 */
@Service
@Log4j2
@Getter
public class ParseService {

  @Value("${path}")
  private String path;
  @Value("${sourceFileExtension}")
  private String sourceFileExtension;
  @Value("${destFileName}")
  private String destFileName;
  @Value("${destFileExtension}")
  private String destFileExtension;
  @Value("${delimiter}")
  private String delimiter;
  @Value("${isSeparateSaveMode}")
  private String isSeparateSaveMode;
  private FileGuider fileGuider;
  private Parser parser;
  private Validator validator;

  /**
   * Constructs ParseService object
   * 
   * @param fileGuder to work with files
   * @param parser to file parsing
   * @param validator to validation performance
   */
  @Autowired
  public ParseService(FileGuider fileGuider, Parser parser, Validator validator) {
    super();
    this.fileGuider = fileGuider;
    this.parser = parser;
    this.validator = validator;
  }

  /**
   * Method for validation of app.properties file
   * 
   * @throws FileNotFoundException
   * @throws EmptyPropertyException
   * @throws NotDirectoryException
   * @throws IncorrectFileFormatException
   * @throws IncorrectModeException
   */
  public void validate() throws FileNotFoundException, EmptyPropertyException,
      NotDirectoryException, IncorrectFileFormatException, IncorrectModeException {
    log.info("Inside ParseService's validating() method.");
    validator.propInit();
    validator.emptyValueCheck();
    validator.pathCheck(path);
    validator.extCheck(sourceFileExtension, destFileExtension);
    validator.modeCheck(isSeparateSaveMode);
  }

  /**
   * Method for parsing performance
   * 
   * @return fileStrings - map with destination files as key and list of parsed strings as value
   * @throws IOException
   * @throws NoFilesToParseException
   */
  public Map<String, List<String>> parse() throws IOException, NoFilesToParseException {
    log.info("Inside ParseService's parsing method.");
    Map<String, List<String>> fileStrings;
    if (Boolean.valueOf(isSeparateSaveMode)) {
      fileStrings = separateProcess();
    } else {
      fileStrings = singleProcess();
    }
    return fileStrings;
  }

  /**
   * Method for processing while single save mode installed (saving in single file)
   * 
   * @return fileStrings - map with destination files as key and list of parsed strings as value
   * @throws IOException
   * @throws NoFilesToParseException
   */
  public Map<String, List<String>> separateProcess()
      throws IOException, NoFilesToParseException {
    log.info("Inside ParseService's singleProcessing() method.");
    List<String> parsedStrings;
    Map<String, List<String>> fileStrings = new HashMap<String, List<String>>();
    for (String file : fileGuider.search(path, sourceFileExtension)) {
      parsedStrings =
          parser.getParsedStrings(delimiter, fileGuider.getTrimmedStrings(file, delimiter));
      fileStrings.put(
          fileGuider.destFileForm(new File(file).getParent(), destFileName, destFileExtension),
          parsedStrings);
    }
    return fileStrings;
  }

  /**
   * Method for processing while separate save mode installed (saving in separate file(s))
   * 
   * @return fileStrings - map with destination files as key and list of parsed strings as value
   * @throws IOException
   * @throws NoFilesToParseException
   */
  public Map<String, List<String>> singleProcess() throws IOException, NoFilesToParseException {
    log.info("Inside ParseService's separateProcessing() method.");
    List<String> parsedStrings = new ArrayList<>();
    Map<String, List<String>> fileStrings = new HashMap<String, List<String>>();
    for (String file : fileGuider.search(path, sourceFileExtension)) {
      parsedStrings.addAll(
          parser.getParsedStrings(delimiter, fileGuider.getTrimmedStrings(file, delimiter)));
    }
    fileStrings.put(fileGuider.destFileForm(path, destFileName, destFileExtension),
        parsedStrings);
    return fileStrings;
  }

  /**
   * Method for writing parsed strings list as value to destination file as key
   * 
   * @throws IOException
   */
  public void write(Map<String, List<String>> fileStrings) throws IOException {
    log.info("Inside ParseService's writing() method.");
    for (Map.Entry<String, List<String>> entry : fileStrings.entrySet()) {
      fileGuider.writeStrings(entry.getKey(), entry.getValue());
    }
  }

  /**
   * Method for deleting source files after parsing
   * 
   * @throws IOException
   * @throws NoFilesToParseException
   */
  public void delete() throws IOException, NoFilesToParseException {
    log.info("Inside ParseService's deleting() method.");
    List<String> sourceFiles = fileGuider.search(path, sourceFileExtension);
    sourceFiles.forEach((file) -> new File(file).delete());
  }
}
