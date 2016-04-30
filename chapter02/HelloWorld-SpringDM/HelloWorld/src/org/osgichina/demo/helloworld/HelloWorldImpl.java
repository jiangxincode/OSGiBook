package org.osgichina.demo.helloworld;

import org.osgichina.demo.timeservice.TimeService;

/**
 * 一个使用TimeService的Bean
 * @author chris
 *
 */
public class HelloWorldImpl{

	private TimeService timeService;
	
	public TimeService getTimeService() {
		return timeService;
	}

	public void setTimeService(TimeService timeService) {
		this.timeService = timeService;
	}

	public void start(){
		System.out.println("started at " + timeService.getCurrentTime());
	}
	
	public void stop(){
		System.out.println("stopped at " + timeService.getCurrentTime());
	}
}
