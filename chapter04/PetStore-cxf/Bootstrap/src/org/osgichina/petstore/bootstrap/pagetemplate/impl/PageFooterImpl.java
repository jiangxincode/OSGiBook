package org.osgichina.petstore.bootstrap.pagetemplate.impl;

import org.osgichina.petstore.bootstrap.pagetemplate.PageFooter;

public class PageFooterImpl implements PageFooter{

	public String getHeadInfo(String resourcePath) {
		return "";
	}

	public String getPageFooter(String servletPath, String resourcePath) {
		return "OSGi原理与最佳实践";
	}
}
