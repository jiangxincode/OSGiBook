package org.osgichina.petstore.productmanagement.menuitem;

import org.osgichina.petstore.bootstrap.menu.MenuItem;
import org.osgichina.petstore.bootstrap.menu.MenuItemInfo;

/**
 * ��Ʒ����Ĳ˵�
 * @author chris
 *
 */
public class MenuItemImpl implements MenuItem{

	public MenuItemInfo getMenuItemInfo() {
		MenuItemInfo o = new MenuItemInfo();
		o.setCaption("������");
		o.setPosition(1000);
		o.setUrl("/productmanagement");
		return o;
	}
}
