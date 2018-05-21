package com.shalkevich.andrei.intexProject.Parser.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.shalkevich.andrei.intexProject.Parser.model.NotDirectoryException;

/**
 * Class for FileSearcher object's methods testing
 * 
 * @author Andrei Shalkevich
 */
public class SeparateWriteParserTest extends AbstractTest {
  private AbstractParser parser;
  
  @BeforeEach
  void createSeparateWriteParser() {
    parser = new SeparateWriteParser();
  }
  
  /**
   * Method for testing of searchFiles() method of SeparateWriteParser object
   */
  @Test
  void searchFilesTest() throws NotDirectoryException, IOException {
    List<String> foundFiles = parser.searchFiles(PATH, SOURCE_FILE_EXTENSION);
    assertEquals(2, foundFiles.size());
  }
  
  /**
   * Method for testing of parse() method of SeparateWriteParser object
   * @throws IOException 
   */
  /*@Test
  void parseTest() throws IOException {
    List<String> parsedStrings = parser.parse(PATH + File.separator + FILE1, DELIMITER);
    assertEquals(2, parsedStrings.size());
  }*/
  
}
