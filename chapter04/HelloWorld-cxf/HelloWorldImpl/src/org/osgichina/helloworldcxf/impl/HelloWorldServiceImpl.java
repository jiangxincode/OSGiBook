package org.osgichina.helloworldcxf.impl;

import java.util.Date;

import org.osgichina.helloworldcxf.HelloWorldService;

/**
 * HelloWorldService��ʵ��
 * @author chris
 *
 */
public class HelloWorldServiceImpl implements HelloWorldService {
	public String sayHello() {
		System.out.println("Hello World!");
		return (new Date()).toString();
	}
}
