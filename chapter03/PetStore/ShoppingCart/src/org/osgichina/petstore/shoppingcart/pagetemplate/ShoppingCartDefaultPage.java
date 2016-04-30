package org.osgichina.petstore.shoppingcart.pagetemplate;

import org.osgichina.petstore.bootstrap.pagetemplate.DefaultPage;

/**
 * 购物车的默认页面
 * @author chris
 *
 */
public class ShoppingCartDefaultPage implements DefaultPage{

	public int getPriority() {
		return 300;
	}

	public String getUri() {
		return "shoppingcart";
	}

}
