package com.shalkevich.andrei.intexProject.Parser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception for an error of the incorrect directory
 * 
 * @author Andrei Shalkevich
 */
@Getter
@AllArgsConstructor
public class NotDirectoryException extends Exception {
  private String message;
}
