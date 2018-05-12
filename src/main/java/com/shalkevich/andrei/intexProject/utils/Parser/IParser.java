package com.shalkevich.andrei.intexProject.utils.Parser;

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
   * @param Stream<T> streamForInput - input stream for parsing
   * @return List<T> list of objects after parsing
   * @throws Exception
   */
  List<T> parse(Stream<T> streamForInput) throws Exception;
}
