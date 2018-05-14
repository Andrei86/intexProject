package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.IOException;
import java.util.List;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.NotDirectoryException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * Class for performance parsing operation
 * 
 * @author Andrei Shalkevich
 */
@Getter
@AllArgsConstructor
public class ParseApplication {
  @NonNull
  private ConfigProperties configPropertiesObject;
  @NonNull
  private FileSearcher fileSearcherObject;
  @NonNull
  private ParserImplementator parserImplementatorObject;
  
  /**
   * Method for getting files for parsing with certain extension
   * 
   * @param fileSearcherObject for performing files searching operations
   * @return filesForParsing - list of files for parsing
   * @throws NotDirectoryException
   * @throws IOException
   */
  public List<String> getFilesForParsing(FileSearcher fileSearcherObject)
      throws NotDirectoryException, IOException {
    List<String> allFoundFiles = fileSearcherObject
        .getAllFoundFiles(configPropertiesObject.getProperties().getProperty("path"));
    List<String> filesForParsing = fileSearcherObject.searchFilesForParsing(allFoundFiles);
    return filesForParsing;

  }

  /**
   * Method for providing parsing process depending on isSeparateSaveMode property
   * 
   * @param parserImplementatorObject for performing parsing process
   * @throws NotDirectoryException
   */
  public void parsingProcess(ParserImplementator parserImplementatorObject)
      throws NotDirectoryException, IOException {
    Boolean isSeparateSaveMode =
        Boolean.valueOf(configPropertiesObject.getProperties().getProperty("isSeparateSaveMode"));
    ParserImplementator parserImplementator = new ParserImplementator(configPropertiesObject);
    if (!isSeparateSaveMode) {
      parserImplementator
          .writeToSingleOutputFileWhileParsing(getFilesForParsing(fileSearcherObject));
    } else {
      parserImplementator
          .writeToSeparateOutputFileWhileParsing(getFilesForParsing(fileSearcherObject));
    }
  }
}
