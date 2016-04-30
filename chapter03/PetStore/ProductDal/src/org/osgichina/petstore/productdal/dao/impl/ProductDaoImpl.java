package org.osgichina.petstore.productdal.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.osgichina.petstore.productdal.dao.ProductDao;
import org.osgichina.petstore.productdal.dataobject.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * ProductDao的实现
 * @author chris
 *
 */
public class ProductDaoImpl implements ProductDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	public Product getProductById(String productId) {
        String   sql    = "select * from PRODUCT where PRODUCTID = ?";
        Object[] params = new Object[] { productId };

        return (Product) jdbcTemplate.queryForObject(sql, params, new ProductRowMapper());
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductListByCategoryId(String categoryId) {
        String   sql    = "select * from PRODUCT where CATEGORY = ?";
        Object[] params = new Object[] { categoryId };

        return (List<Product>) jdbcTemplate.query(sql, params, new ProductRowMapper());
	}

	public void insertProduct(Product product) {
        String   sql    = "insert into PRODUCT (PRODUCTID, NAME, LOGO, DESCN, CATEGORY) values (?, ?, ?, ?, ?)";
        Object[] params = new Object[] { product.getProductId(), product.getName(), product.getLogo(), 
        		product.getDescription(), product.getCategoryId()};

        jdbcTemplate.update(sql, params);

	}

	@SuppressWarnings("unchecked")
	public List<Product> searchProductList(String keywords) {
		keywords = keywords.toLowerCase();
        String   sql    = "select * from PRODUCT where lower(name) like '%?%' or lower(category) like '%?%' or lower(descn) like '%?%'";
        Object[] params = new Object[] { keywords, keywords, keywords};

        return (List<Product>) jdbcTemplate.query(sql, params, new ProductRowMapper());
	}
	
    /**
     * 将result set映射到product对象。
     */
    private static class ProductRowMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int index) throws SQLException {
            Product product = new Product();

            product.setProductId(rs.getString("PRODUCTID"));
            product.setName(rs.getString("NAME"));
            product.setCategoryId(rs.getString("CATEGORY"));
            product.setLogo(rs.getString("LOGO"));
            product.setDescription(rs.getString("DESCN"));

            return product;
        }
    }

}
