package org.osgichina.petstore.productdal.dataobject;

import java.io.Serializable;

import java.util.List;

/**
 * Category的数据对象
 * @author chris
 *
 */
public class Category
        implements Serializable {
    static final long serialVersionUID = 1439806198963349826L;
    private String    categoryId;
    private String    name;
    private String    logo;
    private String    description;
    private List<Product>      productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId.trim();
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

    public String toString() {
        return getCategoryId();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
