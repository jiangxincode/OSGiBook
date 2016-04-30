package org.osgichina.petstore.productmanagement.actionhandler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgichina.petstore.productdal.dataobject.Category;
import org.osgichina.petstore.productdal.dataobject.Product;

/**
 * 产品列表页面的ActionHandler
 * @author chris
 *
 */
public class CategoryListActionHandler extends BaseActionHandler {
	public String handleRequest(HttpServletRequest request, String servletPath,
			String resourcePath) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n");
        sb.append("<tbody><tr valign=\"bottom\">\n");
        sb.append("<td align=\"left\" width=\"10\"><table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n");
        sb.append("<tbody><tr>\n");
        sb.append("</tr></tbody>\n");
        sb.append("</table></td>\n");
        sb.append("</tr></tbody></table>\n");
        
		sb.append("<table cellspacing=\"10\" cellpadding=\"0\" border=\"0\" width=\"100%\" class=\"box\">\n");
		sb.append("<tbody><tr align=\"left\">\n");
		sb.append("<td width=\"100%\">\n");

		List<Category> categories = getCategoryDao().getCategoryList();
		if(categories != null){
			//遍历所有的Category
			for(Category category: categories){
				sb.append("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"10\">\n");
				sb.append("<tr>\n");
		        sb.append("<td><img src=\"" + getImagePath(resourcePath, category.getLogo()));
		        sb.append("\")\"></td>\n");
		        sb.append("<td width=\"100%\">\n");
		        sb.append("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"5\">\n");
		        sb.append("<tr>\n");
		        sb.append("<td colspan=\"2\" class=\"categoryName\">" + category.getName() + "</td>\n");
		        sb.append("</tr>\n");
		        sb.append("<tr>\n");
		        sb.append("<td class=\"subcategoryName\">\n");
		        List<Product> productList = getProductDao().getProductListByCategoryId(category.getCategoryId());
		        if(productList != null){
		        	//遍历Category下的所有Product
		        	for(Product product :  productList){
		        		sb.append("<a href=\"");
		        		sb.append(servletPath);
		        		sb.append("/productmanagement/product_list?productId=");
		        		sb.append(product.getProductId());
		        		sb.append("\" \"title=\"");
		        		sb.append(product.getDescription());
		        		sb.append("\">");
		        		sb.append(product.getName());
		        		sb.append("</a>\n");
		        	}
		        }
		        sb.append("</td>\n");
		        sb.append("<td align=\"right\"><input type=\"button\" onclick=\'alert(\"未实现\");\' value=\"添加产品\"/></td>\n");
		        sb.append("</tr>\n");
		        sb.append("</table>\n");
		        sb.append("</td></tr></table>\n");
			}
		}
		sb.append("</td></tr></tbody></table>\n");
		return sb.toString();
	}
}
