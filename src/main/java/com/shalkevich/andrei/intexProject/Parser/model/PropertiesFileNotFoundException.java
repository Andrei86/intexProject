package com.shalkevich.andrei.intexProject.Parser.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Exception to indicate an error of a lack of .properties file
 * 
 * @author Andrei Shalkevich
 */
@Getter
@RequiredArgsConstructor
public class PropertiesFileNotFoundException extends Exception {
  @NonNull
  private String message;

}
