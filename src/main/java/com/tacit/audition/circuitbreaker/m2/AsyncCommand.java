package com.tacit.audition.circuitbreaker.m2;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

public class AsyncCommand<T> implements Command<T> {

	private int millis;
	
	public AsyncCommand(int millis) {
		this.millis = millis;
	}

	@Override
	public T execute(Callable<T> function) throws Exception {
		
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
	  	Future<T> future = executor.submit(function);
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




