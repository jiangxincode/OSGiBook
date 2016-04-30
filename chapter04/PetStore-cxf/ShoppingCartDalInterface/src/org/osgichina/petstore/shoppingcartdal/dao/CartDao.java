package org.osgichina.petstore.shoppingcartdal.dao;



import org.osgichina.petstore.shoppingcartdal.dataobject.Cart;


/**
 * ���ﳵ���ݷ��ʽӿ�
 * @author chris
 *
 */
public interface CartDao {

    /**
     * �����û�Id���ض�Ӧ�Ĺ��ﳵ
     * @param userId
     * @return ���ﳵ
     */
    Cart getCartByUserId(String userId);
    
    /**
     * ���һ��item���û��Ĺ��ﳵ
     * @param userId
     * @param itemId
     */
    void addItemToCart(String userId, String itemId);
    
    /**
     * ���û��Ĺ��ﳵ��ɾ��һ��item
     * @param userId
     * @param itemId
     */
    void removeItemFromCart(String userId, String itemId);
    
    /**
     * �����û����ﳵ��ĳ��item������
     * @param userId
     * @param itemId
     * @param quantity
     */
    void updateItemInCart(String userId, String itemId, int quantity);
}
