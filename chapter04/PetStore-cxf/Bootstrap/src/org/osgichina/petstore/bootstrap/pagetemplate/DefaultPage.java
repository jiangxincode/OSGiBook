package org.osgichina.petstore.bootstrap.pagetemplate;

/**
 * �ṩ��Uri��priority����Ϣ��priority���ڶԶ��DefaultPage��������
 * @author chris
 *
 */
public interface DefaultPage {
	/**
	 * ����Ĭ�ϵ�Uri��Ϣ
	 * @return
	 */
	String getUri();
	
	/**
	 * ����һ������ȷ���ڶ��DefaultPage�е�˳��
	 * @return
	 */
	int getPriority();
}
