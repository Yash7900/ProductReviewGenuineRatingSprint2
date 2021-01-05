package com.prgr.main.exception;

public class FeedbackNotFoundException extends Exception {
private static final long serialVersionUID = 1L;
	
	public FeedbackNotFoundException(final String message) {
		super(message);
	}
}
