package org.osgichina.petstore.productmanagement.pagetemplate;

import org.osgichina.petstore.bootstrap.pagetemplate.DefaultPage;

/**
 * 产品管理的默认页面信息
 * @author chris
 *
 */
public class ProductManagementDefaultPage implements DefaultPage{

	public int getPriority() {
		return 1000;
	}

	public String getUri() {
		return "productmanagement";
	}

}
