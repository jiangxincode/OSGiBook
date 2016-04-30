package org.osgichina.petstore.productdal.dao;

import java.util.List;

import org.osgichina.petstore.productdal.dataobject.Product;

/**
 * Product�����ݷ���
 * @author chris
 *
 */
public interface ProductDao {
    /**
     * ��ȡcategoryIdָ����category�µ�����product
     * @param categoryId
     * @return Product���б�
     */
    List<Product> getProductListByCategoryId(String categoryId);

    /**
     * ����productId��ȡ��Ӧ��Product
     * @param productId
     * @return Product
     */
    Product getProductById(String productId);

    /**
     * ����Product
     * @param product
     */
    void insertProduct(Product product);
}
