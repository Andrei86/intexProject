package com.shalkevich.andrei.intexProject.Parser.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.shalkevich.andrei.intexProject.Parser.model.NotDirectoryException;

public class ParserImplementatorTest extends AbstractTest {
  protected ParserImplementator parserImplementatorObject;

  @BeforeAll
  void createParserImplementatorObject() {
    parserImplementatorObject =
        new ParserImplementator(OUTPUT_FILE_EXTENSION, OUTPUT_FILE_NAME, DELIMITER, PATH);
  }

  @Test
  void pathNameToStreamConvertingTest() throws NotDirectoryException, IOException {

    assertTrue(parserImplementatorObject.pathNameToStreamConverting(PATH) instanceof Stream<?>);
  }
}
