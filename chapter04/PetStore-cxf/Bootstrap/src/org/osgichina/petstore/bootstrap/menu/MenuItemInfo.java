package org.osgichina.petstore.bootstrap.menu;

/**
 * �˵�����Ϣ
 * @author chris
 *
 */
public class MenuItemInfo {
	/**
	 * �˵��ı���
	 */
	private String caption;
	
	/**
	 * �˵���Ӧ��ͼƬURL
	 */
	private String imgURL;
	
	/**
	 * �˵���λ����Ϣ
	 */
	private int position;
	
	/**
	 *�˵������� 
	 */
	private String url;
	
	public String getCaption() {
		return caption;
	}
	public String getImgURL() {
		return imgURL;
	}
	public int getPosition() {
		return position;
	}
	public String getUrl() {
		return url;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
