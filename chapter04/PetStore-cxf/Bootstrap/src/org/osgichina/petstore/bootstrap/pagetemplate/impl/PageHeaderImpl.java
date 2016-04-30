package org.osgichina.petstore.bootstrap.pagetemplate.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.osgichina.petstore.bootstrap.menu.MenuItem;
import org.osgichina.petstore.bootstrap.menu.MenuItemInfo;
import org.osgichina.petstore.bootstrap.pagetemplate.PageHeader;

public class PageHeaderImpl implements PageHeader {
	private List<MenuItem> menuItems;
	private Map<MenuItem, MenuItemInfo> menuInfoMap = new HashMap<MenuItem, MenuItemInfo>();

	public synchronized void onBind(MenuItem menuItem, Map<?,?> serviceProps){
		List<MenuItem> list = new LinkedList<MenuItem>();
		if(menuItems != null){
			list.addAll(menuItems);
		}
		list.add(menuItem);
		menuInfoMap.put(menuItem, menuItem.getMenuItemInfo());
		Collections.sort(list, new Comparator<MenuItem>(){
			public int compare(MenuItem o1, MenuItem o2) {
				return menuInfoMap.get(o1).getPosition() - menuInfoMap.get(o2).getPosition();
			}
		});
		menuItems = list;
		
	}
	
	public synchronized void onUnbind(MenuItem menuItem, Map<?,?> serviceProps){
		List<MenuItem> list = new LinkedList<MenuItem>();
		if(menuItems != null){
			list.addAll(menuItems);
		}

		menuInfoMap.remove(menuItem);
		list.remove(menuItem);
		menuItems = list;
	}
	
	public String getHeadInfo(String resourcePath) {
		StringBuilder sb = new StringBuilder();
		sb.append("<link href=\"");
		sb.append(resourcePath);
		sb.append("/home/css/petstore-common.css\" rel=\"stylesheet\" type=\"text/css\">\n");
		sb.append("<link href=\"");
		sb.append(resourcePath);
		sb.append("/home/css/petstore-homepage.css\" rel=\"stylesheet\" type=\"text/css\">\n");
		return sb.toString();
	}

	public String getPageHeader(String servletPath, String resourcePath) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n");
		sb.append("<td><img src=\"");
		sb.append(resourcePath);
		sb.append("/home/images/dot_transparent.png\" width=\"30\"/></td>\n");
		sb.append("<td width=\"100%\" valign=\"top\">\n");
		sb.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n");
		sb.append("<tr valign=\"bottom\">\n");
		sb.append("<td width=\"100%\"><a href=\"");
		sb.append(servletPath);
		sb.append("\"><img src=\"");
		sb.append(resourcePath);
		sb.append("/home/images/petstore_title.png\" /></a></td>\n");
		sb.append("<td width=\"1\" align=\"right\">\n");
		sb.append("<table cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n");
		if(menuItems != null && menuItems.size() > 0){
			sb.append("<tr valign=\"bottom\">\n");
			boolean firstItem = true;
			for(MenuItem menuItem : menuItems){
				if( !firstItem ){
					sb.append("<td class=\"shortcut\">\n");
					sb.append("&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;\n");
					sb.append("</td>\n");
				}
				else{
					firstItem = false;
				}
				MenuItemInfo info = menuInfoMap.get(menuItem);
				sb.append("<td class=\"shortcut\">\n");
				String imgURL = info.getImgURL();
				sb.append("<a href=\"");
				sb.append(servletPath);
				sb.append(info.getUrl());
				sb.append("\">\n");
				if(imgURL != null && imgURL.length() > 0){
					sb.append("<img src=\"");
					sb.append(imgURL);
					sb.append("\" />");
				}
				sb.append(info.getCaption());
				sb.append("</a>\n");
				sb.append("</td>\n");
			}
			sb.append("</tr>\n");
		}
		sb.append("</table></td>\n");
		sb.append("</table></td>\n");
		sb.append("</table>\n");
		return sb.toString();
	}
}
