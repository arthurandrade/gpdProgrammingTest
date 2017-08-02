package com.gpd.model.checkout;

import java.util.ArrayList;
import java.util.List;

import com.gpd.model.product.CardProduct;
import com.gpd.utils.DoubleUtils;

public class Invoice {

    private double total;
    private List<CardProduct> products = new ArrayList<CardProduct>();

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<CardProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CardProduct> products) {
        this.products = products;
    }

    public void addProduct(CardProduct product) {
        this.products.add(product);
    }

    public void calculateTotal() {
        double total = products.stream().map(product -> calculatePrice(product)).reduce(0.00, Double::sum);
        setTotal(DoubleUtils.round2(total));
    }

    private double calculatePrice(CardProduct product) {
        return product.getPrice() - product.getDiscount();
    }

}
