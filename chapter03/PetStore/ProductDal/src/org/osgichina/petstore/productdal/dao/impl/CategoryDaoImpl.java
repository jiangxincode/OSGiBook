package org.osgichina.petstore.productdal.dao.impl;


import org.osgichina.petstore.productdal.dao.CategoryDao;
import org.osgichina.petstore.productdal.dataobject.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

/**
 * CategoryDao的实现
 * @author chris
 *
 */
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Category getCategoryById(String categoryId) {
        String   sql    = "select * from CATEGORY where CATID = ?";
        Object[] params = new Object[] { categoryId };

        return (Category) jdbcTemplate.queryForObject(sql, params, new CategoryRowMapper());
    }

    @SuppressWarnings("unchecked")
	public List<Category> getCategoryList() {
        String sql = "select * from CATEGORY";

        return jdbcTemplate.query(sql, new CategoryRowMapper());
    }

    /**
     * 将result set映射到category对象。
     */
    private static class CategoryRowMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int index) throws SQLException {
            Category category = new Category();

            category.setCategoryId(rs.getString("CATID"));
            category.setName(rs.getString("NAME"));
            category.setLogo(rs.getString("LOGO"));
            category.setDescription(rs.getString("DESCN"));

            return category;
        }
    }
}
