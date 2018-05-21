package com.shalkevich.andrei.intexProject.Parser.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;

/**
 * Class for parse process providing with saving parsed strings in separate destination file(s)
 * 
 * @author Andrei Shalkevich
 */
@Log4j2
public class SeparateWriteParser extends AbstractParser {
  @Override
  public void Processing(List<String> sourceFiles, String delimiter, String destFileName,
      String destFileExtension, String path) {
    sourceFiles.stream().forEach((pathName) -> {
      propertyBuilder = new StringBuilder();
      String destinationFile = new File(pathName).getParent().concat(File.separator)
          .concat(destFileName).concat(".").concat(destFileExtension);
      try {
        parse(pathName, delimiter);
        listToFileWriting(parsedStrings, destinationFile);
        parsedStrings = new ArrayList<>();
        new File(pathName).delete();
      } catch (IOException e) {
        log.error(e.getClass().getSimpleName() + " while writing parsed data to " + destinationFile
            + "!");
      }
    });
  }
}
