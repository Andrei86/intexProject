package com.shalkevich.andrei.intexProject.Parser.service;

import java.util.List;
import java.util.stream.Stream;

/**
 * Generic interface for parsing
 * 
 * @author Andrei Shalkevich
 *
 * @param <T>
 */
public interface IParser<T> {
  /**
   * Parse method
   * 
   * @param streamForInput - input stream for parsing
   * @return list of objects after parsing
   * @throws Exception
   */
  List<T> parse(Stream<T> streamForInput) throws Exception;
}
