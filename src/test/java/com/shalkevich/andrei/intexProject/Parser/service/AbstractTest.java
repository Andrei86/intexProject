package com.shalkevich.andrei.intexProject.Parser.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
abstract class AbstractTest {
  protected FileSearcher fileSearcherTest;
  protected List<String> foundFilesListTest = new ArrayList<>();
  
  public static final String PATH = "src/test/resources";
  public static final String INPUT_FILE_EXTENSION = ".yml";
  public static final String OUTPUT_FILE_EXTENSION = ".properties";
  public static final String OUTPUT_FILE_NAME = "output";
  public static final String DELIMITER = ":";
  //public static final Boolean IS_SEPARATE_SAVE_MODE;
  
  /**
   * Method for creating fileSearcherObject and 2 temporary files before testing process
   */
  @BeforeAll
  void createFileSearcherAndTestFiles() throws IOException {
    List<String> lines = Arrays.asList("developers: ", "developer: ", "firstName:   \"Andrey\"", " lastName:   \"Shalkevich\"");
    Path file = Paths.get(PATH + File.separator + "test.txt");
    Files.write(file, lines, Charset.forName("UTF-8"));

    List<String> lines2 = Arrays.asList("developers: ", "developer: ", "firstName:   \"Andrey\"", " lastName:   \"Shalkevich\"");
    Path file2 = Paths.get(PATH + File.separator + "test.yml");
    Files.write(file2, lines2, Charset.forName("UTF-8"));

    foundFilesListTest.add(file.toString());
    foundFilesListTest.add(file2.toString());

    fileSearcherTest = new FileSearcher("./src/test/resources", ".yml");
  }
  
  /**
   * Method for deleting of temporary files after testing process
   */
  @AfterAll
  void deleteCreatedFiles() {
    new File(PATH + File.separator + "test.txt").delete();
    new File(PATH + File.separator + "test.yml").delete();
  }
}
