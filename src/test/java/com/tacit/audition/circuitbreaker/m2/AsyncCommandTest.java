package com.tacit.audition.circuitbreaker.m2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class AsyncCommandTest {
	
	
	@Rule public ExpectedException exception = ExpectedException.none();
	
//	@Test(expected = TimeOutException.class)
//	public void shouldThrowTimeOutExceptionIfTheServiceExecutionTakesLongerThanExpected() throws Exception {
//		LongRunningService service = new LongRunningService(100); 
//		AsyncCommand<String> command = new AsyncCommand<>(50);
//		command.execute(()->service.process());
//	}`
//	
//	@Test
//	public void shouldNotThrownAnyExcepionIfServiceIsExecutedOnTime() throws Exception {
//		LongRunningService service = new LongRunningService(20); 
//		AsyncCommand<String> command = new AsyncCommand<>(50);
//		String result = command.execute(()->service.process());
//		assertThat(result, equalTo("done"));
//	}
	
	
//	@Test
//	public void shouldRetryWhenTimoutExceptionIsTriggered() throws Exception {
//		
//		LongRunningService service = new LongRunningService(50); 
//		RetrierCommand<String> retrier = new RetrierCommand<String>(3, 50);
//		String result = retrier.execute(()->service.process());
//		assertThat( result  , equalTo("done"));
//		
//	}
	
	
	@Test
	public void shouldOpenCircuiteWhenMaxTriesHasBeenReached() throws Exception {
		
		this.exception.expect(Exception.class);
		this.exception.expectMessage("Service Unavailable");
		
		
		LongRunningService service = new LongRunningService(50); 
		CircuitBreakerCommand<String> breaker = new CircuitBreakerCommand<String>(3, 50);
		breaker.execute(()->service.process());
		
	}

}
