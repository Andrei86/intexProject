package com.shalkevich.andrei.intexProject.Parser.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.shalkevich.andrei.intexProject.Parser.model.NotDirectoryException;

/**
 * Abstract class for extending
 * 
 * @author Andrei Shalkevich
 */
public abstract class AbstractParser implements Parser<String> {
  protected StringBuilder propertyBuilder;
  protected List<String> parsedStrings = new ArrayList<>();
  /**
   * Searches for files by the path and file extension
   * 
   * @param path on which you need to search files for parsing
   * @param sourceFileExtension for source files searching
   * @return list of source files
   * @throws NotDirectoryException
   * @throws IOException
   */
  public List<String> searchFiles(String path, String sourceFileExtension)
      throws NotDirectoryException, IOException {
    try {
      return Files.walk(Paths.get(path)).filter(Files::isRegularFile).map(Path::toString)
          .filter(file -> file.endsWith(sourceFileExtension)).collect(Collectors.toList());
    } catch (NoSuchFileException e) {
      throw new NotDirectoryException(
          "Pathname for searching files is incorrect. Please, check your path property of app.properties file.");
    }
  }

  /**
   * Method for source file parsing string by string: deletes spaces, replaces property key
   * delimiters with dots, replaces property key-value delimiter with colon
   * 
   * @param file - file to process
   * @param delimiter of property string
   * @return list of parsed strings
   * @throws IOException
   */
  @Override
  public List<String> parse(String file, String delimiter) throws IOException {
    Files.lines(Paths.get(file)).map((string) -> string.trim())
        .filter((stringAfterTrim) -> stringAfterTrim.contains(delimiter))
        .forEach((stringAfterTrim) -> {
          if (stringAfterTrim.endsWith(delimiter)) {
            propertyBuilder.append(stringAfterTrim.replace(delimiter, "."));
          } else {
            parsedStrings.add(propertyBuilder.toString()
                + lastPartOfParsedStringForming(stringAfterTrim, delimiter));
          }
        });
    return parsedStrings;
  }

  /**
   * Method for forming of last part of key-value pair in parsing string
   * 
   * @param stringAfterTrim - string after trim with last part of key and value separated with
   *        delimiter
   * @param delimiter - source file string delimiter
   * @return string assembled from last part of key and value property
   */
  public String lastPartOfParsedStringForming(String stringAfterTrim, String delimiter) {
    String[] propertyKeyValuePair = stringAfterTrim.split(delimiter);
    String lastPartOfKeyValuePair =
        propertyKeyValuePair[0].concat(":").concat(propertyKeyValuePair[1].trim());
    return lastPartOfKeyValuePair;
  }

  /**
   * Method for writing list of parsed strings to a destination file
   * 
   * @param parsedStrings - parsed strings list for destination file
   * @param pathName - destination file for writing parsed strings
   * @return file - destination file with parsed strings
   * @throws IOException
   */
  public File listToFileWriting(List<String> parsedStrings, String pathName) throws IOException {
    Path path =
        Files.write(Paths.get(pathName), (Iterable<String>) parsedStrings.stream()::iterator);
    return path.toFile();
  }

  /**
   * Method for parse processing
   * 
   * @param sourceFiles - list of files to parse
   * @param delimiter - source file string delimiter
   * @param destFileName - name of file to writing parsed strings
   * @param destFileExtension - extension of file to writing parsed strings
   * @param path - root for source files
   */
  public abstract void Processing(List<String> sourceFiles, String delimiter, String destFileName,
      String destFileExtension, String path);
}
