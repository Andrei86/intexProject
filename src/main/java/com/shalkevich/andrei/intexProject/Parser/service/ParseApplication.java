package com.shalkevich.andrei.intexProject.Parser.service;

import java.io.IOException;
import java.util.List;
import com.shalkevich.andrei.intexProject.Parser.model.NotDirectoryException;
import com.shalkevich.andrei.intexProject.Parser.model.PropertiesFileNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class for performance parsing operation
 * 
 * @author Andrei Shalkevich
 */
@Getter
@AllArgsConstructor
public class ParseApplication {
  private FileSearcher fileSearcherObject;
  private ParserImplementator parserImplementatorObject;
  private Boolean isSeparateSaveMode;

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
    List<String> allFoundFiles = fileSearcherObject
        .getAllFoundFiles();
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
    if (!isSeparateSaveMode) {
      parserImplementatorObject
          .writeToSingleOutputFileWhileParsing(getFilesForParsing(fileSearcherObject));
    } else {
      parserImplementatorObject
          .writeToSeparateOutputFileWhileParsing(getFilesForParsing(fileSearcherObject));
    }
  }
}
