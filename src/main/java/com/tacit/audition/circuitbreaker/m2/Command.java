package com.tacit.audition.circuitbreaker.m2;

import java.util.concurrent.Callable;

public interface Command<T> {
	
	public T execute(Callable<T> function) throws Exception;

}
