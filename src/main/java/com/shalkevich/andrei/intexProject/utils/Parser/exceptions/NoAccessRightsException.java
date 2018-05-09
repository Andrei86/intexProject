package com.shalkevich.andrei.intexProject.utils.Parser.exceptions;

/**
 * @author Andrei Shalkevich
 */
public class PropertyNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;

	/**
	 * Constructor
	 * @param message - message from exception
	 */
	public PropertyNotFoundException(String message){	
		this.message = message;
	}
}
