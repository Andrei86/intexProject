package com.shalkevich.andrei.intexProject.exceptions;

import java.io.IOException;


/**
 * Class Exception when file is not found at given path
 * @author Andrei Shalkevich
 */
public class MyFileNotFoundException extends IOException{
	
	/**
	 * Message field
	 */
	private String message;

	/**
	 * Constructor - new object creating with message value
	 * @param message - message from exception
	 */
	public MyFileNotFoundException(String message) {
		super();
		this.message = message;
	}
	
}
