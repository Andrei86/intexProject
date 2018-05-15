package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.NotDirectoryException;
import lombok.Getter;

/**
 * Class for searching files with the specified extension in specified directory
 * 
 * @author Andrei Shalkevich
 */
@Getter
public class FileSearcher {
  @Autowired
  private ConfigProperties configPropertiesObject;

  /**
   * Search in directory and in sub directories method for all existing files
   * 
   * @param directory for searching
   * @throws IOException
   * @throws NotDirectoryException
   */
  public List<String> getAllFoundFiles(String directory) throws NotDirectoryException, IOException {
    List<String> allFoundFiles;
        try {
          allFoundFiles = Files.walk(Paths.get(directory)).filter(Files::isRegularFile).map(Path::toString)
          .collect(Collectors.toList());
        } catch (NoSuchFileException e) {
          throw new NotDirectoryException("Pathname for searching files is incorrect. Please, check your path property of app.properties file.");
        }
    return allFoundFiles;
  }

  /**
   * Method for getting files for parsing depending on the extension from all founded files list
   * 
   * @param allFoundFiles - list of all found files in searching directory
   * @return filesForParsing - files with specified in app.properties file extension for parsing
   */
  public List<String> searchFilesForParsing(List<String> allFoundFiles) {
    String inputFileExtension = configPropertiesObject.getProperties().getProperty("inputFileExtension");
    List<String> filesForParsing = new ArrayList<>();
    allFoundFiles.stream().forEach((file) -> {
      if (file.endsWith(inputFileExtension)) {
        filesForParsing.add(file);
      }
    });
    return filesForParsing;
  }
}
