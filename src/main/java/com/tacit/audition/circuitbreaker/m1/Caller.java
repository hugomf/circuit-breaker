package com.tacit.audition.circuitbreaker.m1;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

public class Caller {

	private int millis;
	private LongRunningService service;
	
	public Caller(int millis, LongRunningService service) {
		this.millis = millis;
		this.service = service;
	}

	public String execute() throws TimeOutException {
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
	  	Future<String> future = executor.submit(service);
	  	
	  	try {
			return future.get(this.millis, MILLISECONDS);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			throw new TimeOutException();

		}
	  	return null;
	  	
	}
	

}
