package com.tacit.audition.circuitbreaker.m2;

import java.util.Random;

public class LongRunningService {
	
	private int millis;
	
	public LongRunningService(int millis) {
		this.millis = millis;
	}

	public String process() {
		Integer waitTime = getRandom(this.millis-3, this.millis + 3);
		//Integer waitTime = this.millis;
		System.out.println("test1:" + waitTime);
		wait(waitTime);
		return "done";
	}
	
	private void wait(int millis) {
		
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Integer getRandom(Integer min, Integer max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}


}
