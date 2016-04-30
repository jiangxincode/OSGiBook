package org.osgichina.demo.timeservice.impl;

import java.util.Date;

import org.osgichina.demo.timeservice.TimeService;


/**
 * TimeServiceµÄÊµÏÖ
 * @author chris
 *
 */
public class TimeServiceImpl implements TimeService{

	public String getCurrentTime() {
		return (new Date()).toString();
	}

}
