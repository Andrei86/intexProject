package com.shalkevich.andrei.intexProject.utils.Parser.exceptions;

import lombok.Data;

/**
 * @author Andrei Shalkevich
 */
@Data
public class NotDirectoryException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String message;

	/**
	 * Constructor
	 * @param message - message from exception
	 */
	public NotDirectoryException(String message) {
		this.message = message;
	}
}
