package com.prgr.main.exception;

public class ProductException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public ProductException(String message) {
		super(message);
		
	}

	@Override
	public String toString() {
		return "ProductException [message=" + message + "]";
	}
	
}
