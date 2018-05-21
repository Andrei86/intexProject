package com.shalkevich.andrei.intexProject.Parser.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import lombok.extern.log4j.Log4j2;

@Log4j2
//@TestInstance(Lifecycle.PER_CLASS)
abstract class AbstractTest {
  
  public static final String PATH = "src/test/resources";
  public static final String SOURCE_FILE_EXTENSION = "yml";
  public static final String DESTINATION_FILE_EXTENSION = "properties";
  public static final String DESTINATION_FILE_NAME = "output";
  public static final String DELIMITER = ":";
  public static final String FILE1 = "test1.yml";
  public static final String FILE2 = "test2.yml";
  //public static final Boolean IS_SEPARATE_SAVE_MODE;
  
  /**
   * Method for creating of 2 temporary files for test process
   */
  @BeforeEach
  void createTestFiles() throws IOException {
    List<String> lines = Arrays.asList("developers: ", "developer: ", "firstName:   \"Andrey\"", " lastName:   \"Shalkevich\"");
    Path file = Paths.get(PATH + File.separator + FILE1);
    Files.write(file, lines, Charset.forName("UTF-8"));

    List<String> lines2 = Arrays.asList("developers: ", "developer: ", "firstName:   \"Andrey\"", " lastName:   \"Shalkevich\"");
    Path file2 = Paths.get(PATH + File.separator + FILE2);
    Files.write(file2, lines2, Charset.forName("UTF-8"));
    
    log.info(String.format("Creating 2 files %s and %s"
        + "and input file extention %s", file.toString(), file2.toString(), SOURCE_FILE_EXTENSION));
  }
  
  /**
   * Method for deleting of temporary files after testing process
   */
  @AfterEach
  void deleteCreatedFiles() {
    new File(PATH + File.separator + FILE1).delete();
    new File(PATH + File.separator + FILE2).delete();
    log.info("Deleting files %s and %s", FILE1, FILE2);
  }
}
