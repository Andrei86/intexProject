package com.shalkevich.andrei.intexProject.Parser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception to indicate that property in app.properties file is null
 * 
 * @author Andrei Shalkevich
 */
@Getter
@AllArgsConstructor
public class PropertyIsNullException extends Exception {
  private String message;
}
