package org.osgichina.petstore.shoppingcartdal.dataobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * shopping cart����
 *
 */
public class Cart implements Serializable {
	private static final long serialVersionUID = -3091661442775578603L;
	
	private ConcurrentHashMap<String/*itemId*/, CartItem>  cartItems = new ConcurrentHashMap<String, CartItem>();
    
	private transient volatile List<CartItem> items;
	
	/**
	 * ���ù��ﳵ����
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
     * ��ȡitemIdָ����Item�ڹ��ﳵ�е���Ŀ����
     * @param itemId
     * @return CartItem����
     */
    public CartItem getCartItem(String itemId) {
        return (CartItem) cartItems.get(itemId);
    }

    /**
     * ���ù��ﳵ��������Ŀ
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
     * ��ȡ��ǰ���ﳵ�е�������Ŀ
     * @return CartItem�б�
     */
    public List<CartItem> getCartItemList() {
        if (items == null) {
        	items = new ArrayList<CartItem>(cartItems.values());
        }

        return items;
    }

    /**
     * ���item
     * @param itemId
     * @return ������Ӻ��CartItem����
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
     * ɾ��item
     * @param itemId
     */
    public void removeItem(String itemId) {
   		if(null != cartItems.remove(itemId)){
   			items = null;
   		}
    }

    /**
     * ����item������
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
