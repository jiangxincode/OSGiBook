package org.osgichina.petstore.productmanagement.pagetemplate;

import org.osgichina.petstore.bootstrap.pagetemplate.DefaultPage;

/**
 * ��Ʒ�����Ĭ��ҳ����Ϣ
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
