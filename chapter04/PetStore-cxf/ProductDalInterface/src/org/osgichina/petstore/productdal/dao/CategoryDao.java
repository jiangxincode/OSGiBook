package org.osgichina.petstore.productdal.dao;


import java.util.List;

import org.osgichina.petstore.productdal.dataobject.Category;

/**
 * Category的数据访问接口
 * @author chris
 *
 */
public interface CategoryDao {
	
    /**
     * 获取所有的Category对象
     * @return Category列表
     */
    public List<Category> getCategoryList();

    /**
     * 根据Category的Id获取对应的Category信息
     * @param categoryId
     * @return Category对象
     */
    public Category getCategoryById(String categoryId);
}
