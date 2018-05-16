package com.shalkevich.andrei.intexProject.Parser.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.shalkevich.andrei.intexProject.Parser.model.NotDirectoryException;

/**
 * Class for FileSearcher object's methods testing
 * 
 * @author Andrei Shalkevich
 */
public class FileSearcherTest extends AbstractTest {

  /**
   * Method for testing of getAllFoundFiles method of FileSearcher object
   */
  @Test
  void getAllFoundFilesTest() throws NotDirectoryException, IOException {
    List<String> foundFiles = fileSearcherTest.getAllFoundFiles();
    assertEquals(3, foundFiles.size());
  }

  /**
   * Method for testing of searchFilesForParsing method of FileSearcher object
   */
  @Test
  void searchFilesForParsingTest() throws NotDirectoryException, IOException {
    List<String> filesForParsing = fileSearcherTest.searchFilesForParsing(foundFilesListTest);
    assertEquals(1, filesForParsing.size());
  }
}
