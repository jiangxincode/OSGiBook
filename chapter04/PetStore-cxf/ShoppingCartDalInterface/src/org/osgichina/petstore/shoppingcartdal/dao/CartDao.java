package org.osgichina.petstore.shoppingcartdal.dao;



import org.osgichina.petstore.shoppingcartdal.dataobject.Cart;


/**
 * 购物车数据访问接口
 * @author chris
 *
 */
public interface CartDao {

    /**
     * 根据用户Id返回对应的购物车
     * @param userId
     * @return 购物车
     */
    Cart getCartByUserId(String userId);
    
    /**
     * 添加一个item到用户的购物车
     * @param userId
     * @param itemId
     */
    void addItemToCart(String userId, String itemId);
    
    /**
     * 从用户的购物车中删除一个item
     * @param userId
     * @param itemId
     */
    void removeItemFromCart(String userId, String itemId);
    
    /**
     * 更新用户购物车中某个item的数量
     * @param userId
     * @param itemId
     * @param quantity
     */
    void updateItemInCart(String userId, String itemId, int quantity);
}
