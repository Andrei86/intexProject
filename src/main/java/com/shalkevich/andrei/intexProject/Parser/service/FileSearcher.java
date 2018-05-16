package com.shalkevich.andrei.intexProject.Parser.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.shalkevich.andrei.intexProject.Parser.model.NotDirectoryException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class for searching files with the specified extension in specified directory
 * 
 * @author Andrei Shalkevich
 */
@Getter
@AllArgsConstructor
public class FileSearcher {
  private String path;
  private String inputFileExtension;

  /**
   * Search in directory and in sub directories method for all existing files
   * 
   * @param directory for searching
   * @throws IOException
   * @throws NotDirectoryException
   */
  public List<String> getAllFoundFiles() throws NotDirectoryException, IOException {
    List<String> allFoundFiles;
    try {
      allFoundFiles = Files.walk(Paths.get(path)).filter(Files::isRegularFile)
          .map(Path::toString).collect(Collectors.toList());
    } catch (NoSuchFileException e) {
      throw new NotDirectoryException(
          "Pathname for searching files is incorrect. Please, check your path property of app.properties file.");
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
    List<String> filesForParsing = new ArrayList<>();
    allFoundFiles.stream().
    forEach((file) -> {
      if (file.endsWith(inputFileExtension)) {
        filesForParsing.add(file);
      }
    });
    return filesForParsing;
  }
}
