package com.shalkevich.andrei.intexProject.utils.Parser;

import java.util.List;
import java.util.stream.Stream;


/**
 * Generic interface for parser
 * @author Andrei Shalkevich
 *
 * @param <T>
 */
public interface IParser<T> {
	
	/**Parse method
	 * @param streamForInput - stream for input for parsing
	 * @return stream
	 */
	List<T> parse(Stream<T> streamForInput) throws Exception;
}
