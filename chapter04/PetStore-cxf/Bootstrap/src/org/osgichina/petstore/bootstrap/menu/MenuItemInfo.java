package org.osgichina.petstore.bootstrap.menu;

/**
 * 菜单项信息
 * @author chris
 *
 */
public class MenuItemInfo {
	/**
	 * 菜单的标题
	 */
	private String caption;
	
	/**
	 * 菜单对应的图片URL
	 */
	private String imgURL;
	
	/**
	 * 菜单的位置信息
	 */
	private int position;
	
	/**
	 *菜单的链接 
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
