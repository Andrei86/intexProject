package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.NotDirectoryException;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Class for searching files with the specified extension in specified directory
 * 
 * @author Andrei Shalkevich
 */
@Log4j2
@Getter
@RequiredArgsConstructor
public class FileSearcher {

  @NonNull
  private ConfigProperties configProperties;

  /**
   * Field for save paths to searched files for parsing
   */
  private List<String> foundedFiles = new ArrayList<String>();

  /**
   * Search in directory and in sub directories method for adding files to foundedFiles list for
   * parsing
   * 
   * @param File file - directory for searching
   */
  public void search(File file) throws NotDirectoryException {
    if (file.isDirectory()) {
      Arrays.stream(file.listFiles()).forEach((dir) -> {
        if (dir.isDirectory()) {
          try {
            search(dir);
          } catch (NotDirectoryException ex) {
            log.error(ex.getClass().getSimpleName() + ": " + ex.getMessage());
          }
        } else {
          if (dir.getName().toLowerCase()
              .endsWith(configProperties.getProperties().getProperty("inputFileExtension"))) {
            foundedFiles.add(dir.getAbsoluteFile().toString());
          }
        }
      });
    } else
      throw new NotDirectoryException(
          String.format("%s is not a directory.", file.getAbsolutePath()));
  }
}
