package com.shalkevich.andrei.intexProject.utils.Parser.exceptions;

public class PermissionDeniedException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String message;

	/**
	 * Constructor
	 * @param message - message for exception
	 */
	public PermissionDeniedException(String message) {
		super();
		this.message = message;
	}
}
