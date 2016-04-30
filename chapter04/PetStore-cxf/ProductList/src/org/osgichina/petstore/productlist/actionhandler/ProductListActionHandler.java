package org.osgichina.petstore.productlist.actionhandler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgichina.petstore.productdal.dataobject.Category;
import org.osgichina.petstore.productdal.dataobject.Item;
import org.osgichina.petstore.productdal.dataobject.Product;

/**
 * Pruduct列表的页面ActionHandler
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
		String productId = request.getParameter("product_id");
		String action = request.getParameter("action");
		if(null != action && action.equals("add_item")){
			//add item to cart
			getCartDao().addItemToCart(request.getSession(true).getId(), request.getParameter("item_id"));
		}
		Product product = getProductDao().getProductById(productId);
		Category category = getCategoryDao().getCategoryById(product.getCategoryId());
		//Product基本信息展示
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
			//遍历Product下的所有Item
			for(Item item : items){
				sb.append("<tr onmouseenter=\"this.style.backgroundColor='powderblue'\" onmouseleave=\"this.style.backgroundColor=''\">\n");
				sb.append("<td>" + item.getItemId() + "</td>\n");
				sb.append("<td>" + item.getListPrice() + "</td>\n");
				sb.append("<td>" + item.getAttribute1() + "</td>\n");
				sb.append("<td>" + item.getQuantity() + "</td>\n");
				sb.append("<td align=\"right\">\n");
				sb.append("<form action=\"" 
						+ servletPath 
						+ "/productlist/product_list?product_id="
						+ product.getProductId()
						+ "\" method=\"POST\">\n");
				sb.append("<input type=\"hidden\" name=\"action\" value=\"add_item\"/>\n");
				sb.append("<input type=\"hidden\" name=\"item_id\" value=\"" + item.getItemId() + "\"/>\n");
				sb.append("<input type=\"submit\" value=\"放到购物车\"/>\n");
				sb.append("</form>\n");
				sb.append("</td>\n");
				sb.append("</tr>\n");
			}
		}
		sb.append("<tr bgcolor=\"#DDDDDD\">\n");
		sb.append("<td colspan=\"10\" align=\"right\">\n");
		sb.append("<input type=\"button\" value=\"继 续 购 物\" onclick=\"location='");
		sb.append(servletPath);
		sb.append("/productlist'\"/>\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("</table>\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("</table>\n");
		
		sb.append("</td></tr></tbody></table>\n");
		return sb.toString();
	}
}
