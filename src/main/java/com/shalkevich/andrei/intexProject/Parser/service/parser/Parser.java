package com.shalkevich.andrei.intexProject.Parser.service.parser;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.shalkevich.andrei.intexProject.Parser.model.Constants;
import lombok.extern.log4j.Log4j2;

/**
 * Class for parsing performance
 * 
 * @author Andrei Shalkevich
 */
@Service
@Log4j2
public class Parser {
  /**
   * Method for parsed strings getting
   * 
   * @param file - source file
   * @param delimiter of property string
   * @return parsed strings
   */
  public List<String> getParsedStrings(String delimiter, List<String> trimmedStrings) {
    log.info("Inside getParsedStrings() method");
    StringBuilder key = new StringBuilder();
    Boolean isPrevKey = false;
    List<String> parsedStrings = new ArrayList<>();
    for (String str : trimmedStrings)
      if (str.endsWith(delimiter)) {
        if (isPrevKey) {
          isPrevKey = false;
          key.setLength(0);
        }
        key.append(str.replace(delimiter, Constants.DOT));
      } else {
        isPrevKey = true;
        parsedStrings.add(key.toString() + stringForm(str, delimiter));
      }
    return parsedStrings;
  }

  /**
   * Method for forming of last part of key-value pair in parsing string
   * 
   * @param trimmedString - string after trim with last part of key and value separated with
   *        delimiter
   * @param delimiter - source file string delimiter
   * @return string assembled from last part of key and value
   */
  public String stringForm(String trimmedString, String delimiter) {
    log.info(String.format("Inside Parser's stringForming method with %s and %s parameters",
        trimmedString, delimiter));
    String[] keyValuePair = trimmedString.split(delimiter);
    return keyValuePair[0].concat(Constants.COLON).concat(keyValuePair[1].trim());
  }
}
