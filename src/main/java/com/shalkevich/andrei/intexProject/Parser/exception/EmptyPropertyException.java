package com.shalkevich.andrei.intexProject.Parser.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception to indicate null property value in .properties file
 * 
 * @author Andrei Shalkevich
 */
@Getter
@AllArgsConstructor
public class EmptyPropertyException extends Exception {
  private String message;
}
