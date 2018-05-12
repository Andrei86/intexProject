package com.shalkevich.andrei.intexProject.utils.Parser.exceptions;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Exception for an error of the incorrect directory
 * 
 * @author Andrei Shalkevich
 */
@Getter
@RequiredArgsConstructor
public class NotDirectoryException extends Exception {

  private static final long serialVersionUID = 1L;
  @NonNull
  private String message;
}
