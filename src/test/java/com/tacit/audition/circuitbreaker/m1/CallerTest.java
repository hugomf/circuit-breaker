package com.tacit.audition.circuitbreaker.m1;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.tacit.audition.circuitbreaker.m1.Caller;
import com.tacit.audition.circuitbreaker.m1.LongRunningService;
import com.tacit.audition.circuitbreaker.m1.TimeOutException;

public class CallerTest {
	
	@Test(expected = TimeOutException.class)
	public void shouldThrowTimeOutExceptionIfTheServiceExecutionTakesLongerThanExpected() throws Exception {
		LongRunningService service = new LongRunningService(100); 
		Caller caller = new Caller(50, service);
		caller.execute();
	}
	
	@Test
	public void shouldNotThrownAnyExcepionIfServiceIsExecutedOnTime() throws Exception {
		LongRunningService service = new LongRunningService(40); 
		Caller caller = new Caller(50, service);
		String result = caller.execute();
		assertThat(result, equalTo("done"));
	}
	

}
