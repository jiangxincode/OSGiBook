package org.osgichina.petstore.shoppingcart.actionhandler;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgichina.petstore.productdal.dataobject.Category;
import org.osgichina.petstore.productdal.dataobject.Item;
import org.osgichina.petstore.productdal.dataobject.Product;
import org.osgichina.petstore.shoppingcartdal.dataobject.Cart;
import org.osgichina.petstore.shoppingcartdal.dataobject.CartItem;

/**
 * 显示购物车页面的ActionHandler
 * @author chris
 *
 */
public class ViewCartActionHandler extends BaseActionHandler {	
	
	public String handleRequest(HttpServletRequest request, String servletPath,
			String resourcePath) {
		String userId = request.getSession(true).getId();
		String action = request.getParameter("action");
		if(null != action){
			if(action.equals("delete_item")){
				String itemId = request.getParameter("item_id");
				getCartDao().removeItemFromCart(userId, itemId);
			}
			else if(action.equals("update_item")){
				String itemId = request.getParameter("item_id");
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				getCartDao().updateItemInCart(userId, itemId, quantity);
			}
		}
		
		Cart cart = getCartDao().getCartByUserId(request.getSession(true).getId());
		
		StringBuilder sb = new StringBuilder();
		sb.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"10\">\n");
		sb.append("<tr>\n");
		sb.append("<td class=\"subcategoryName\"><img src=\"");
		sb.append(getImagePath(resourcePath, "cart.png"));
		sb.append("\"/> 我的购物车</td>\n");
		sb.append("</tr>\n");
		sb.append("<tr>\n");
		sb.append("<td>\n");
		sb.append("<table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"10\">\n");
		sb.append("<tr bgcolor=\"#DDDDDD\">\n");
		sb.append("<th width=\"1%\">图片</th>\n");
		sb.append("<th>ID</th>\n");
		sb.append("<th>名称</th>\n");
		sb.append("<th>描述</th>\n");
		sb.append("<th>单价</th>\n");
		sb.append("<th>数量</th>\n");
		sb.append("<th align=\"right\">小计</th>\n");
		sb.append("<th>&nbsp;</th>\n");
		sb.append("</tr>\n");
		List<CartItem> items = cart.getCartItemList();
		if(null == items || items.size() == 0){
			sb.append("<tr>\n");
			sb.append("<td colspan=\"10\">购物车是空的。</td>\n");
			sb.append("</tr>\n");
		}
		else{
			BigDecimal total = new BigDecimal(0);
			//遍历购物车中的所有的项
			for(CartItem cartItem : items){
				Item item = getItemDao().getItemById(cartItem.getItemId());
				Product product = getProductDao().getProductById(item.getProductId());
				Category category = getCategoryDao().getCategoryById(product.getCategoryId());
				
				sb.append("<tr onmouseenter=\"this.style.backgroundColor='powderblue'\" onmouseleave=\"this.style.backgroundColor=''\">\n");
				sb.append("<td width=\"1%\"><img src=\"" +
						getImagePath(resourcePath, product.getLogo()) +
						"\"></td>\n");
				sb.append("<td>" + item.getItemId() + "</td>\n");
				sb.append("<td>" + category.getDescription() + ", " + product.getDescription() + "</td>\n");
				sb.append("<td>" + item.getAttribute1() + "</td>\n");
				sb.append("<td>" + item.getListPrice() + "</td>\n");
				sb.append("<td>\n");
				sb.append("<form action=\"" 
						+ servletPath 
						+ "/shoppingcart"
						+ "\" method=\"POST\">\n");
				sb.append("<input type=\"text\" name=\"quantity\" value=\"" +
						cartItem.getQuantity() +
						"\" size=\"5\" maxlength=\"5\"/>\n");
				sb.append("<input type=\"hidden\" name=\"action\" value=\"update_item\"/>\n");
				sb.append("<input type=\"hidden\" name=\"item_id\" value=\"" + item.getItemId() + "\"/>\n");
				sb.append("<input type=\"submit\" value=\"更新数量\"/>\n");
				sb.append("</form>\n");		
				sb.append("</td>\n");
				sb.append("<td align=\"right\">" + cartItem.getTotal(item.getListPrice()) + "</td>\n");
				total = total.add(cartItem.getTotal(item.getListPrice()));
				sb.append("<td align=\"right\">\n");
				sb.append("<form action=\"" 
						+ servletPath 
						+ "/shoppingcart"
						+ "\" method=\"POST\">\n");
				sb.append("<input type=\"hidden\" name=\"action\" value=\"delete_item\"/>\n");
				sb.append("<input type=\"hidden\" name=\"item_id\" value=\"" + item.getItemId() + "\"/>\n");
				sb.append("<input type=\"submit\" value=\"删除\"/>\n");
				sb.append("</form>\n");
				sb.append("</td>\n");
				sb.append("</tr>\n");
				sb.append("<tr>\n");
			}
			sb.append("<td colspan=\"6\">&nbsp;</td>\n");
			sb.append("<td align=\"right\"><hr size=\"1\"/><br/>总计：" + total + "</td>\n");
			sb.append("<td>&nbsp;</td>\n");
			sb.append("</tr>\n");
			sb.append("<tr bgcolor=\"#DDDDDD\">\n");
			sb.append("<td colspan=\"10\" align=\"right\">\n");
			sb.append("<input type=\"button\" value=\"继续购物\" onclick=\"location='" +
					servletPath + "/productlist" +
					"'\"/>\n");
			sb.append("</td>\n");
			sb.append("</tr>\n");
		}
		sb.append("</table>\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("</table>\n");
		
		return sb.toString();
	}
}
