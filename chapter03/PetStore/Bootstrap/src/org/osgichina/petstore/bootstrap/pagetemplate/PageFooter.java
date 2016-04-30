package org.osgichina.petstore.bootstrap.pagetemplate;

/**
 * ҳ�Žӿڣ���������ҳ����Ϣ
 * @author chris
 *
 */
public interface PageFooter {
	/**
	 * ������HTML��head�е�����
	 * @param resourcePath ��Դ��URIǰ׺
	 * @return ���뵽head�е�����
	 */
	String getHeadInfo(String resourcePath);
	
	/**
	 * ���ɼ��뵽���ص�ҳ���ҳ�ŵ�����
	 * @param servletPath WebӦ�õ�URI��ǰ׺
	 * @param resourcePath ��Դ��URIǰ׺
	 * @return ҳ�ŵ�����
	 */
	String getPageFooter(String servletPath, String resourcePath);
}
