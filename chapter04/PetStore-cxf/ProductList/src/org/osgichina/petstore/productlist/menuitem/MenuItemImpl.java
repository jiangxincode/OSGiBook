package org.osgichina.petstore.productlist.menuitem;

import org.osgichina.petstore.bootstrap.menu.MenuItem;
import org.osgichina.petstore.bootstrap.menu.MenuItemInfo;

/**
 * 产品列表的菜单
 * @author chris
 *
 */
public class MenuItemImpl implements MenuItem{

	public MenuItemInfo getMenuItemInfo() {
		MenuItemInfo o = new MenuItemInfo();
		o.setCaption("宠物类目");
		o.setPosition(100);
		o.setUrl("/productlist");
		return o;
	}
}
