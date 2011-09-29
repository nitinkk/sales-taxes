package com.thoughtworks.demo.taxes;

/**
 * This exception is thrown when an item is not found in a repository.
 * 
 * @author Dmitriy Efremov
 */
public class NoSuchItemException extends RuntimeException {
	
	private static final long serialVersionUID = 7655432728493696726L;

	/**
	 * Constructor.
	 * @param message the message describing this exception
	 */
	public NoSuchItemException(String message) {
		super(message);
	}

}
