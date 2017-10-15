package com.gpd.model.checkout;

import java.util.ArrayList;
import java.util.List;

import com.gpd.model.product.CardProduct;
import com.gpd.utils.DoubleUtils;

public class Invoice {

    private double total;
    private double discount;
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
        double subtotal = products.stream().map(product -> calculatePrice(product)).reduce(0.00, Double::sum); 
        double total = subtotal - discount;
        setTotal(DoubleUtils.round2(total));
    }

    private double calculatePrice(CardProduct product) {
        return product.getPrice() - product.getDiscount();
    }

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}
