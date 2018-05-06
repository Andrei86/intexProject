package com.shalkevich.andrei.intexProject.utils.Parser.exceptions;

/**
 * @author Andrei Shalkevich
 */
public class PropertiesFileNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String message;

	/**
	 * Constructor
	 * @param message - message for exception
	 */
	public PropertiesFileNotFoundException(String message) {
		super();
		this.message = message;
	}
}
