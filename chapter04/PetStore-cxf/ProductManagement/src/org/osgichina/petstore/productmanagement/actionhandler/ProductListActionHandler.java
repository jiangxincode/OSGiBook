package org.osgichina.petstore.productmanagement.actionhandler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgichina.petstore.productdal.dataobject.Category;
import org.osgichina.petstore.productdal.dataobject.Item;
import org.osgichina.petstore.productdal.dataobject.Product;

/**
 * 产品具体信息页面的ActionHandler
 * @author chris
 *
 */
public class ProductListActionHandler extends BaseActionHandler {
	
	/* (non-Javadoc)
	 * @see org.osgichina.petstore.bootstrap.actionhandler.ActionHandler#handleRequest(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String)
	 */
	public String handleRequest(HttpServletRequest request, String servletPath,
			String resourcePath) {
		StringBuilder sb = new StringBuilder();
        
		Product product = getProductDao().getProductById(request.getParameter("productId"));
		Category category = getCategoryDao().getCategoryById(product.getCategoryId());
		//Product的基础信息
		sb.append("<table cellspacing=\"10\" cellpadding=\"0\" border=\"0\" width=\"100%\" class=\"box\">\n");
		sb.append("<tbody><tr align=\"left\">\n");
		sb.append("<td width=\"100%\">\n");
		
		sb.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"10\">\n");
		sb.append("<tr>\n");
		sb.append("<td rowspan=\"2\" width=\"1%\"><img src=\"");
		sb.append(resourcePath + "/" + getWebResourceMgr().getRelativeResourcePath() + "/images/");
		sb.append(product.getLogo());
		sb.append("\"></td>\n");
		sb.append("<td class=\"categoryName\">");
		sb.append(category.getName());
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("<tr>\n");
		sb.append("<td colspan=\"2\" class=\"subcategoryName\">" + product.getName() + " - " + product.getDescription() + "\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("<tr>\n");
		sb.append("<td colspan=\"2\">\n");
		sb.append("<table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"10\">\n");
		sb.append("<tr bgcolor=\"#DDDDDD\">\n");
		sb.append("<th>ID</th>\n");
		sb.append("<th>单价</th>\n");
		sb.append("<th>描述</th>\n");
		sb.append("<th>库存数量</th>\n");
		sb.append("<th>&nbsp;</th>\n");
		sb.append("</tr>\n");

		List<Item> items = getItemDao().getItemListByProductId(product.getProductId());
		if(null != items){
			//Product下所有的Item的信息
			for(Item item : items){
				sb.append("<tr onmouseenter=\"this.style.backgroundColor='powderblue'\" onmouseleave=\"this.style.backgroundColor=''\">\n");
				sb.append("<td>" + item.getItemId() + "</td>\n");
				sb.append("<td>" + item.getListPrice() + "</td>\n");
				sb.append("<td>" + item.getAttribute1() + "</td>\n");
				sb.append("<td>" + item.getQuantity() + "</td>\n");
				sb.append("<td align=\"right\">[<a onclick=\"alert('未实现')\" href=\"#\">修改</a>]</td>\n");
				sb.append("</tr>\n");
			}
		}
		sb.append("</table>\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("</table>\n");
		
		sb.append("</td></tr></tbody></table>\n");
		return sb.toString();
	}
}
