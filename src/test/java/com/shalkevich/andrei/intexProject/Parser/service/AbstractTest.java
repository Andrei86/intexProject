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

/**
 * Abstract test class for creating and deleting test source files
 * 
 * @author Andrei Shalkevich
 */
@Log4j2
public abstract class AbstractTest {

  protected Path testFile1;
  protected Path testFile2;

  protected static final String PATH = "src/test/resources";
  protected static final String TEST_SUBDIRECTORY = "/tezt";
  protected static final String SOURCE_FILE_EXTENSION = "yml";
  protected static final String DESTINATION_FILE_EXTENSION = "properties";
  protected static final String DESTINATION_FILE_NAME = "output";
  protected static final String DELIMITER = ":";
  protected static final String FILE1 = "test1.yml";
  protected static final String FILE2 = "test2.yml";
  protected static final String IS_SEPARATE_SAVE_MODE = "true";
  protected static final String FAKE_VALUE = "fake";

  /**
   * Method for creating of 2 temporary files and test subdirectory for test process
   */
  @BeforeEach
  void createTestFiles() throws IOException {
    List<String> lines = Arrays.asList("developers: ", "developer: ", "firstName:   \"Andrey\"",
        " lastName:   \"Shalkevich\"");
    testFile1 = Paths.get(PATH + File.separator + FILE1);
    Files.write(testFile1, lines, Charset.forName("UTF-8"));

    List<String> lines2 = Arrays.asList("developers: ", "developer: ", "firstName:   \"Andrey\"",
        " lastName:   \"Shalkevich\"");
    testFile2 = Paths.get(PATH + TEST_SUBDIRECTORY + File.separator + FILE2);
    Files.createDirectory(Paths.get(PATH + TEST_SUBDIRECTORY));
    Files.write(testFile2, lines2, Charset.forName("UTF-8"));

    log.info(String.format("Creating 2 test files %s and %s.", testFile1.toString(),
        testFile2.toString()));
  }

  /**
   * Method for deleting of temporary files after test process
   * @throws IOException 
   */
  @AfterEach
  void deleteTestFiles() throws IOException {
    testFile1.toFile().delete();
    testFile2.toFile().delete();
    Files.delete(Paths.get(PATH + TEST_SUBDIRECTORY));
    log.info(String.format("Deleting test directory %s and files %s and %s.", TEST_SUBDIRECTORY, FILE1, FILE2));
  }
}
