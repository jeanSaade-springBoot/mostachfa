package com.springframework.exceptions;

@SuppressWarnings("serial")
public class SystemException extends Exception {

	public SystemException() {
	}

	public SystemException(String message, Throwable throwable) {
		super(message, throwable);
	}
	public SystemException(String message) {
		super("******** " + message + " ********");
	}
}