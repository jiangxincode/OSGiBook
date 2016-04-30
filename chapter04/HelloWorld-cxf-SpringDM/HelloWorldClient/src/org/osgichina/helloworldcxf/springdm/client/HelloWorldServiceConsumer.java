package org.osgichina.helloworldcxf.springdm.client;

import org.osgichina.helloworldcxf.springdm.HelloWorldService;

/**
 * ���������ߣ�չʾ���ʹ�÷���
 * @author chris
 *
 */
public class HelloWorldServiceConsumer {
	private HelloWorldService helloWorldService;
	
	/**
	 * ע�����ķ���
	 * @param helloWorldService
	 */
	public void setHelloWorldService(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	/**
	 * ʹ�÷���ķ���
	 */
	public void start(){
         System.out.println("call say hello at " + helloWorldService.sayHello());               
	}
}
