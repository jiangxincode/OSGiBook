package org.osgichina.petstore.bootstrap.pagetemplate;

/**
 * 提供了Uri和priority的信息，priority用于对多个DefaultPage进行排序
 * @author chris
 *
 */
public interface DefaultPage {
	/**
	 * 返回默认的Uri信息
	 * @return
	 */
	String getUri();
	
	/**
	 * 返回一个级别，确定在多个DefaultPage中的顺序
	 * @return
	 */
	int getPriority();
}
