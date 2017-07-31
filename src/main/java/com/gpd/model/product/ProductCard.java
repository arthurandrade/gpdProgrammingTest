package com.gpd.model.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "price" })
public class ProductCard {

    private double discount;
    private Product product;
    private Boolean applied;

    public ProductCard(Product product) {
        this.product = product;
        this.discount = 0.00;
        this.applied = false;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Product getProduct() {
        return product;
    }

    public Boolean isApplied() {
        return applied;
    }

    public void setApplied(Boolean applied) {
        this.applied = applied;
    }

    public double getPrice() {
        return product.getPrice();
    }
}
