package org.osgichina.petstore.bootstrap.pagetemplate;

/**
 * 页眉接口，用于生成页眉信息
 * @author chris
 *
 */
public interface PageHeader {
	/**
	 * 生成在HTML的head中的内容
	 * @param resourcePath 资源的URI前缀
	 * @return 加入到head中的内容
	 */
	String getHeadInfo(String resourcePath);

	/**
	 * 生成加入到返回的页面的页眉的内容
	 * @param servletPath Web应用的URI的前缀
	 * @param resourcePath 资源的URI前缀
	 * @return 页眉的内容
	 */
	String getPageHeader(String servletPath, String resourcePath);
}
