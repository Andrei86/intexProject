package com.shalkevich.andrei.intexProject.Parser.service.fileGuider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import com.shalkevich.andrei.intexProject.Parser.exception.NoFilesToParseException;
import com.shalkevich.andrei.intexProject.Parser.model.Constants;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Abstract class for file search, recording and creation
 * 
 * @author Andrei Shalkevich
 */
@Repository
@Log4j2
@NoArgsConstructor
public class FileGuider {
  /**
   * Searches for files by the path and file extension
   * 
   * @param path on which you need to search files for parsing
   * @param sourceFileExtension for source files searching
   * @return list of source files
   * @throws IOException
   * @throws NoFilesToParseException
   */
  public List<String> search(String path, String sourceFileExtension)
      throws IOException, NoFilesToParseException {
    log.info("Inside search method");
    List<String> sourceFiles =
        Files.walk(Paths.get(path)).filter(Files::isRegularFile).map(Path::toString)
            .filter(file -> file.endsWith(sourceFileExtension)).collect(Collectors.toList());
    if (sourceFiles.isEmpty()) {
      throw new NoFilesToParseException("There is no files to parse.");
    }
    return sourceFiles;
  }

  /**
   * Method for trimmed strings getting
   * 
   * @param file - source file
   * @param delimiter of property string
   * @return list of useful strings for parsing
   * @throws IOException
   */
  public List<String> getTrimmedStrings(String file, String delimiter) throws IOException {
    log.info("Inside getTrimmedStrings() method");
    return Files.lines(Paths.get(file)).map((string) -> string.trim())
        .filter((trimmedString) -> trimmedString.contains(delimiter)).collect(Collectors.toList());
  }

  /**
   * Method for destination file forming
   * 
   * @param path - root for source files
   * @param destFileName - name of file to write parsed strings
   * @param destFileExtension - extension of file to write parsed strings
   */
  public String destFileForm(String path, String destFileName, String destFileExtension) {
    log.info("Inside destFileForming method.");
    return path.concat(File.separator).concat(destFileName)
        .concat(Constants.DOT).concat(destFileExtension);
  }

  /**
   * Method for writing list of parsed strings to a destination file
   * 
   * @param parsedStrings - parsed strings list
   * @param destFile - destination file for writing parsed strings
   * @return destFile - destination file for parsed strings
   * @throws IOException
   */
  public File writeStrings(String destFile, List<String> parsedStrings) throws IOException {
    log.info("Inside FileGuider's writeStrings method.");
    return Files.write(Paths.get(destFile), (Iterable<String>) parsedStrings.stream()::iterator)
        .toFile();
  }

  /**
   * Method for deleting of source files after parsing
   * 
   * @param files - source file to deleting
   */
  public Boolean deleteFile(String file) {
    log.info("Inside FileGuider's delete method.");
    return new File(file).delete();
  }
}
