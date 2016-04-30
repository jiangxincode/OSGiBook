package org.osgichina.petstore.productlist.actionhandler;

import org.osgichina.petstore.bootstrap.actionhandler.ActionHandler;
import org.osgichina.petstore.util.WebResourceMgr;

/**
 * ����ActionHandler�ĳ��л���
 * ʵ����getHeadInfo�������ṩ������ͼƬ����URL�ķ����������ṩ�˶�WebResourceMgr����Ĺ���
 * @author chris
 *
 */
public abstract class BaseActionHandler implements ActionHandler {
	private WebResourceMgr webResourceMgr; 
	public WebResourceMgr getWebResourceMgr() {
		return webResourceMgr;
	}
	public void setWebResourceMgr(WebResourceMgr webResourceMgr) {
		this.webResourceMgr = webResourceMgr;
	}
	
	/* (non-Javadoc)
	 * @see org.osgichina.petstore.bootstrap.actionhandler.ActionHandler#getHeadInfo(java.lang.String)
	 */
	public String getHeadInfo(String resourcePath) {
		StringBuilder sb = new StringBuilder();
		sb.append("<link href=\"");
		sb.append(resourcePath);
		sb.append("/" + webResourceMgr.getRelativeResourcePath() + "/css/petstore-store.css\" rel=\"stylesheet\" type=\"text/css\">\n");
		return sb.toString();
	}
	
	/**
	 * ������ԴURIǰ׺�Լ�ͼƬ�ļ����ƣ�����ͼƬ��������URL��
	 * @param resourcePath
	 * @param imageFile
	 * @return
	 */
	protected String getImagePath(String resourcePath, String imageFile){
		return resourcePath + "/" + getWebResourceMgr().getRelativeResourcePath() + "/images/" + imageFile;
	}

}
