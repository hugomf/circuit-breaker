package com.tacit.audition.circuitbreaker.m2;

public class MaxTriesException  extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public Exception origin;

	public MaxTriesException(String message, Exception origin) {
		super(message);
		this.origin = origin;
	}
	
	public Exception getOrigin() {
		return this.origin;
	}

}
