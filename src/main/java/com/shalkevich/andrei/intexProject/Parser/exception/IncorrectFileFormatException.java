package com.shalkevich.andrei.intexProject.Parser.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception for an error of the incorrect file format
 * 
 * @author Andrei Shalkevich
 */
@Getter
@AllArgsConstructor
public class IncorrectFileFormatException extends Exception {
  private String message;
}
