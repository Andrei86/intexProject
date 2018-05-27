package com.shalkevich.andrei.intexProject.Parser.service.fileGuider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.shalkevich.andrei.intexProject.Parser.exception.NoFilesToParseException;
import com.shalkevich.andrei.intexProject.Parser.model.Constants;
import com.shalkevich.andrei.intexProject.Parser.service.AbstractTest;
import lombok.extern.log4j.Log4j2;

/**
 * Class for testing FileGuider's object methods
 * 
 * @author Andrei Shalkevich
 */
@Log4j2
public class FileGuiderTest extends AbstractTest {

  private FileGuider fileGuider = new FileGuider();

  /**
   * Method for testing search method of FileGuider
   */
  @Test
  void searchTest() throws IOException, NoFilesToParseException {
    log.info("Inside searchTest() method.");
    List<String> sourceFiles = fileGuider.search(PATH, SOURCE_FILE_EXTENSION);
    assertEquals(2, sourceFiles.size());
  }

  /**
   * Method for testing search method of FileGuider
   */
  @Test
  void NoFilesToParseExceptionTest() throws IOException, NoFilesToParseException {
    log.info("Inside NoFilesToParseExceptionTest() method.");
    assertThrows(NoFilesToParseException.class, () -> {
      fileGuider.search(PATH, FAKE_VALUE);
    });
  }

  /**
   * Method for testing getTrimmedStrings() method of FileGuider
   */
  @Test
  void getTrimmedStringsTest() throws IOException {
    log.info("Inside getTrimmedStringsTest() method.");
    List<String> trimmedStrings = fileGuider.getTrimmedStrings(testFile1.toString(), DELIMITER);
    assertEquals(4, trimmedStrings.size());
  }

  /**
   * Method for testing of destFileForm() method of FileGuider
   */
  @Test
  void destFileFormTest() throws IOException {
    log.info("Inside destFileFormTest() method.");
    String formedFile =
        fileGuider.destFileForm(PATH, DESTINATION_FILE_NAME, DESTINATION_FILE_EXTENSION);
    assertEquals(
        PATH + File.separator + DESTINATION_FILE_NAME + Constants.DOT + DESTINATION_FILE_EXTENSION,
        formedFile);
  }
  
  /**
   * Method for testing writeStrings() method of FileGuider
   */
  @Test
  void writeStringsTest() throws IOException {
    log.info("Inside writeStringsTest() method.");
    String[] strings = {FAKE_VALUE, FAKE_VALUE};
    List<String> parsedStrings = Arrays.asList(strings);
    String destFile = fileGuider.destFileForm(PATH, DESTINATION_FILE_NAME, DESTINATION_FILE_EXTENSION);
    fileGuider.writeStrings(destFile, parsedStrings);
    File output = new File(destFile);
    long fileLength = output.length();
    output.delete();
    assertEquals(12, fileLength);
  }
}
