package org.osgichina.petstore.productmanagement.actionhandler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgichina.petstore.productdal.dao.CategoryDao;
import org.osgichina.petstore.productdal.dao.ProductDao;
import org.osgichina.petstore.productdal.dataobject.Category;
import org.osgichina.petstore.productdal.dataobject.Product;

/**
 * ��Ʒ�б�ҳ���ActionHandler
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
	
	/* (non-Javadoc)
	 * @see org.osgichina.petstore.bootstrap.actionhandler.ActionHandler#handleRequest(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String)
	 */
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

		List<Category> categories = categoryDao.getCategoryList();
		if(categories != null){
			//�������е�Category
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
		        List<Product> productList = productDao.getProductListByCategoryId(category.getCategoryId());
		        if(productList != null){
		        	//����Category�µ�����Product
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
		        sb.append("<td align=\"right\"><input type=\"button\" onclick=\'alert(\"δʵ��\");\' value=\"��Ӳ�Ʒ\"/></td>\n");
		        sb.append("</tr>\n");
		        sb.append("</table>\n");
		        sb.append("</td></tr></table>\n");
			}
		}
		sb.append("</td></tr></tbody></table>\n");
		return sb.toString();
	}
}
