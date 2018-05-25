package com.shalkevich.andrei.intexProject.Parser.service.validator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.shalkevich.andrei.intexProject.Parser.exception.IncorrectFileFormatException;
import com.shalkevich.andrei.intexProject.Parser.exception.IncorrectModeException;
import com.shalkevich.andrei.intexProject.Parser.exception.EmptyPropertyException;
import com.shalkevich.andrei.intexProject.Parser.model.Constants;
import com.shalkevich.andrei.intexProject.Parser.model.Format;
import lombok.extern.log4j.Log4j2;

/**
 * Class for app.properties file validation
 * 
 * @author Andrei Shalkevich
 */
@Service
@Log4j2
public class PropValidator {

  private static final String PROPERTIES_FILE_NAME = "app.properties";
  private Properties properties = new Properties();

  /**
   * Properties object initialization method
   * 
   * @throws FileNotFoundException
   */
  public void propInit() throws FileNotFoundException {
    InputStream inputStream =
        PropValidator.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
    if (inputStream == null) {
      throw new FileNotFoundException("There is no such app.properties file on CLASSPATH.");
    }
    try {
      properties.load(inputStream);
    } catch (IOException e) {
      log.error(e.getClass().getSimpleName()
          + " exception while loading properties from app.properties file.");
    }
  }

  /**
   * Validating for null values of properties file
   * 
   * @throws EmptyPropertyException
   */
  public void nullValidation() throws EmptyPropertyException {
    log.info("Inside nullValidation() method.");
    Set<Object> keySet = properties.keySet();
    for (Object key : keySet) {
      String strKey = String.valueOf(key);
      if (properties.getProperty(strKey).isEmpty()) {
        throw new EmptyPropertyException(String.format("Missing value for key %s!", key));
      }
    }
  }

  /**
   * Validating for correct path value of properties file
   * 
   * @param path to search files
   * @throws NotDirectoryException
   */
  public void pathValidation(String path) throws NotDirectoryException {
    log.info("Inside pathValidation() method.");
    if (!Files.exists(Paths.get(path))) {
      throw new NotDirectoryException(String.format("%s is not a directory or not found!", path));
    }
  }

  /**
   * Validating for proper source and destination file extension values of properties file
   * 
   * @param sourceFileExtension - extension of source file
   * @param destFileExtension - extension of destination file
   * @throws IncorrectFileFormatException
   */
  public void extValidation(String sourceFileExtension, String destFileExtension)
      throws IncorrectFileFormatException {
    log.info("Inside extValidation() method.");
    List<String> sEnum =
        Arrays.stream(Format.values()).map(Enum::name).collect(Collectors.toList());
    if (!sEnum.contains(sourceFileExtension) || !sEnum.contains(destFileExtension)) {
      throw new IncorrectFileFormatException(
          "There is no possibility to process such file format!");
    }
  }

  /**
   * Validating for correct value of isSeparateSaveMode property
   * 
   * @param saveMode for parse process
   * @throws IncorrectModeException
   */
  public void modeValidation(String saveMode) throws IncorrectModeException {
    log.info("Inside modeValidation() method.");
    if (!(saveMode.equals(Constants.TRUE) || saveMode.equals(Constants.FALSE))) {
      throw new IncorrectModeException(
          String.format("The value of isSeparateSaveMode property must be %s or %s", Constants.TRUE,
              Constants.FALSE));
    }
  }
}
