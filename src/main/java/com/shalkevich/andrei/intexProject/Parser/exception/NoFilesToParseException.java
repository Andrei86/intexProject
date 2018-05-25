package com.shalkevich.andrei.intexProject.Parser.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception for an error of absence of files to parse
 * 
 * @author Andrei Shalkevich
 */
@Getter
@AllArgsConstructor
public class NoFilesToParseException extends Exception{
  private String message;
}
