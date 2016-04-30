package org.osgichina.petstore.productlist.actionhandler;

import org.osgichina.petstore.bootstrap.actionhandler.ActionHandler;
import org.osgichina.petstore.util.WebResourceMgr;

/**
 * 具体ActionHandler的抽闲基类
 * 实现了getHeadInfo方法，提供了生成图片完整URL的方法，并且提供了对WebResourceMgr对象的管理
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
	 * 根据资源URI前缀以及图片文件名称，生成图片的完整的URL。
	 * @param resourcePath
	 * @param imageFile
	 * @return
	 */
	protected String getImagePath(String resourcePath, String imageFile){
		return resourcePath + "/" + getWebResourceMgr().getRelativeResourcePath() + "/images/" + imageFile;
	}

}
