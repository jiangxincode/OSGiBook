package org.osgichina.petstore.productlist.pagetemplate;

import org.osgichina.petstore.bootstrap.pagetemplate.DefaultPage;

/**
 * 产品列表的默认页面设置
 * @author chris
 *
 */
public class ProductListDefaultPage implements DefaultPage{

	public int getPriority() {
		return 0;
	}

	public String getUri() {
		return "productlist";
	}

}
