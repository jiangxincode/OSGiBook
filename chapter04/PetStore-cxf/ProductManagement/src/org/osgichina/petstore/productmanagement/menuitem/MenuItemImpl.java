package org.osgichina.petstore.productmanagement.menuitem;

import org.osgichina.petstore.bootstrap.menu.MenuItem;
import org.osgichina.petstore.bootstrap.menu.MenuItemInfo;

/**
 * 产品管理的菜单
 * @author chris
 *
 */
public class MenuItemImpl implements MenuItem{

	public MenuItemInfo getMenuItemInfo() {
		MenuItemInfo o = new MenuItemInfo();
		o.setCaption("库存管理");
		o.setPosition(1000);
		o.setUrl("/productmanagement");
		return o;
	}
}
