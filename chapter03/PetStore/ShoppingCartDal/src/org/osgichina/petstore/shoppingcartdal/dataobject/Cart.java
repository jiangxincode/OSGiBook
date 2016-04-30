package org.osgichina.petstore.shoppingcartdal.dataobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * shopping cart对象。
 *
 */
public class Cart implements Serializable {
	private static final long serialVersionUID = -3091661442775578603L;
	
	private ConcurrentHashMap<String/*itemId*/, CartItem>  cartItems = new ConcurrentHashMap<String, CartItem>();
    
	private transient volatile List<CartItem> items;
	
	/**
	 * 设置购物车数据
	 * @param cartItems
	 */
	public void setCartItems(Map<String, CartItem> cartItems) {
    	if(null == cartItems || cartItems.size() == 0){
    		this.cartItems.clear();
    		return;
    	}
    	
    	ConcurrentHashMap<String, CartItem>  map = new ConcurrentHashMap<String, CartItem>(cartItems);
    	
    	this.cartItems = map;
    	items = null;
	}

	

    /**
     * 获取itemId指定的Item在购物车中的条目数据
     * @param itemId
     * @return CartItem对象
     */
    public CartItem getCartItem(String itemId) {
        return (CartItem) cartItems.get(itemId);
    }

    /**
     * 设置购物车的所有条目
     * @param items
     */
    public void setCartItemList(List<CartItem> items){
    	if(null == items){
    		this.items = null;
    		return;
    	}
    	List<CartItem> l = new ArrayList<CartItem>(items);
    	this.items = l;
    }
    
    /**
     * 获取当前购物车中的所有条目
     * @return CartItem列表
     */
    public List<CartItem> getCartItemList() {
        if (items == null) {
        	items = new ArrayList<CartItem>(cartItems.values());
        }

        return items;
    }

    /**
     * 添加item
     * @param itemId
     * @return 返回添加后的CartItem对象
     */
    public CartItem addItem(String itemId) {
        
    	CartItem cartItem = cartItems.get(itemId);
    	if(null == cartItem){
    		cartItem = new CartItem();
    		cartItem.setItemId(itemId);
    		CartItem oldCartItem = cartItems.putIfAbsent(itemId, cartItem);
    		if(null != oldCartItem){
    			cartItem = oldCartItem;
    		}
    	}

        cartItem.incrementQuantity();

        items = null;
        
        return cartItem;
    }

    /**
     * 删除item
     * @param itemId
     */
    public void removeItem(String itemId) {
   		if(null != cartItems.remove(itemId)){
   			items = null;
   		}
    }

    /**
     * 设置item的数量
     * @param itemId
     * @param quantity
     */
    public void setQuantity(String itemId, int quantity) {
        CartItem cartItem = (CartItem) cartItems.get(itemId);

        if (cartItem == null) {
            cartItem = addItem(itemId);
        }

        cartItem.setQuantity(quantity);
    }
}
