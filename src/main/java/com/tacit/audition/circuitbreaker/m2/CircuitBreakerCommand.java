package com.tacit.audition.circuitbreaker.m2;

import static com.tacit.audition.circuitbreaker.m2.CircuitBreakerCommand.CircuitBrakerStatus.CLOSED;
import static com.tacit.audition.circuitbreaker.m2.CircuitBreakerCommand.CircuitBrakerStatus.OPEN;

import java.util.concurrent.Callable;

public class CircuitBreakerCommand<T> extends RetrierCommand<T> {

	public enum CircuitBrakerStatus {OPEN, CLOSED, HALF_OPEN};
	
	private CircuitBrakerStatus currentStatus = CLOSED;
	
	public CircuitBreakerCommand(int maxRetries, int millis) {
		super(maxRetries, millis);
	}
	
	@Override
	public T execute(Callable<T> function) throws Exception {
		if (currentStatus == CLOSED) {
			return attemptExecute(function);
		}
		
		if (currentStatus == OPEN) {
			throw new Exception();
		}
		return null;
	}
	
	
	public T attemptExecute(Callable<T> function)  throws Exception {
		try {
			return super.execute(function);			
		}
		catch (MaxTriesException max) {
			this.currentStatus = OPEN;
			throw new Exception("Service Unavailable");
		}
	}
	
	

}
