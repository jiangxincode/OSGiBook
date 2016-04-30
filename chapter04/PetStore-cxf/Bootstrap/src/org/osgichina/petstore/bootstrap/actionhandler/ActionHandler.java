package org.osgichina.petstore.bootstrap.actionhandler;

import javax.servlet.http.HttpServletRequest;


/**
 * 处理具体请求的对象
 * @author chris
 *
 */
public interface ActionHandler {
	/**
	 * 处理请求，并产生页面
	 * @param request
	 * @param servletPath
	 * @param resourcePath
	 * @return 页面HTML内容
	 */
	String handleRequest(HttpServletRequest request, String servletPath, String resourcePath);
	
	/**
	 * 生成改页面用的放在返回的HTML的Body前的内容
	 * @param resourcePath 资源的路径前缀
	 * @return 加入在返回的HTML中body前的内容
	 */
	String getHeadInfo(String resourcePath);
}
