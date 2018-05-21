package com.shalkevich.andrei.intexProject.Parser.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import lombok.extern.log4j.Log4j2;

/**
 * Class for parse process providing with saving parsed strings in single destination file
 * 
 * @author Andrei Shalkevich
 */
@Log4j2
public class SingleWriteParser extends AbstractParser {

  @Override
  public void Processing(List<String> sourceFiles, String delimiter, String destFileName,
      String destFileExtension, String path) {
    String destinationFile =
        path.concat(File.separator).concat(destFileName).concat(".").concat(destFileExtension);
    sourceFiles.stream().forEach((pathName) -> {
      propertyBuilder = new StringBuilder();
      try {
        parse(pathName, delimiter);
        listToFileWriting(parsedStrings, destinationFile);
        new File(pathName).delete();
      } catch (IOException e) {
        log.error(e.getClass().getSimpleName() + " while writing parsed data to " + destinationFile
            + "!");
      }
    });
  }
}
