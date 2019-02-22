package com.tacit.audition.circuitbreaker.m2;

import java.util.concurrent.Callable;

public class RetrierCommand<T> extends AsyncCommand<T> {
	
	private static final String MAX_ATTEPMTS_MSG = "Max Attempts has been reached: %s";
	private int maxRetries;

	public RetrierCommand(int maxRetries, int millis) {
		super(millis);
		this.maxRetries = maxRetries;
	}
	
	public T execute(Callable<T> function) throws Exception {
		try {
			return super.execute(function);
		} catch (Exception exp) {
			return retry(function);
		}
	}

	private T retry(Callable<T> function) throws Exception {
		
		int retryCounter = 0;
		while (retryCounter < maxRetries) {
			try {
				return super.execute(function);
			} catch(Exception exp) {
				retryCounter++;
				System.out.println("FAILED - Method Failed, attempt number:" + retryCounter);
				if (retryCounter >= maxRetries) {
					throw new MaxTriesException(String.format(MAX_ATTEPMTS_MSG, this.maxRetries), exp);
				}
			}
		}
		return null;
	}
		
	

}
