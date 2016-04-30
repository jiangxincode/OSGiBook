package org.osgichina.petstore.bootstrap.actionhandler;

import javax.servlet.http.HttpServletRequest;


/**
 * �����������Ķ���
 * @author chris
 *
 */
public interface ActionHandler {
	/**
	 * �������󣬲�����ҳ��
	 * @param request
	 * @param servletPath
	 * @param resourcePath
	 * @return ҳ��HTML����
	 */
	String handleRequest(HttpServletRequest request, String servletPath, String resourcePath);
	
	/**
	 * ���ɸ�ҳ���õķ��ڷ��ص�HTML��Bodyǰ������
	 * @param resourcePath ��Դ��·��ǰ׺
	 * @return �����ڷ��ص�HTML��bodyǰ������
	 */
	String getHeadInfo(String resourcePath);
}
