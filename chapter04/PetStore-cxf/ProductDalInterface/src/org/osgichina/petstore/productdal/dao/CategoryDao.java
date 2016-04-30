package org.osgichina.petstore.productdal.dao;


import java.util.List;

import org.osgichina.petstore.productdal.dataobject.Category;

/**
 * Category�����ݷ��ʽӿ�
 * @author chris
 *
 */
public interface CategoryDao {
	
    /**
     * ��ȡ���е�Category����
     * @return Category�б�
     */
    public List<Category> getCategoryList();

    /**
     * ����Category��Id��ȡ��Ӧ��Category��Ϣ
     * @param categoryId
     * @return Category����
     */
    public Category getCategoryById(String categoryId);
}
