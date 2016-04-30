package org.osgichina.petstore.shoppingcartdal.dataobject;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 代表shopping cart中的一项。
 */
public class CartItem implements Serializable {
    private static final long serialVersionUID = -5133800203878636242L;
    private String            itemId;
    private int               quantity;

    public CartItem(){
    	
    }
    public CartItem(String itemId) {
        this.itemId = itemId;
    }

    public void setItemId(String itemId){
    	this.itemId = itemId;
    }
    
    public String getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public BigDecimal getTotal(BigDecimal listPrice) {
        if (listPrice == null) {
            return new BigDecimal(0);
        }

        return listPrice.multiply(new BigDecimal(quantity));
    }
}