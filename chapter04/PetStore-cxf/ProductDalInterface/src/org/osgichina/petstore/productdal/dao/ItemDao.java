package org.osgichina.petstore.productdal.dao;

import java.util.List;

import org.osgichina.petstore.productdal.dataobject.Item;

/**
 * Item�����ݷ��ʽӿ�
 * @author chris
 *
 */
public interface ItemDao {
    /**
     * ����productId��ȡ������ص�Item��Ϣ
     * @param productId
     * @return Item���б�
     */
    public List<Item> getItemListByProductId(String productId);

    /**
     * ����ItemId��ȡItem��Ϣ
     * @param itemId
     * @return Item����
     */
    public Item getItemById(String itemId);
}
