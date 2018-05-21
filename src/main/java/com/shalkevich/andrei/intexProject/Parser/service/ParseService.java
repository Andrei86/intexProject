package com.shalkevich.andrei.intexProject.Parser.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.shalkevich.andrei.intexProject.Parser.model.NotDirectoryException;
import lombok.NonNull;

/**
 * Class for parsing operation serving
 * 
 * @author Andrei Shalkevich
 */
@Service
public class ParseService {

  @Value("${path}")
  private @NonNull String path;
  @Value("${sourceFileExtension}")
  private @NonNull String sourceFileExtension;
  @Value("${destFileName}")
  private @NonNull String destFileName;
  @Value("${destFileExtension}")
  private @NonNull String destFileExtension;
  @Value("${delimiter}")
  private @NonNull String delimiter;
  @Value("${isSeparateSaveMode}")
  private @NonNull String isSeparateSaveMode;

  /**
   * Method for getting of AbstractParser object depending on parsing saving mode
   * 
   * @param parsedStrings - parsed strings list for destination file
   * @param pathName - destination file for writing parsed strings
   * @return AbstractParser object
   * @throws IOException
   */
  public AbstractParser getParser() {
    AbstractParser parser =
        Boolean.valueOf(isSeparateSaveMode) ? new SeparateWriteParser() : new SingleWriteParser();
    return parser;
  }

  /**
   * Method for parsing process providing
   * 
   * @param parser object for providing of parsing process
   * @throws NotDirectoryException
   * @throws IOException
   */
  public void Parsing(AbstractParser parser) throws NotDirectoryException, IOException {
    List<String> sourceFiles = parser.searchFiles(path, sourceFileExtension);
    parser.Processing(sourceFiles, delimiter, destFileName, destFileExtension, path);
  }
}
