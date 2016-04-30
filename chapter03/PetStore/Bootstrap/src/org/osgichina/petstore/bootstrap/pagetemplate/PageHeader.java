package org.osgichina.petstore.bootstrap.pagetemplate;

/**
 * ҳü�ӿڣ���������ҳü��Ϣ
 * @author chris
 *
 */
public interface PageHeader {
	/**
	 * ������HTML��head�е�����
	 * @param resourcePath ��Դ��URIǰ׺
	 * @return ���뵽head�е�����
	 */
	String getHeadInfo(String resourcePath);

	/**
	 * ���ɼ��뵽���ص�ҳ���ҳü������
	 * @param servletPath WebӦ�õ�URI��ǰ׺
	 * @param resourcePath ��Դ��URIǰ׺
	 * @return ҳü������
	 */
	String getPageHeader(String servletPath, String resourcePath);
}
