package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.NotDirectoryException;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertiesFileNotFoundException;
import lombok.Getter;

/**
 * Class for performance parsing operation
 * 
 * @author Andrei Shalkevich
 */
@Getter
public class ParseApplication {
  @Autowired
  private ConfigProperties configPropertiesObject;
  @Autowired
  private FileSearcher fileSearcherObject;
  @Autowired
  private ParserImplementator parserImplementatorObject;

  /**
   * Method for getting files for parsing with certain extension
   * 
   * @param fileSearcherObject for performing files searching operations
   * @return filesForParsing - list of files for parsing
   * @throws NotDirectoryException
   * @throws IOException
   * @throws PropertiesFileNotFoundException
   */
  public List<String> getFilesForParsing(FileSearcher fileSearcherObject)
      throws NotDirectoryException, IOException, PropertiesFileNotFoundException {
    configPropertiesObject.initializePropertiesObject();
    List<String> allFoundFiles = fileSearcherObject
        .getAllFoundFiles(configPropertiesObject.getProperties().getProperty("path"));
    List<String> filesForParsing = fileSearcherObject.searchFilesForParsing(allFoundFiles);
    return filesForParsing;
  }

  /**
   * Method for providing parsing process depending on isSeparateSaveMode property
   * 
   * @param parserImplementatorObject for performing parsing and saving process
   * @throws NotDirectoryException
   * @throws PropertiesFileNotFoundException
   */
  public void parsingProcess(ParserImplementator parserImplementatorObject)
      throws NotDirectoryException, IOException, PropertiesFileNotFoundException {
    Boolean isSeparateSaveMode =
        Boolean.valueOf(configPropertiesObject.getProperties().getProperty("isSeparateSaveMode"));
    if (!isSeparateSaveMode) {
      parserImplementatorObject
          .writeToSingleOutputFileWhileParsing(getFilesForParsing(fileSearcherObject));
    } else {
      parserImplementatorObject
          .writeToSeparateOutputFileWhileParsing(getFilesForParsing(fileSearcherObject));
    }
  }
}
