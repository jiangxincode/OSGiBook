package org.osgichina.petstore.productlist.menuitem;

import org.osgichina.petstore.bootstrap.menu.MenuItem;
import org.osgichina.petstore.bootstrap.menu.MenuItemInfo;

/**
 * ��Ʒ�б�Ĳ˵�
 * @author chris
 *
 */
public class MenuItemImpl implements MenuItem{

	public MenuItemInfo getMenuItemInfo() {
		MenuItemInfo o = new MenuItemInfo();
		o.setCaption("������Ŀ");
		o.setPosition(100);
		o.setUrl("/productlist");
		return o;
	}
}
