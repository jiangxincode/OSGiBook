package org.osgichina.petstore.productdal.dataobject;

import java.io.Serializable;


/**
 * Product数据对象
 * @author chris
 *
 */
public class Product implements Serializable {
    static final long serialVersionUID = 8358445392285671806L;
    private String    productId;
    private String    categoryId;
    private String    name;
    private String    logo;
    private String    description;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId                 = productId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /* Public Methods*/
    public String toString() {
        return getName();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
