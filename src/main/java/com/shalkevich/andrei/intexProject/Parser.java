package com.shalkevich.andrei.intexProject;

import java.util.stream.Stream;


/**
 * Generic interface for parser
 * @author Andrei Shalkevich
 *
 * @param <T>
 */
public interface Parser<T> {
	
	/**Parse method
	 * @param streamForInput - stream for input for parsing
	 * @return stream
	 */
	Stream<T> parse(Stream<T> streamForInput);
}
