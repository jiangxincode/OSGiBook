package org.osgichina.helloworldcxf.springdm.client;

import org.osgichina.helloworldcxf.springdm.HelloWorldService;

/**
 * 服务消费者，展示如何使用服务
 * @author chris
 *
 */
public class HelloWorldServiceConsumer {
	private HelloWorldService helloWorldService;
	
	/**
	 * 注入服务的方法
	 * @param helloWorldService
	 */
	public void setHelloWorldService(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	/**
	 * 使用服务的方法
	 */
	public void start(){
         System.out.println("call say hello at " + helloWorldService.sayHello());               
	}
}
