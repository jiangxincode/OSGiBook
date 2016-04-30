package org.osgichina.petstore.shoppingcartdal.dao.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.osgichina.petstore.shoppingcartdal.dao.CartDao;
import org.osgichina.petstore.shoppingcartdal.dataobject.Cart;

/**
 * CartDao的基于内存的实现
 * @author chris
 *
 */
public class CartDaoImpl implements CartDao {

	private ConcurrentHashMap<String, Cart> cartMap = new ConcurrentHashMap<String, Cart>();
	
	private Cart getOrCreate(String userId){
		Cart cart = cartMap.get(userId);
		if(null == cart){
			cart = new Cart();
			Cart oldCart = cartMap.putIfAbsent(userId, cart);
			if(null != oldCart){
				cart = oldCart;
			}
		}
		return cart;
	}
	
	public void addItemToCart(String userId, String itemId) {
		Cart cart = getOrCreate(userId);
		cart.addItem(itemId);
	}

	public Cart getCartByUserId(String userId) {
		return getOrCreate(userId);
	}

	public void removeItemFromCart(String userId, String itemId) {
		Cart cart = getOrCreate(userId);
		cart.removeItem(itemId);
	}

	public void updateItemInCart(String userId, String itemId, int quantity) {
		Cart cart = getOrCreate(userId);
		cart.setQuantity(itemId, quantity);
	}
}
