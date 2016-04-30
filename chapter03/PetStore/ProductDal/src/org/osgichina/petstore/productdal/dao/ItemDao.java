package org.osgichina.petstore.productdal.dao;

import java.util.List;

import org.osgichina.petstore.productdal.dataobject.Item;

/**
 * Item的数据访问接口
 * @author chris
 *
 */
public interface ItemDao {
    /**
     * 根据productId获取所有相关的Item信息
     * @param productId
     * @return Item的列表
     */
    public List<Item> getItemListByProductId(String productId);

    /**
     * 根据ItemId获取Item信息
     * @param itemId
     * @return Item对象
     */
    public Item getItemById(String itemId);
}
