package com.tacit.audition.circuitbreaker.m1;

import java.util.concurrent.Callable;

public class LongRunningService implements Callable<String> {
	
	private int millis;
	
	public LongRunningService(int millis) {
		this.millis = millis;
	}

	public String call() {
		wait(this.millis);
		return "done";
	}
	
	private void wait(int millis) {
		
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
//	private Integer getRandom(Integer min, Integer max) {
//		Random rand = new Random();
//		return rand.nextInt((max - min) + 1) + min;
//	}


}
