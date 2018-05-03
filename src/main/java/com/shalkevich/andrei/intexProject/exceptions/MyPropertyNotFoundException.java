package com.shalkevich.andrei.intexProject.exceptions;

import java.io.IOException;


/**
 * Class for MyPropertyNotFoundException
 * @author Andrei Shalkevich
 *
 */
public class MyPropertyNotFoundException extends IOException{
	
	/**
	 * Message field
	 */
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Constructor - new object creating with message value
	 * @param message - message from exception
	 */
	public MyPropertyNotFoundException(String message){
	
		this.message = message;
	}

}
