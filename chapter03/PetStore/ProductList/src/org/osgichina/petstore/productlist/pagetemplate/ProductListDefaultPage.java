package org.osgichina.petstore.productlist.pagetemplate;

import org.osgichina.petstore.bootstrap.pagetemplate.DefaultPage;

/**
 * ��Ʒ�б��Ĭ��ҳ������
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
