package com.gpd.model.checkout;

import java.util.ArrayList;
import java.util.List;

import com.gpd.model.product.ProductCard;
import com.gpd.utils.DoubleUtils;

public class Invoice {

    private double total;
    private List<ProductCard> products = new ArrayList<ProductCard>();

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ProductCard> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCard> products) {
        this.products = products;
    }

    public void addProduct(ProductCard product) {
        this.products.add(product);
    }

    public void calculateTotal() {
        double total = products.stream().map(product -> calculatePrice(product)).reduce(0.00, Double::sum);
        setTotal(DoubleUtils.round2(total));
    }

    private double calculatePrice(ProductCard product) {
        return product.getPrice() - product.getDiscount();
    }

}
