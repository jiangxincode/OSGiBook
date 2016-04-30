package org.osgichina.petstore.productdal.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.osgichina.petstore.productdal.dao.ItemDao;
import org.osgichina.petstore.productdal.dataobject.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * ItemDao的实现
 * @author chris
 *
 */
public class ItemDaoImpl implements ItemDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	public Item getItemById(String itemId) {
        String   sql    = "select * from ITEM where ITEMID = ?";
        Object[] params = new Object[] { itemId };

        return (Item) jdbcTemplate.queryForObject(sql, params, new ItemRowMapper());
	}

	@SuppressWarnings("unchecked")
	public List<Item> getItemListByProductId(String productId) {
        String   sql    = "select * from ITEM where PRODUCTID = ?";
        Object[] params = new Object[] { productId };

        return (List<Item>) jdbcTemplate.query(sql, params, new ItemRowMapper());
	}

    /**
     * 将result set映射到product对象。
     */
    private static class ItemRowMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int index) throws SQLException {
            Item item = new Item();
            
            item.setItemId(rs.getString("ITEMID"));
            item.setListPrice(rs.getBigDecimal("LISTPRICE"));
            item.setUnitCost(rs.getBigDecimal("UNITCOST"));
            item.setQuantity(rs.getInt("QUANTITY"));
            item.setProductId(rs.getString("PRODUCTID"));
            item.setStatus(rs.getString("STATUS"));
            item.setAttribute1(rs.getString("ATTR1"));
            item.setAttribute2(rs.getString("ATTR2"));
            item.setAttribute3(rs.getString("ATTR3"));
            item.setAttribute4(rs.getString("ATTR4"));
            item.setAttribute5(rs.getString("ATTR5"));
            return item;
        }
    }

}
