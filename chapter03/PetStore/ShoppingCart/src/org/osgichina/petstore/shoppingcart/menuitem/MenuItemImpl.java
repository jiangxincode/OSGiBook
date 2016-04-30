package org.osgichina.petstore.shoppingcart.menuitem;

import org.osgichina.petstore.bootstrap.menu.MenuItem;
import org.osgichina.petstore.bootstrap.menu.MenuItemInfo;
import org.osgichina.petstore.util.WebConfigMgr;
import org.osgichina.petstore.util.WebResourceMgr;

/**
 * 购物车的菜单
 * @author chris
 *
 */
public class MenuItemImpl implements MenuItem{
	private WebConfigMgr webConfigMgr;
	private WebResourceMgr webResourceMgr; 
	public WebConfigMgr getWebConfigMgr() {
		return webConfigMgr;
	}
	public void setWebConfigMgr(WebConfigMgr webConfigMgr) {
		this.webConfigMgr = webConfigMgr;
	}
	public WebResourceMgr getWebResourceMgr() {
		return webResourceMgr;
	}
	public void setWebResourceMgr(WebResourceMgr webResourceMgr) {
		this.webResourceMgr = webResourceMgr;
	}

	public MenuItemInfo getMenuItemInfo() {
		MenuItemInfo o = new MenuItemInfo();
		o.setCaption("我的购物车");
		o.setPosition(300);
		o.setUrl("/shoppingcart");
		o.setImgURL(webConfigMgr.getResourcePath() + "/" + getWebResourceMgr().getRelativeResourcePath() + "/images/cart.png");
		return o;
	}
}
