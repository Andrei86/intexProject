package com.shalkevich.andrei.intexProject.Parser.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.shalkevich.andrei.intexProject.Parser.service.FileSearcher;

public class NotDirectoryExceptionTestClass {
  /**
   * Method for testing of NotDirectoryException throwing
   * 
   * @throws IOException
   * @throws NotDirectoryException
   */
  @Test
  void NotDirectoryExceptionTest() throws NotDirectoryException, IOException {
    FileSearcher fileSearcherTest = new FileSearcher("somePath", ".someExtension");
    assertThrows(NotDirectoryException.class, () -> {
      fileSearcherTest.getAllFoundFiles();
    });
  }
}
