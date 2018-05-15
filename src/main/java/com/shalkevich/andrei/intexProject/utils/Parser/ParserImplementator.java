package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Class for file parsing and saving parsed strings list to single or separate files depending on
 * parse saving mode
 * 
 * @author Andrei Shalkevich
 */
@Log4j2
@Getter
@RequiredArgsConstructor
public class ParserImplementator implements IParser<String> {
  @Autowired
  @NonNull
  private ConfigProperties configPropertiesObj;
  private StringBuilder propertyBuilder;
  private List<String> parsedStringsList = new ArrayList<>();

  /**
   * Method for input file parsing string by string: deletes spaces, replaces property key
   * delimiters with dots, replaces property key-value delimiter with colon
   * 
   * @param inputStream - strings stream from input file
   * @return list of parsed strings for writing to a file
   */
  @Override
  public List<String> parse(Stream<String> inputStream) {
    String delimiter = configPropertiesObj.getProperties().getProperty("delimiter");
    inputStream.map((string) -> string.trim()).forEach((stringAfterTrim) -> {
      if (stringAfterTrim.contains(delimiter)) {
        if (stringAfterTrim.endsWith(delimiter)) {
          propertyBuilder.append(stringAfterTrim.replace(delimiter, "."));
        } else {
          parsedStringsList.add(propertyBuilder.toString()
              + lastPartOfKeyValuePairForming(stringAfterTrim, delimiter));
        }
      }
    });
    return parsedStringsList;
  }

  /**
   * Method for forming of last part of key-value pair in parsing string
   * 
   * @param stringAfterTrim - string after trim with last part of key
   * and value separated with delimiter
   * @param delimiter - file for parsing string delimiter
   * @return string assembled from last part of key and value property
   */
  public String lastPartOfKeyValuePairForming(String stringAfterTrim, String delimiter) {
    String[] propertyKeyValuePair = stringAfterTrim.split(delimiter);
    String lastPartOfKeyValuePair =
        propertyKeyValuePair[0].concat(":").concat(propertyKeyValuePair[1].trim());
    return lastPartOfKeyValuePair;
  }

  /**
   * Method for converting pathname to Stream<String> object
   * 
   * @param pathname for searching files for parsing
   */
  public Stream<String> pathNameToStreamConverting(String pathname) throws IOException {
    return Files.lines(Paths.get(pathname));
  }

  /**
   * Method for writing list of parsed strings to a certain file
   * 
   * @param parsedStringsList
   * @param pathName of file for writing parsed strings
   * @throws IOException
   */
  public File listToFileWriting(List<String> parsedStringsList, String pathName)
      throws IOException {
    Path path =
        Files.write(Paths.get(pathName), (Iterable<String>) parsedStringsList.stream()::iterator);
    return path.toFile();
  }

  /**
   * Method for getting name and extension of output file
   */
  public String getOutFileNameAndExtention() {
    return configPropertiesObj.getProperties().getProperty("outputFileName")
        + configPropertiesObj.getProperties().getProperty("outputFileExtension");
  }

  /**
   * Method for getting path name for input file(s)
   */
  public String getInputFilePathName() {
    return configPropertiesObj.getProperties().getProperty("path");
  }

  /**
   * Method for writing list of parsed strings to single output file
   * 
   * @param inputFilesList - pathnames list of input files for parsing
   */
  public void writeToSingleOutputFileWhileParsing(List<String> inputFilesList) {
    String singleOutFileName =
        getInputFilePathName().concat(File.separator).concat(getOutFileNameAndExtention());
    inputFilesList.stream().forEach((pathName) -> {
      propertyBuilder = new StringBuilder();
      try {
        Stream<String> streamForFileParsing = pathNameToStreamConverting(pathName);
        parse(streamForFileParsing);
        listToFileWriting(parsedStringsList, singleOutFileName);
        new File(pathName).delete();
      } catch (IOException e) {
        log.error(e.getClass().getSimpleName() + " while writing parsed data to "
            + singleOutFileName + "!");
      }
    });
  }

  /**
   * Method for writing list of parsed strings to separate output files
   * 
   * @param inputFilesList - pathnames list of input files for parsing
   */
  public void writeToSeparateOutputFileWhileParsing(List<String> inputFilesList) {
    inputFilesList.stream().forEach((pathName) -> {
      propertyBuilder = new StringBuilder();
      String separateOutFileName = new File(pathName).getParent().concat(File.separator)
          .concat(getOutFileNameAndExtention());
      try {
        Stream<String> streamBeforeFileParsing = pathNameToStreamConverting(pathName);
        parse(streamBeforeFileParsing);
        listToFileWriting(parsedStringsList, separateOutFileName);
        parsedStringsList = new ArrayList<>();
        new File(pathName).delete();
      } catch (IOException e) {
        log.error(e.getClass().getSimpleName() + " while writing parsed data to "
            + separateOutFileName + "!");
      }
    });
  }
}
