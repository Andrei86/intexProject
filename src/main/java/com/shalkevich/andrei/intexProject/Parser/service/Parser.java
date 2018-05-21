package com.shalkevich.andrei.intexProject.Parser.service;

import java.util.List;

/**
 * Generic interface for parsing
 * 
 * @author Andrei Shalkevich
 *
 * @param <T>
 * @param pathName - destination file for writing parsed strings
 * @param delimiter - source file string delimiter
 */
public interface Parser<T> {
  /**
   * Parse method
   * 
   * @param streamForInput - input stream for parsing
   * @return list of objects after parsing
   * @throws Exception
   */
  List<T> parse(String pathName, String delimiter) throws Exception;
}
