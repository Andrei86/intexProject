package com.shalkevich.andrei.intexProject.Parser.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception for an error of incorrect isSeparateMode property
 * 
 * @author Andrei Shalkevich
 */
@Getter
@AllArgsConstructor
public class IncorrectModeException extends Exception {
  private String message;
}
