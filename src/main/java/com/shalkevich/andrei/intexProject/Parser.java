package com.shalkevich.andrei.intexProject;

import java.util.stream.Stream;

public interface Parser<T> {

	Stream<T> parse(Stream<T> streamForInput);
}
