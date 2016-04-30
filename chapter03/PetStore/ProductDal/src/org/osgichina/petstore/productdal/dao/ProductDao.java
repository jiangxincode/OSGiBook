package org.osgichina.petstore.productdal.dao;

import java.util.List;

import org.osgichina.petstore.productdal.dataobject.Product;

/**
 * Product的数据访问
 * @author chris
 *
 */
public interface ProductDao {
    /**
     * 获取categoryId指定的category下的所有product
     * @param categoryId
     * @return Product的列表
     */
    List<Product> getProductListByCategoryId(String categoryId);

    /**
     * 根据productId获取对应的Product
     * @param productId
     * @return Product
     */
    Product getProductById(String productId);

    /**
     * 插入Product
     * @param product
     */
    void insertProduct(Product product);
}
