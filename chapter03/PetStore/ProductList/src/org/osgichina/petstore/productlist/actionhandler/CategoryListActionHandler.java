package org.osgichina.petstore.productlist.actionhandler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgichina.petstore.productdal.dao.CategoryDao;
import org.osgichina.petstore.productdal.dao.ProductDao;
import org.osgichina.petstore.productdal.dataobject.Category;
import org.osgichina.petstore.productdal.dataobject.Product;

/**
 * Category列表页面的ActionHandler
 * @author chris
 *
 */
public class CategoryListActionHandler extends BaseActionHandler {

	private CategoryDao categoryDao;

	private ProductDao productDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public String handleRequest(HttpServletRequest request, String servletPath,
			String resourcePath) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n");
        sb.append("<tbody><tr valign=\"bottom\">\n");
        sb.append("<td align=\"left\" width=\"10\"><table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n");
        sb.append("<tbody><tr>\n");
        sb.append("<td width=\"10\" class=\"selected\"><table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n");
        sb.append("<tbody><tr>\n");
        sb.append("<td height=\"20\" width=\"1\" class=\"tabLeft\"><img width=\"20\" ");
        sb.append("src=\"" + getImagePath(resourcePath, "dot_transparent.png") + "\"/></td>\n");
        sb.append("<td nowrap=\"\" class=\"tabMid\">宠物类目</td>\n");
        sb.append("<td height=\"20\" width=\"1\" class=\"tabRight\"><img width=\"20\" src=\"" + getImagePath(resourcePath, "dot_transparent.png") + "\"/></td>\n");
        sb.append("</tr></tbody>\n");
        sb.append("</table></td>\n");
        sb.append("</tr></tbody>\n");
        sb.append("</table></td>\n");
        sb.append("</tr></tbody></table>\n");
        
		sb.append("<table cellspacing=\"10\" cellpadding=\"0\" border=\"0\" width=\"100%\" class=\"box\">\n");
		sb.append("<tbody><tr align=\"left\">\n");
		sb.append("<td width=\"100%\">\n");

		List<Category> categories = categoryDao.getCategoryList();
		if(categories != null){
			//遍历所有的Category
			for(Category category: categories){
				sb.append("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"10\">\n");
				sb.append("<tr>\n");
		        sb.append("<td><img src=\"" + getImagePath(resourcePath, category.getLogo()));
		        sb.append("\")\"></td>\n");
		        sb.append("<td width=\"100%\">\n");
		        sb.append("<div class=\"categoryName\">" + category.getName() + "</div>\n");
		        sb.append("<div class=\"subcategoryName\">\n");
		        
		        List<Product> productList = productDao.getProductListByCategoryId(category.getCategoryId());
		        if(productList != null){
		        	//遍历每个Category下的所有Product
		        	for(Product product :  productList){
		        		sb.append("<a href=\"");
		        		sb.append(servletPath);
		        		sb.append("/productlist/product_list?product_id=");
		        		sb.append(product.getProductId());
		        		sb.append("\" \"title=\"");
		        		sb.append(product.getDescription());
		        		sb.append("\">");
		        		sb.append(product.getName());
		        		sb.append("</a>\n");
		        	}
		        }
		        sb.append("</div></td>\n");
		        sb.append("</tr>\n");
		        sb.append("</table>\n");
			}
		}
		sb.append("</td></tr></tbody></table>\n");
		return sb.toString();
	}
}
